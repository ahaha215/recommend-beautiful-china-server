package com.lee.recommendbeautifulchina.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName PostsToLike
 * @Description 帖子点赞实体类
 * @Author lee
 * @Date 2023/1/19 15:31
 * @Version 1.0
 */
@TableName("t_posts_to_like")
@Data
public class PostsToLike implements Serializable {

    /**
     * 版本号
     */
    private static final long serialVersionUID = 1L;

    /**
     * id号
     */
    @TableId(value = "id",type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * 帖子id
     */
    private String postsId;

    /**
     * 点赞用户id
     */
    private String likeUserId;

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
