package com.lee.recommendbeautifulchina.controller;

import com.lee.recommendbeautifulchina.model.result.R;
import com.lee.recommendbeautifulchina.service.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName BannerOssController
 * @Description 轮播图 Oss 控制层
 * @Author lee
 * @Date 2023/1/19 15:13
 * @Version 1.0
 */
@CrossOrigin
@RestController
@RequestMapping("/oss/banner")
public class BannerOssController {
    /**
     * 阿里云文件上传服务层
     */
    @Autowired
    private OssService ossService;

    /**
     * 上传轮播图
     * @param file
     * @return
     */
    @PostMapping("/uploadOssFileBanner")
    public R uploadOssFileBanner(@RequestBody MultipartFile file){
        // 上传文件夹
        String folder = "cms/banner";
        String url = ossService.uploadFile(file,folder);
        return R.ok().data("url",url);
    }

    /**
     * 删除轮播图
     * @param fileName
     * @return
     */
    @DeleteMapping("/delOssFileBanner/{fileName}")
    public R delOssFileBanner(@PathVariable("fileName") String fileName){

        String path = "cms/banner/" + fileName;

        String message = ossService.delFile(path);
        return R.ok().data("message",message);
    }
}
