package com.lee.recommendbeautifulchina.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lee.recommendbeautifulchina.model.dto.ScenicSpotAuditDTO;
import com.lee.recommendbeautifulchina.model.dto.ScenicSpotDTO;
import com.lee.recommendbeautifulchina.model.entity.*;
import com.lee.recommendbeautifulchina.model.result.R;
import com.lee.recommendbeautifulchina.model.vo.ScenicSpotStatisticsByTypeVO;
import com.lee.recommendbeautifulchina.model.vo.ScenicSpotStatisticsVO;
import com.lee.recommendbeautifulchina.model.vo.ScenicSpotVO;
import com.lee.recommendbeautifulchina.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @ClassName scenicSpotController
 * @Description scenicSpotController
 * @Author lee
 * @Date 2023/2/3 19:07
 * @Version 1.0
 */
@RestController
@RequestMapping("/scenicSpotService/scenicSpot")
@Slf4j
@CrossOrigin
public class ScenicSpotController {
    /**
     * 景点服务层
     */
    @Autowired
    private ScenicSpotService scenicSpotService;

    /**
     * 景点类型服务层
     */
    @Autowired
    private ScenicSpotToTypeService scenicSpotToTypeService;

    /**
     * 景点评论服务层
     */
    @Autowired
    private ScenicSpotCommentService scenicSpotCommentService;

    /**
     * 景点图标
     */
    @Autowired
    private OssService ossService;

    /**
     * 景点类型
     */
    @Autowired
    private TypeService typeService;

    /**
     * 用户服务层
     */
    @Autowired
    private UserService userService;

    /**
     * 分页展示景点
     */
    @GetMapping("/pageConditionScenicSpotList/{condition}/{current}/{limit}")
    public R pageConditionScenicSpotList(@PathVariable("condition") String condition,
                                         @PathVariable("current") long current,
                                         @PathVariable("limit") long limit){

        Page<ScenicSpot> page = new Page<>(current,limit);

        // 分页获取景点信息
        QueryWrapper<ScenicSpot> queryWrapper = new QueryWrapper<>();

        // 判断分页查询的条件
        if ("待审核".equals(condition)){
            queryWrapper.eq("audit","待审核");
        } else {
            queryWrapper.ne("audit","待审核");
        }

        Page<ScenicSpot> scenicSpotPage = scenicSpotService.page(page,queryWrapper);

        // 获取景点数据
        List<ScenicSpot> records = scenicSpotPage.getRecords();

        List<ScenicSpotVO> scenicSpotVOList = new ArrayList<>();

        // 遍历景点，封装 scenicSpotVO
        for (ScenicSpot record : records) {
            ScenicSpotVO scenicSpotVO = new ScenicSpotVO();
            BeanUtils.copyProperties(record,scenicSpotVO);

            User user = userService.getById(record.getUserId());

            scenicSpotVO.setUser(user);

            List<Type> typeList = new ArrayList<>();

            // 获取景点对应的类型
            QueryWrapper<ScenicSpotToType> scenicSpotToTypeQueryWrapper = new QueryWrapper<>();
            scenicSpotToTypeQueryWrapper.eq("scenic_spot_id",record.getId());
            List<ScenicSpotToType> list = scenicSpotToTypeService.list(scenicSpotToTypeQueryWrapper);
            for (ScenicSpotToType scenicSpotToType : list) {

                Type type = typeService.getById(scenicSpotToType.getTypeId());
                typeList.add(type);
            }

            // 将类型列表加入到 scenicSpotVO
            scenicSpotVO.setTypeList(typeList);

            // 将封装好的scenicSpotVO追加到arrayList集合中
            scenicSpotVOList.add(scenicSpotVO);

        }

        return R.ok()
                .data("total",scenicSpotPage.getTotal())
                .data("pages",scenicSpotPage.getPages())
                .data("hasPrevious",scenicSpotPage.hasPrevious())
                .data("hasNext",scenicSpotPage.hasNext())
                .data("scenicSpotVOList",scenicSpotVOList);
    }

