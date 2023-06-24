package com.lee.recommendbeautifulchina.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName Posts
 * @Description 帖子实体类
 * @Author lee
 * @Date 2023/1/19 15:30
 * @Version 1.0
 */
@TableName("t_posts")
@Data
public class Posts implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id号
     */
    @TableId(value = "id",type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * 发帖内容
     */
    private String content;

    /**
     * 审核状态
     */
    private String audit;

    /**
     * 点赞数
     */
    private Integer likes;

    /**
     * 发帖用户id
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
