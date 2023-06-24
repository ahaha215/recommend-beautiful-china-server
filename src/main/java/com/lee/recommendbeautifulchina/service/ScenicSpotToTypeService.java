package com.lee.recommendbeautifulchina.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lee.recommendbeautifulchina.model.entity.ScenicSpotToType;

import java.util.List;

/**
 * @ClassName ScenicSpotToTypeService
 * @Description ScenicSpotToTypeService
 * @Author lee
 * @Date 2023/2/3 18:56
 * @Version 1.0
 */
public interface ScenicSpotToTypeService extends IService<ScenicSpotToType> {
    List<ScenicSpotToType> selectAllScenicSpotToTypeId();
}
