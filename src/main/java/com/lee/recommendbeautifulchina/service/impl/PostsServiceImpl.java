package com.lee.recommendbeautifulchina.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lee.recommendbeautifulchina.mapper.PostsMapper;
import com.lee.recommendbeautifulchina.model.entity.Posts;
import com.lee.recommendbeautifulchina.model.entity.PostsComment;
import com.lee.recommendbeautifulchina.model.entity.User;
import com.lee.recommendbeautifulchina.model.vo.PostsCommentVO;
import com.lee.recommendbeautifulchina.model.vo.PostsVO;
import com.lee.recommendbeautifulchina.service.PostsCommentService;
import com.lee.recommendbeautifulchina.service.PostsService;
import com.lee.recommendbeautifulchina.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName PostsServiceImpl
 * @Description 贴子服务层实现类
 * @Author lee
 * @Date 2023/1/19 15:52
 * @Version 1.0
 */
@Service
public class PostsServiceImpl extends ServiceImpl<PostsMapper, Posts> implements PostsService {

    @Autowired
    private UserService userService;

    @Autowired
    private PostsCommentService postsCommentService;

    @Override
    public List<PostsVO> postsStatisticsByUserId(String userId, long current, long limit) {

        QueryWrapper<Posts> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);

        Page<Posts> page= new Page<>(current,limit);

        // 分页获取帖子信息
        Page<Posts> postsPage = this.page(page, queryWrapper);

        // 获取帖子资源
        List<Posts> records = postsPage.getRecords();

        List<PostsVO> postsVOList = new ArrayList<>();

        // 遍历帖子, 封装 postsVO
        for (Posts record : records) {
            PostsVO postsVO = new PostsVO();
            BeanUtils.copyProperties(record,postsVO);

            User user = userService.getById(record.getUserId());
            postsVO.setUser(user);

            List<PostsCommentVO> postsCommentList = new ArrayList<>();

            // 封装帖子的评论
            QueryWrapper<PostsComment> postsCommentQueryWrapper = new QueryWrapper<>();
            postsCommentQueryWrapper.eq("posts_id",record.getId());
            List<PostsComment> comments = postsCommentService.list(postsCommentQueryWrapper);
            for (PostsComment comment : comments) {
                PostsCommentVO postsCommentVO = new PostsCommentVO();
                BeanUtils.copyProperties(comment,postsCommentVO);

                User commentUser = userService.getById(record.getUserId());
                postsCommentVO.setUser(commentUser);

                // 将评论加入到列表中
                postsCommentList.add(postsCommentVO);
            }

            // 将评论列表加入到 postsVO
            postsVO.setCommentList(postsCommentList);

            // 将封装好的postsVO追加到arrayList集合中
            postsVOList.add(postsVO);

        }

        return postsVOList;
    }
}
