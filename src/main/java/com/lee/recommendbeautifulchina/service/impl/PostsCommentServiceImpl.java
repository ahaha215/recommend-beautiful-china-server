package com.lee.recommendbeautifulchina.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lee.recommendbeautifulchina.mapper.PostsCommentMapper;
import com.lee.recommendbeautifulchina.model.entity.PostsComment;
import com.lee.recommendbeautifulchina.model.entity.ScenicSpotComment;
import com.lee.recommendbeautifulchina.service.PostsCommentService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName PostsCommentServiceImpl
 * @Description 帖子评论服务层实现类
 * @Author lee
 * @Date 2023/1/19 15:52
 * @Version 1.0
 */
@Service
public class PostsCommentServiceImpl extends ServiceImpl<PostsCommentMapper, PostsComment> implements PostsCommentService {
    @Override
    public int statisticsPostsCommentByUserId(String userId) {
        QueryWrapper<PostsComment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_Id", userId);

        List<PostsComment> list = this.list(queryWrapper);

        int postsCommentNum = list.size();

        return postsCommentNum;
    }
}