    /**
     * 按照类型查找景点
     */
    @GetMapping("/pageScenicSpotListByType/{typeId}/{current}/{limit}")
    public R pageScenicSpotListByType(@PathVariable("typeId") String typeId,
                                    @PathVariable("current") long current,
                                    @PathVariable("limit") long limit){


        QueryWrapper<ScenicSpotToType> typeQueryWrapper = new QueryWrapper<>();
        typeQueryWrapper.eq("type_id",typeId);
        List<ScenicSpotToType> theTypeList = scenicSpotToTypeService.list(typeQueryWrapper);

        List<String> scenicSpotIdList = new ArrayList<>();

        for (ScenicSpotToType scenicSpotToType : theTypeList) {
            scenicSpotIdList.add(scenicSpotToType.getScenicSpotId());
        }

        Page<ScenicSpot> page = new Page<>(current,limit);

        // 分页获取景点信息
        QueryWrapper<ScenicSpot> queryWrapper = new QueryWrapper<>();

        // 判断分页查询的条件
        queryWrapper.eq("audit","通过");
        queryWrapper.in("id",scenicSpotIdList);


        Page<ScenicSpot> scenicSpotPage = scenicSpotService.page(page,queryWrapper);

        // 获取景点数据
        List<ScenicSpot> records = scenicSpotPage.getRecords();

        List<ScenicSpotVO> scenicSpotVOList = new ArrayList<>();

        // 遍历景点，封装 scenicSpotVO
        for (ScenicSpot record : records) {
            ScenicSpotVO scenicSpotVO = new ScenicSpotVO();
            BeanUtils.copyProperties(record,scenicSpotVO);

            User user = userService.getById(record.getUserId());

            scenicSpotVO.setUser(user);

            List<Type> typeList = new ArrayList<>();

            // 获取景点对应的类型
            QueryWrapper<ScenicSpotToType> scenicSpotToTypeQueryWrapper = new QueryWrapper<>();
            scenicSpotToTypeQueryWrapper.eq("scenic_spot_id",record.getId());
            List<ScenicSpotToType> list = scenicSpotToTypeService.list(scenicSpotToTypeQueryWrapper);
            for (ScenicSpotToType scenicSpotToType : list) {

                Type type = typeService.getById(scenicSpotToType.getTypeId());
                typeList.add(type);
            }

            // 将类型列表加入到 scenicSpotVO
            scenicSpotVO.setTypeList(typeList);

            // 将封装好的scenicSpotVO追加到arrayList集合中
            scenicSpotVOList.add(scenicSpotVO);

        }

        return R.ok()
                .data("total",scenicSpotPage.getTotal())
                .data("pages",scenicSpotPage.getPages())
                .data("hasPrevious",scenicSpotPage.hasPrevious())
                .data("hasNext",scenicSpotPage.hasNext())
                .data("scenicSpotVOList",scenicSpotVOList);
    }

    /**
     * 分页排序景点
     * @param sortCondition
     * @param current
     * @param limit
     * @return
     */
    @GetMapping("pageScenicSpotListBySort/{sortCondition}/{current}/{limit}")
    public R pageScenicSpotListBySort(@PathVariable("sortCondition") String sortCondition,
                                    @PathVariable("current") long current,
                                    @PathVariable("limit") long limit){

        Page<ScenicSpot> page = new Page<>(current,limit);

        QueryWrapper<ScenicSpot> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("audit","通过");

        if ("sortByCreateTime".equals(sortCondition)){
            // 按照发布时间先后顺序排序
            queryWrapper.orderByDesc("gmt_create");
        } else if("sortByViewSum".equals(sortCondition)){
            // 按照访问量进行降序排序
            queryWrapper.orderByDesc("view_sum");
        }

        Page<ScenicSpot> scenicSpotPage = scenicSpotService.page(page, queryWrapper);

        // 获取景点数据
        List<ScenicSpot> records = scenicSpotPage.getRecords();

        List<ScenicSpotVO> scenicSpotVOList = new ArrayList<>();

        // 遍历景点，封装 scenicSpotVO
        for (ScenicSpot record : records) {
            ScenicSpotVO scenicSpotVO = new ScenicSpotVO();
            BeanUtils.copyProperties(record,scenicSpotVO);

            User user = userService.getById(record.getUserId());

            scenicSpotVO.setUser(user);

            List<Type> typeList = new ArrayList<>();

            // 获取景点对应的类型
            QueryWrapper<ScenicSpotToType> scenicSpotToTypeQueryWrapper = new QueryWrapper<>();
            scenicSpotToTypeQueryWrapper.eq("scenic_spot_id",record.getId());
            List<ScenicSpotToType> list = scenicSpotToTypeService.list(scenicSpotToTypeQueryWrapper);
            for (ScenicSpotToType scenicSpotToType : list) {

                Type type = typeService.getById(scenicSpotToType.getTypeId());
                typeList.add(type);
            }

            // 将类型列表加入到 scenicSpotVO
            scenicSpotVO.setTypeList(typeList);

            // 将封装好的scenicSpotVO追加到arrayList集合中
            scenicSpotVOList.add(scenicSpotVO);

        }

        return R.ok()
                .data("total",scenicSpotPage.getTotal())
                .data("pages",scenicSpotPage.getPages())
                .data("hasPrevious",scenicSpotPage.hasPrevious())
                .data("hasNext",scenicSpotPage.hasNext())
                .data("scenicSpotVOList",scenicSpotVOList);
    }

