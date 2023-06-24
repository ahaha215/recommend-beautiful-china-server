package com.lee.recommendbeautifulchina.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lee.recommendbeautifulchina.model.entity.ScenicSpot;
import com.lee.recommendbeautifulchina.model.vo.ScenicSpotVO;

import java.util.List;

/**
 * @ClassName ScenicSpotService
 * @Description ScenicSpotService
 * @Author lee
 * @Date 2023/2/3 18:56
 * @Version 1.0
 */
public interface ScenicSpotService extends IService<ScenicSpot> {

    // 统计推荐的资源
    List<ScenicSpotVO> scenicSpotStatisticsByUserId(String userId,long current,long limit);
}
