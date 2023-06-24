package com.lee.recommendbeautifulchina.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lee.recommendbeautifulchina.model.entity.ScenicSpotComment;
import com.lee.recommendbeautifulchina.model.entity.User;
import com.lee.recommendbeautifulchina.model.result.R;
import com.lee.recommendbeautifulchina.model.vo.ScenicSpotCommentVO;
import com.lee.recommendbeautifulchina.service.ScenicSpotCommentService;
import com.lee.recommendbeautifulchina.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ScenicSpotCommentController
 * @Description ScenicSpotCommentController
 * @Author lee
 * @Date 2023/2/3 19:08
 * @Version 1.0
 */
@RestController
@RequestMapping("/scenicSpotService/scenicSpotComment")
@Slf4j
@CrossOrigin
public class ScenicSpotCommentController {

    /**
     * scenicSpotCommentService
     */
    @Autowired
    private ScenicSpotCommentService scenicSpotCommentService;

    /**
     * user服务层
     */
    @Autowired
    private UserService userService;

    /**
     * 按照用户统计景点评论数
     * @param userId
     * @return
     */
    @GetMapping("/statisticsScenicSpotCommentByUserId/{userId}")
    public R statisticsScenicSpotCommentByUserId(@PathVariable("userId") String userId){

        QueryWrapper<ScenicSpotComment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);

        long scenicSpotCommentCount = scenicSpotCommentService.count(queryWrapper);

        return R.ok().data("scenicSpotCommentCount",scenicSpotCommentCount);
    }

    /**
     * 分页获取景点评论
     * @param scenicSpotId
     * @param current
     * @param limit
     * @return
     */
    @GetMapping("/pageScenicSpotCommentList/{scenicSpotId}/{current}/{limit}")
    public R pageScenicSpotCommentList(@PathVariable("scenicSpotId") String scenicSpotId,
                                     @PathVariable("current") long current,
                                     @PathVariable("limit") long limit){

        Page<ScenicSpotComment> page = new Page<>(current,limit);
        QueryWrapper<ScenicSpotComment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("scenic_spot_id",scenicSpotId);
        Page<ScenicSpotComment> scenicSpotCommentPage = scenicSpotCommentService.page(page, queryWrapper);

        List<ScenicSpotComment> list = scenicSpotCommentPage.getRecords();

        ArrayList<ScenicSpotCommentVO> scenicSpotCommentList = new ArrayList<>();

        for (ScenicSpotComment scenicSpotComment : list) {
            ScenicSpotCommentVO scenicSpotCommentVO = new ScenicSpotCommentVO();
            BeanUtils.copyProperties(scenicSpotComment,scenicSpotCommentVO);

            User user = userService.getById(scenicSpotComment.getUserId());

            scenicSpotCommentVO.setUser(user);

            scenicSpotCommentList.add(scenicSpotCommentVO);
        }

        return R.ok().data("total",scenicSpotCommentPage.getTotal())
                .data("pages",scenicSpotCommentPage.getPages())
                .data("hasPrevious",scenicSpotCommentPage.hasPrevious())
                .data("hasNext",scenicSpotCommentPage.hasNext())
                .data("scenicSpotVOList",scenicSpotCommentList);
    }

    /**
     * 景点评论
     * @param scenicSpotComment
     * @return
     */
    @PostMapping("/publishScenicSpotComment")
    public R publishScenicSpotComment(ScenicSpotComment scenicSpotComment){

        boolean saveFlag = scenicSpotCommentService.save(scenicSpotComment);
        if (saveFlag){
            return R.ok();
        }
        return R.error().message("对不起，评论资源失败！");
    }
}
