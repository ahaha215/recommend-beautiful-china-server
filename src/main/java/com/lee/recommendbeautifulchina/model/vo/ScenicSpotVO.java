package com.lee.recommendbeautifulchina.model.vo;

import com.lee.recommendbeautifulchina.model.entity.Type;
import com.lee.recommendbeautifulchina.model.entity.User;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @ClassName ScenicSpotVO
 * @Description
 * @Author lee
 * @Date 2023/2/3 18:31
 * @Version 1.0
 */
@Data
public class ScenicSpotVO implements Serializable {

    /**
     * 版本号
     */
    private static final long serialVersionUID = 1l;

    /**
     * id 标识符
     */
    private String id;

    /**
     * 景点名称
     */
    private String scenicSpotName;

    /**
     * 景点描述
     */
    private String description;

    /**
     * 景点详情
     */
    private String detail;

    /**
     * 所属地区
     */
    private String region;

    /**
     * 所在地址
     */
    private String address;

    /**
     * 景点图标
     */
    private String icon;

    /**
     * 审核状态
     */
    private String audit;

    /**
     * 星级评价
     */
    private String star;

    /**
     * 浏览人数
     */
    private int viewSum;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 修改时间
     */
    private Date gmtModified;

    /**
     * 景点类型
     */
    private List<Type> typeList;

    /**
     * 推荐用户
     */
    private User user;
}
