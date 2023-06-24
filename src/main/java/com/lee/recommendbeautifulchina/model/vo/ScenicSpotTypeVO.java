package com.lee.recommendbeautifulchina.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName ScenicSpotTypeVO
 * @Description ScenicSpotType VO
 * @Author lee
 * @Date 2023/2/3 18:32
 * @Version 1.0
 */
@Data
public class ScenicSpotTypeVO implements Serializable {

    /**
     * 序列化版本号
     */
    private static final long serialVersionUID = 1L;

    /**
     * id标识号
     */
    private String id;

    /**
     * 类型名称
     */
    private String name;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 修改时间
     */
    private Date gmtModified;
}
