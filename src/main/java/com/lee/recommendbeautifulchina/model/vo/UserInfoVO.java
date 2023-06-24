package com.lee.recommendbeautifulchina.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName UserInfoVO
 * @Description 用户信息 VO
 * @Author lee
 * @Date 2023/1/19 16:07
 * @Version 1.0
 */
@Data
public class UserInfoVO implements Serializable {
    /**
     * 版本号
     */
    private static final long serialVersionUID = 1L;

    /**
     * jwtToken
     */
    private String jwtToken;

    /**
     * id
     */
    private String id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 邮箱号
     */
    private String email;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 性别
     */
    private String sex;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 积分
     */
    private Integer points;

    /**
     * 积分
     */
    private String role;

    /**
     * 状态
     */
    private String isDisabled;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 修改时间
     */
    private Date gmtModified;
}