    /**
     * 审核景点
     */
    @PostMapping("/auditScenicSpot")
    public R auditScenicSpot(@RequestBody ScenicSpotAuditDTO scenicSpotAuditDTO){

        UpdateWrapper<ScenicSpot> updateWrapper = new UpdateWrapper<>();

        updateWrapper.eq("id",scenicSpotAuditDTO.getId());
        updateWrapper.set("audit",scenicSpotAuditDTO.getAuditStr());

        boolean flag = scenicSpotService.update(updateWrapper);

        if (flag){
            return R.ok();
        } else {
            return R.error().message("审核失败！");
        }
    }

    /**
     * 删除景点
     * @param scenicSpotId
     * @return
     */
    @DeleteMapping("/delScenicSpot/{scenicSpotId}")
    @Transactional
    public R delScenicSpot(@PathVariable("scenicSpotId") String scenicSpotId){

        // 获取待删除的景点
        ScenicSpot delScenicSpot = scenicSpotService.getById(scenicSpotId);

        // 删除景点评论
        QueryWrapper<ScenicSpotComment> commentWrapper = new QueryWrapper<>();
        commentWrapper.eq("scenic_spot_id",scenicSpotId);
        scenicSpotCommentService.remove(commentWrapper);

        // 删除景点所包含的类型
        QueryWrapper<ScenicSpotToType> typeWrapper = new QueryWrapper<>();
        typeWrapper.eq("scenic_spot_id",scenicSpotId);
        scenicSpotToTypeService.remove(typeWrapper);

        // 删除景点
        boolean removeScenicSpotFlag = scenicSpotService.removeById(scenicSpotId);

        // 删除景点图标
        String icon = delScenicSpot.getIcon();
        String[] iconStr = icon.split("/");
        ossService.delFile(iconStr[iconStr.length - 1]);

        if (!removeScenicSpotFlag){
            return R.error().message("删除景点失败！");
        } else {
            return  R.ok();
        }
    }

    /**
     * 按照id查找景点
     * @param scenicSpotId
     * @return
     */
    @GetMapping("/findScenicSpotById/{scenicSpotId}")
    public R findScenicSpotById(@PathVariable("scenicSpotId") String scenicSpotId){

        // 获取景点信息
        ScenicSpot scenicSpot = scenicSpotService.getById(scenicSpotId);

        ScenicSpotVO scenicSpotVO = new ScenicSpotVO();
        BeanUtils.copyProperties(scenicSpot,scenicSpotVO);

        // 获取景点类型信息
        QueryWrapper<ScenicSpotToType> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("scenic_spot_id",scenicSpotId);
        List<ScenicSpotToType> list = scenicSpotToTypeService.list(queryWrapper);

        // 封装 scenicSpotType
        List<Type> scenicSpotTypeList = new ArrayList<>();

        for (ScenicSpotToType scenicSpotToType : list) {

            Type type = typeService.getById(scenicSpotToType.getTypeId());

            scenicSpotTypeList.add(type);
        }

        scenicSpotVO.setTypeList(scenicSpotTypeList);

        User user = userService.getById(scenicSpot.getUserId());

        scenicSpotVO.setUser(user);

        return R.ok().data("scenicSpotVO",scenicSpotVO);
    }

