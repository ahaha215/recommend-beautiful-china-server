package com.lee.recommendbeautifulchina.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName PostsAuditDTO
 * @Description 帖子审核 DTO
 * @Author lee
 * @Date 2023/1/19 15:39
 * @Version 1.0
 */
@Data
public class PostsAuditDTO implements Serializable {

    private static final long serialVersionUID = 1l;

    /**
     * 贴子id
     */
    private String id;

    /**
     * 审核结果
     */
    private String auditStr;
}
