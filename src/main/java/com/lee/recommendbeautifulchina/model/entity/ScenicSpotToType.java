package com.lee.recommendbeautifulchina.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName ScenicSpotToType
 * @Description ScenicSpotToType
 * @Author lee
 * @Date 2023/2/3 18:50
 * @Version 1.0
 */
@TableName("t_scenic_spot_to_type")
@Data
public class ScenicSpotToType implements Serializable {

    /**
     * 序列化版本号
     */
    private static final long serialVersionUID = 1L;

    /**
     * id号
     */
    @TableId(value = "id",type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * 景点id
     */
    private String scenicSpotId;

    /**
     * 类型id
     */
    private String typeId;

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
