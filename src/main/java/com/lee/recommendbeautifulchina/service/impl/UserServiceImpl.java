package com.lee.recommendbeautifulchina.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lee.recommendbeautifulchina.mapper.UserMapper;
import com.lee.recommendbeautifulchina.model.entity.User;
import com.lee.recommendbeautifulchina.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @ClassName UserServiceImpl
 * @Description 用户中心服务层
 * @Author lee
 * @Date 2023/1/19 16:14
 * @Version 1.0
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