    /**
     * 景点统计
     * @return
     */
    @GetMapping("/scenicSpotStatistics")
    public R scenicSpotStatistics(){

        // 统计景点数目
        long scenicSpotSum = scenicSpotService.count();

        // 统计景点评论数
        long scenicSpotComment = scenicSpotCommentService.count();

        // 统计景点浏览数
        long scenicSpotView = 0;
        List<ScenicSpot> scenicSpots = scenicSpotService.list();
        for (ScenicSpot scenicSpot : scenicSpots) {
            int viewSum = scenicSpot.getViewSum();
            scenicSpotView = scenicSpotView + viewSum;
        }

        ScenicSpotStatisticsVO scenicSpotStatisticsVO = new ScenicSpotStatisticsVO();
        scenicSpotStatisticsVO.setScenicSpotSum(scenicSpotSum);
        scenicSpotStatisticsVO.setScenicSpotComment(scenicSpotComment);
        scenicSpotStatisticsVO.setScenicSpotView(scenicSpotView);

        return R.ok().data("scenicSpotStatistics",scenicSpotStatisticsVO);
    }

    /**
     * 统计用户推荐的景点
     * @param userId
     * @return
     */
    @GetMapping("/scenicSpotStatisticsByUserId/{userId}/{current}/{limit}")
    public R scenicSpotStatisticsByUserId(@PathVariable("userId") String userId,
                                        @PathVariable("current") long current,
                                        @PathVariable("limit") long limit){

        QueryWrapper<ScenicSpot> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);


        // 分页获取用户推荐景点
        Page<ScenicSpot> page = new Page<>(current,limit);

        Page<ScenicSpot> scenicSpotPage = scenicSpotService.page(page, queryWrapper);

        // 获取景点数据
        List<ScenicSpot> records = scenicSpotPage.getRecords();

        List<ScenicSpotVO> scenicSpotVOList = new ArrayList<>();

        // 遍历景点，封装 scenicSpotVO
        for (ScenicSpot record : records) {
            ScenicSpotVO scenicSpotVO = new ScenicSpotVO();
            BeanUtils.copyProperties(record,scenicSpotVO);

            User user = userService.getById(record.getUserId());

            scenicSpotVO.setUser(user);

            List<Type> typeList = new ArrayList<>();

            // 获取景点对应的类型
            QueryWrapper<ScenicSpotToType> scenicSpotToTypeQueryWrapper = new QueryWrapper<>();
            scenicSpotToTypeQueryWrapper.eq("scenic_spot_id",record.getId());
            List<ScenicSpotToType> list = scenicSpotToTypeService.list(scenicSpotToTypeQueryWrapper);
            for (ScenicSpotToType scenicSpotToType : list) {

                Type type = typeService.getById(scenicSpotToType.getTypeId());
                typeList.add(type);
            }

            // 将类型列表加入到 scenicSpotVO
            scenicSpotVO.setTypeList(typeList);

            // 将封装好的scenicSpotVO追加到arrayList集合中
            scenicSpotVOList.add(scenicSpotVO);

        }

