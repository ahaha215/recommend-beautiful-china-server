package com.lee.recommendbeautifulchina.controller;

import com.lee.recommendbeautifulchina.model.result.R;
import com.lee.recommendbeautifulchina.service.OssService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName ScenicSpotController
 * @Description
 * @Author lee
 * @Date 2023/1/19 15:13
 * @Version 1.0
 */
@CrossOrigin
@RestController
@RequestMapping("/oss/scenicSpot")
@Slf4j
public class ScenicSpotOssController {
    /**
     * 阿里云文件上传服务层
     */
    @Autowired
    private OssService ossService;

    /**
     * 上传资源图标
     * @param file
     * @return
     */
    @PostMapping("/uploadOssFileScenicSpotIcon")
    public R uploadOssFileScenicSpotIcon(MultipartFile file){

        // 上传文件夹
        String folder = "scenic_spot/icon";
        String url = ossService.uploadFile(file,folder);
        return R.ok().data("url",url);
    }

    /**
     * 删除资源图标
     * @param fileName
     * @return
     */
    @DeleteMapping("/delOssFileScenicSpotIcon/{fileName}")
    public R delOssFileBanner(@PathVariable("fileName") String fileName){

        String path = "scenicSpot/icon/" + fileName;

        String message = ossService.delFile(path);

        log.info(path);

        return R.ok().data("message",message);
    }
}
