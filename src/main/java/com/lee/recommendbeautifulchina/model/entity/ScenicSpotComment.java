package com.lee.recommendbeautifulchina.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName ScenicSpotComment
 * @Description ScenicSpotComment 实体类
 * @Author lee
 * @Date 2023/2/3 16:31
 * @Version 1.0
 */
@TableName("t_scenic_spot_comment")
@Data
public class ScenicSpotComment implements Serializable {

    /**
     * 版本序列号
     */
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
     * 评论景点id
     */
    private String scenicSpotId;

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
