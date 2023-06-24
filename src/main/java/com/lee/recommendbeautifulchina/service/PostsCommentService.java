package com.lee.recommendbeautifulchina.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lee.recommendbeautifulchina.model.entity.PostsComment;

/**
 * @ClassName PostsCommentService
 * @Description 帖子评论服务层
 * @Author lee
 * @Date 2023/1/19 15:49
 * @Version 1.0
 */
public interface PostsCommentService extends IService<PostsComment> {
    int statisticsPostsCommentByUserId(String userId);
}
