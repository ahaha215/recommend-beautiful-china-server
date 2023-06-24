package com.lee.recommendbeautifulchina.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lee.recommendbeautifulchina.model.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName UserMapper
 * @Description 用户中心持久层
 * @Author lee
 * @Date 2023/1/19 16:10
 * @Version 1.0
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
