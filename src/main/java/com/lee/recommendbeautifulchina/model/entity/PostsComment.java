package com.lee.recommendbeautifulchina.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName PostsComment
 * @Description 帖子评论实体类
 * @Author lee
 * @Date 2023/1/19 15:31
 * @Version 1.0
 */
@TableName("t_posts_comment")
@Data
public class PostsComment implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id号
     */
    @TableId(value = "id",type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 评论帖子id
     */
    private String postsId;

    /**
     * 评论用户id
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
