package com.lee.recommendbeautifulchina.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName ScenicSpotAuditDTO
 * @Description ScenicSpotAudit DTO
 * @Author lee
 * @Date 2023/2/3 18:28
 * @Version 1.0
 */
@Data
public class ScenicSpotAuditDTO implements Serializable {

    /**
     * 版本号
     */
    private static final long serialVersionUID = 1l;

    /**
     * 景点id
     */
    private String id;

    /**
     * 审核结果
     */
    private String auditStr;
}
