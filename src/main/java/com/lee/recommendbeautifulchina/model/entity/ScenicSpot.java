package com.lee.recommendbeautifulchina.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName ScenicSpot
 * @Description ScenicSpot 实体类
 * @Author lee
 * @Date 2023/2/3 16:08
 * @Version 1.0
 */
@TableName("t_scenic_spot")
@Data
public class ScenicSpot implements Serializable {

    /**
     * 版本序列号
     */
    private static final long serialVersionUID = 1l;

    /**
     * id 标识符
     */
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * 景点名称
     */
    private String scenicSpotName;

    /**
     * 景点描述
     */
    private String description;

    /**
     * 景点详情
     */
    private String detail;

    /**
     * 所属地区
     */
    private String region;

    /**
     * 所在地址
     */
    private String address;

    /**
     * 景点图标
     */
    private String icon;

    /**
     * 审核状态
     */
    private String audit;

    /**
     * 星级评价
     */
    private String star;

    /**
     * 浏览人数
     */
    private int viewSum;

    /**
     * 发布用户
     */
    private String userId;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;
}
