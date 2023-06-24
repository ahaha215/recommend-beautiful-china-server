package com.lee.recommendbeautifulchina.model.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName ScenicSpotStatisticsVO
 * @Description ScenicSpotStatistics VO
 * @Author lee
 * @Date 2023/2/3 18:32
 * @Version 1.0
 */
@Data
public class ScenicSpotStatisticsVO implements Serializable {

    /**
     * 序列化版本号
     */
    private static final long serialVersionUID = 1l;

    /**
     * 景点总数目
     */
    private Long scenicSpotSum;

    /**
     * 景点评论数
     */
    private Long scenicSpotComment;

    /**
     * 景点浏览数
     */
    private Long scenicSpotView;
}
