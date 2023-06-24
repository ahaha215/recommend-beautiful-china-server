package com.lee.recommendbeautifulchina.controller;

import com.lee.recommendbeautifulchina.model.entity.Banner;
import com.lee.recommendbeautifulchina.model.result.R;
import com.lee.recommendbeautifulchina.service.BannerService;
import com.lee.recommendbeautifulchina.service.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName BannerController
 * @Description 轮播图控制层
 * @Author lee
 * @Date 2023/1/19 15:04
 * @Version 1.0
 */
@CrossOrigin
@RestController
@RequestMapping("/cms/banner")
public class BannerController {

    /**
     * 轮播图服务层
     */
    @Autowired
    private BannerService bannerService;

    /**
     * Oss对象存储服务层
     */
    @Autowired
    private OssService ossService;

    /**
     * 添加轮播图
     * @param banner
     * @return
     */
    @PostMapping("/addBanner")
    public R addBanner(@RequestBody Banner banner){
        boolean flag = bannerService.save(banner);
        if (flag){
            return R.ok();
        } else {
            return R.error();
        }
    }

    /**
     * 删除轮播图
     * @param bannerId
     * @return
     */
    @DeleteMapping("/delBanner/{bannerId}")
    public R delBanner(@PathVariable("bannerId") String bannerId){

        Banner banner = bannerService.getById(bannerId);
        String[] splitStr = banner.getImageUrl().split("/");

        // 删除轮播图图片
        String message = ossService.delFile(splitStr[splitStr.length - 1]);

        /*if (!result.getSuccess()){
            return R.error().message("删除轮播图图片资源失败！");
        }*/

        boolean flag = bannerService.removeById(bannerId);
        if (flag){
            return R.ok();
        } else {
            return R.error().message("轮播图删除失败！");
        }
    }

    /**
     * 修改轮播图信息
     * @param banner
     * @return
     */
    @PostMapping("/updateBanner")
    public R updateBanner(@RequestBody Banner banner){
        boolean flag = bannerService.updateById(banner);
        if (!flag){
            return R.error().message("修改轮播图信息失败！");
        } else {
            return R.ok();
        }
    }

    /**
     * 查询所有的轮播图
     * @return
     */
    @GetMapping("/findAllBanner")
    public R findAllBanner(){
        List<Banner> list = bannerService.list();
        return R.ok().data("bannerList",list);
    }

    /**
     * 按照id查询轮播
     * @param bannerId
     * @return
     */
    @GetMapping("/findBannerById/{bannerId}")
    public R findBannerById(@PathVariable("bannerId") String bannerId){
        Banner banner = bannerService.getById(bannerId);
        return R.ok().data("banner",banner);
    }
}
