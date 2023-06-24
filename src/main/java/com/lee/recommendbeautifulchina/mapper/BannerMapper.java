package com.lee.recommendbeautifulchina.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lee.recommendbeautifulchina.model.entity.Banner;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName BannerMapper
 * @Description 轮播图持久层
 * @Author lee
 * @Date 2023/1/19 14:59
 * @Version 1.0
 */
@Mapper
public interface BannerMapper extends BaseMapper<Banner> {
}
