package com.lee.recommendbeautifulchina.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName OssService
 * @Description
 * @Author lee
 * @Date 2023/1/19 15:09
 * @Version 1.0
 */
public interface OssService {
    String uploadFile(MultipartFile file, String folder);

    String delFile(String filePath);
}
