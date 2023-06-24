package com.lee.recommendbeautifulchina.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lee.recommendbeautifulchina.mapper.PostsToLikeMapper;
import com.lee.recommendbeautifulchina.model.entity.PostsToLike;
import com.lee.recommendbeautifulchina.service.PostsToLikeService;
import org.springframework.stereotype.Service;

/**
 * @ClassName PostsToLikeServiceImpl
 * @Description 帖子点赞服务层实现类
 * @Author lee
 * @Date 2023/1/19 15:52
 * @Version 1.0
 */
@Service
public class PostsToLikeServiceImpl extends ServiceImpl<PostsToLikeMapper, PostsToLike> implements PostsToLikeService {
}
