package com.lee.recommendbeautifulchina.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName Type
 * @Description 类型实体类
 * @Author lee
 * @Date 2023/1/19 15:31
 * @Version 1.0
 */
@TableName("t_type")
@Data
public class Type implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id标识号
     */
    @TableId(value = "id",type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * 类型名称
     */
    private String typeName;

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
