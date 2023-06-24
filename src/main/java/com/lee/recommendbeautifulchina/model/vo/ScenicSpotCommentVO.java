package com.lee.recommendbeautifulchina.model.vo;

import com.lee.recommendbeautifulchina.model.entity.User;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName ScenicSpotCommentVO
 * @Description ScenicSpotComment VO
 * @Author lee
 * @Date 2023/2/3 18:31
 * @Version 1.0
 */
@Data
public class ScenicSpotCommentVO implements Serializable {

    /**
     * 版本序列号
     */
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
     * 评论景点id
     */
    private String scenicSpotId;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 修改时间
     */
    private Date gmtModified;

    /**
     * 评论用户
     */
    private User user;
}
