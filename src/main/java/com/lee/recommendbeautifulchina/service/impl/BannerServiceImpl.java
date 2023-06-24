package com.lee.recommendbeautifulchina.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lee.recommendbeautifulchina.mapper.BannerMapper;
import com.lee.recommendbeautifulchina.model.entity.Banner;
import com.lee.recommendbeautifulchina.service.BannerService;
import org.springframework.stereotype.Service;

/**
 * @ClassName BannerServiceImpl
 * @Description 轮播图服务层实现类
 * @Author lee
 * @Date 2023/1/19 15:03
 * @Version 1.0
 */
@Service
public class BannerServiceImpl extends ServiceImpl<BannerMapper, Banner> implements BannerService {
}
