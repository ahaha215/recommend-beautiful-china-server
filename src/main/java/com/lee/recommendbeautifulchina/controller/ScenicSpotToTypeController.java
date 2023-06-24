package com.lee.recommendbeautifulchina.controller;

import com.lee.recommendbeautifulchina.model.entity.ScenicSpotToType;
import com.lee.recommendbeautifulchina.model.entity.Type;
import com.lee.recommendbeautifulchina.model.result.R;
import com.lee.recommendbeautifulchina.model.vo.ScenicSpotTypeVO;
import com.lee.recommendbeautifulchina.service.ScenicSpotToTypeService;
import com.lee.recommendbeautifulchina.service.TypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ScenicSpotToTypeController
 * @Description ScenicSpotToTypeController
 * @Author lee
 * @Date 2023/2/3 19:08
 * @Version 1.0
 */
@RestController
@RequestMapping("/scenicSpotService/scenicSpotToType")
@Slf4j
@CrossOrigin
public class ScenicSpotToTypeController {

    /**
     * 景点类型服务层
     */
    @Autowired
    private ScenicSpotToTypeService scenicSpotToTypeService;

    /**
     * 类型服务层
     */
    @Autowired
    private TypeService typeService;


    /**
     * 查询资源类型
     *
     * @return
     */
    @GetMapping("/listScenicSpotToType")
    public R listScenicSpotToType(){

        List<ScenicSpotToType> scenicSpotToTypeList = scenicSpotToTypeService.selectAllScenicSpotToTypeId();
        List<ScenicSpotTypeVO> typeList = new ArrayList<>();

        for (ScenicSpotToType scenicSpotToType : scenicSpotToTypeList) {

            Type type = typeService.getById(scenicSpotToType.getTypeId());

            ScenicSpotTypeVO scenicSpotTypeVO = new ScenicSpotTypeVO();
            BeanUtils.copyProperties(type,scenicSpotTypeVO);
            scenicSpotTypeVO.setName(type.getTypeName());

            typeList.add(scenicSpotTypeVO);
        }

        return R.ok().data("typeList",typeList);
    }
}
