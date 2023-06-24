package com.lee.recommendbeautifulchina.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName ScenicSpotDTO
 * @Description ScenicSpot DTO
 * @Author lee
 * @Date 2023/2/3 18:22
 * @Version 1.0
 */
@Data
public class ScenicSpotDTO implements Serializable {

    /**
     * 版本号
     */
    private static final long serialVersionUID = 1l;

    /**
     * 名称
     */
    private String scenicSpotName;

    /**
     * 描述
     */
    private String description;

    /**
     * 详情
     */
    private String detail;

    /**
     * 所属省份
     */
    private String region;

    /**
     * 地址
     */
    private String address;


    /**
     * 图标
     */
    private String icon;

    /**
     * 审核状态
     */
    private String audit;

    /**
     * 星级
     */
    private String star;

    /**
     * 资源用户id
     */
    private String userId;

    /**
     * 景点类型
     */
    private String[] typeList;
}
