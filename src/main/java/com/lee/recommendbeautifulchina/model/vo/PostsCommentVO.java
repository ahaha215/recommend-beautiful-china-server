package com.lee.recommendbeautifulchina.model.vo;

import com.lee.recommendbeautifulchina.model.entity.User;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName PostsCommentVo
 * @Description 帖子评论 VO
 * @Author lee
 * @Date 2023/1/19 15:40
 * @Version 1.0
 */
@Data
public class PostsCommentVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id号
     */
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
     * 评论用户
     */
    private User user;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 修改时间
     */
    private Date gmtModified;
}
