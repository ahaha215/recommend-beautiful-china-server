package com.lee.recommendbeautifulchina.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName ResetPasswordDTO
 * @Description 通过验证码重置密码 DTO
 * @Author lee
 * @Date 2023/1/19 16:06
 * @Version 1.0
 */
@Data
public class ResetPasswordDTO implements Serializable {
    /**
     * 序列号
     */
    private static final long serialVersionUID = 1l;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 验证码
     */
    private String verificationCode;

    /**
     * 新密码
     */
    private String newPassword;
}