        return R.ok()
                .data("total",scenicSpotPage.getTotal())
                .data("pages",scenicSpotPage.getPages())
                .data("hasPrevious",scenicSpotPage.hasPrevious())
                .data("hasNext",scenicSpotPage.hasNext())
                .data("scenicSpotVOList",scenicSpotVOList);
    }

    /**
     * 分页查询搜索景点
     * @param scenicSpotNameLike
     * @param limit
     * @param current
     * @return
     */
    @GetMapping("/pageScenicSpotListByScenicSpotNameLike/{scenicSpotNameLike}/{current}/{limit}")
    public R pageScenicSpotListByScenicSpotNameLike(@PathVariable("scenicSpotNameLike") String scenicSpotNameLike,
                                                @PathVariable("current") long current,
                                                @PathVariable("limit") long limit) {

        Page<ScenicSpot> page = new Page<>(current,limit);

        // 分页获取景点信息
        QueryWrapper<ScenicSpot> queryWrapper = new QueryWrapper<>();

        // 判断分页查询的条件
        queryWrapper.ne("audit","待审核");
        queryWrapper.like("scenic_spot_name",scenicSpotNameLike);

        Page<ScenicSpot> scenicSpotPage = scenicSpotService.page(page,queryWrapper);

        // 获取景点数据
        List<ScenicSpot> records = scenicSpotPage.getRecords();

        List<ScenicSpotVO> scenicSpotVOList = new ArrayList<>();

        // 遍历景点，封装 scenicSpotVO
        for (ScenicSpot record : records) {
            ScenicSpotVO scenicSpotVO = new ScenicSpotVO();
            BeanUtils.copyProperties(record,scenicSpotVO);

            User user = userService.getById(record.getUserId());

            scenicSpotVO.setUser(user);

            List<Type> typeList = new ArrayList<>();

            // 获取景点对应的类型
            QueryWrapper<ScenicSpotToType> scenicSpotToTypeQueryWrapper = new QueryWrapper<>();
            scenicSpotToTypeQueryWrapper.eq("scenic_spot_id",record.getId());
            List<ScenicSpotToType> list = scenicSpotToTypeService.list(scenicSpotToTypeQueryWrapper);
            for (ScenicSpotToType scenicSpotToType : list) {

                Type type = typeService.getById(scenicSpotToType.getTypeId());
                typeList.add(type);
            }

            // 将类型列表加入到 scenicSpotVO
            scenicSpotVO.setTypeList(typeList);

            // 将封装好的scenicSpotVO追加到arrayList集合中
            scenicSpotVOList.add(scenicSpotVO);

        }

        return R.ok()
                .data("total",scenicSpotPage.getTotal())
                .data("pages",scenicSpotPage.getPages())
                .data("hasPrevious",scenicSpotPage.hasPrevious())
                .data("hasNext",scenicSpotPage.hasNext())
                .data("scenicSpotVOList",scenicSpotVOList);
    }

    /**
     * 增加景点的访问量
     * @param scenicSpotId
     * @return
     */
    @PostMapping("/addScenicSpotViewSum/{scenicSpotId}")
    public R addScenicSpotViewSum(@PathVariable("scenicSpotId") String scenicSpotId){

        ScenicSpot scenicSpot = scenicSpotService.getById(scenicSpotId);

        scenicSpot.setViewSum(scenicSpot.getViewSum() + 1);
        boolean flag = scenicSpotService.updateById(scenicSpot);
        if (!flag){
            return R.error();
        }
        return R.ok();
    }

    @Transactional
    @PostMapping("/addScenicSpot")
    public R addScenicSpot(ScenicSpotDTO scenicSpotDTO){

        // 生成id
        String scenicSpotId = UUID.randomUUID().toString().replaceAll("-","");

        // 添加景点
        ScenicSpot scenicSpot = new ScenicSpot();
        BeanUtils.copyProperties(scenicSpotDTO,scenicSpot);
        scenicSpot.setId(scenicSpotId);
        scenicSpot.setViewSum(0);
        scenicSpot.setAudit("待审核");
        scenicSpotService.save(scenicSpot);

        // 添加景点类型
        String[] typeListId = scenicSpotDTO.getTypeList();
        for (String id : typeListId) {
            ScenicSpotToType scenicSpotToType = new ScenicSpotToType();
            scenicSpotToType.setTypeId(id);
            scenicSpotToType.setScenicSpotId(scenicSpotId);

            scenicSpotToTypeService.save(scenicSpotToType);
        }
        return R.ok();
    }

    /**
     * 按照景点类型统计景点
     * @return
     */
    @GetMapping("/statisticsScenicSpotByType")
    public R statisticsScenicSpotByType(){

        // 统计所有的景点数目
        long scenicSpotNum = scenicSpotService.count();

        List<Type> typeList = typeService.list();

        List<ScenicSpotStatisticsByTypeVO> scenicSpotStatisticsByTypes = new ArrayList<>();

        for (Type type : typeList) {

            ScenicSpotStatisticsByTypeVO scenicSpotStatisticsByTypeVO = new ScenicSpotStatisticsByTypeVO();
            scenicSpotStatisticsByTypeVO.setName(type.getTypeName());

            QueryWrapper<ScenicSpotToType> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("type_id",type.getId());
            long num = scenicSpotToTypeService.count(queryWrapper);
            scenicSpotStatisticsByTypeVO.setValue(num);

            scenicSpotStatisticsByTypes.add(scenicSpotStatisticsByTypeVO);
        }

        return R.ok()
                .data("scenicSpotNum",scenicSpotNum)
                .data("scenicSpotStatisticsByTypes",scenicSpotStatisticsByTypes);
    }
}
