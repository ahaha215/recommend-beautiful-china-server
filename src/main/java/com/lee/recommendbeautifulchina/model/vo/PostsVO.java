package com.lee.recommendbeautifulchina.model.vo;

import com.lee.recommendbeautifulchina.model.entity.Type;
import com.lee.recommendbeautifulchina.model.entity.User;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @ClassName PostsVO
 * @Description 帖子 VO
 * @Author lee
 * @Date 2023/1/19 15:40
 * @Version 1.0
 */
@Data
public class PostsVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id号
     */
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
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 修改时间
     */
    private Date gmtModified;

    /**
     * 帖子类型
     */
    private List<Type> typeList;

    /**
     * 发帖用户
     */
    private User user;

    /**
     * 帖子评论
     */
    private List<PostsCommentVO> commentList;
}
