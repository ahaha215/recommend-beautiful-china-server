package com.lee.recommendbeautifulchina.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lee.recommendbeautifulchina.model.entity.Posts;
import com.lee.recommendbeautifulchina.model.vo.PostsVO;

import java.util.List;

/**
 * @ClassName PostsService
 * @Description 帖子服务层
 * @Author lee
 * @Date 2023/1/19 15:49
 * @Version 1.0
 */
public interface PostsService extends IService<Posts> {

    /**
     * 分页展示用户发布的帖子
     */
    public List<PostsVO> postsStatisticsByUserId(String userId,long current,long limit);
}
