package com.lee.recommendbeautifulchina.controller;

import com.lee.recommendbeautifulchina.model.result.R;
import com.lee.recommendbeautifulchina.service.OssService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName AvatarOssController
 * @Description 头像 Oss 控制层
 * @Author lee
 * @Date 2023/1/19 15:12
 * @Version 1.0
 */
@CrossOrigin
@RestController
@RequestMapping("/oss/avatar")
@Slf4j
public class AvatarOssController {
    /**
     *  阿里云文件上传服务层
     */
    @Autowired
    private OssService ossService;

    /**
     * 上传用户头像
     * @param file
     * @return
     */
    @PostMapping("/uploadOssFileAvatar")
    public R uploadOssFileAvatar(@RequestBody MultipartFile file){
        // 上传文件夹
        String folder = "user/avatar";
        String url = ossService.uploadFile(file,folder);
        return R.ok().data("url",url);
    }

    /**
     * 删除用户头像
     * @param fileName
     * @return
     */
    @DeleteMapping("/delOssFileAvatar/{fileName}")
    public R delOssFileAvatar(@PathVariable("fileName") String fileName){

        String path = "user/avatar/" + fileName;

        String message = ossService.delFile(path);
        return R.ok().data("message",message);
    }
}
