package com.lee.recommendbeautifulchina.model.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName ScenicSpotStatisticsByTypeVO
 * @Description ScenicSpotStatisticsByType VO
 * @Author lee
 * @Date 2023/2/3 18:32
 * @Version 1.0
 */
@Data
public class ScenicSpotStatisticsByTypeVO implements Serializable {

    /**
     * 序列化版本号
     */
    private static final long serialVersionUID = 1l;

    /**
     * 景点名
     */
    private String name;

    /**
     * 景点数目
     */
    private long value;
}
