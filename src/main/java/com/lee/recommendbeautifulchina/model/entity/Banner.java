package com.lee.recommendbeautifulchina.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName Banner
 * @Description 轮播图实体类
 * @Author lee
 * @Date 2023/1/19 14:56
 * @Version 1.0
 */
@TableName("t_banner")
@Data
public class Banner implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * i
     */
    @TableId(value = "id",type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * 轮播图标题
     */
    private String title;

    /**
     * 图片地址
     */
    private String imageUrl;

    /**
     * 链接地址
     */
    private String linkUrl;

    /**
     * 创建地址
     */
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    /**
     * 修改地址
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;
}
