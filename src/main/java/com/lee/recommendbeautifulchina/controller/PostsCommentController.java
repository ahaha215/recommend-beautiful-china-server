package com.lee.recommendbeautifulchina.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lee.recommendbeautifulchina.model.entity.PostsComment;
import com.lee.recommendbeautifulchina.model.result.R;
import com.lee.recommendbeautifulchina.service.PostsCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName PostsCommentController
 * @Description 帖子评论控制层
 * @Author lee
 * @Date 2023/1/19 15:57
 * @Version 1.0
 */
@CrossOrigin
@RestController
@RequestMapping("/postsservice/postsComment")
public class PostsCommentController {

    /**
     * postsCommentService
     */
    @Autowired
    private PostsCommentService postsCommentService;

    /**
     * 查询用户评论的帖子数
     * @param userId
     * @return
     */
    @GetMapping("/statisticsPostsCommentByUserId/{userId}")
    public R statisticsPostsCommentByUserId(@PathVariable("userId") String userId){

        QueryWrapper<PostsComment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);

        long postsCommentCount = postsCommentService.count(queryWrapper);

        return R.ok().data("postsCommentCount",postsCommentCount);
    }

    /**
     * 发布帖子评论
     * @param postsComment
     * @return
     */
    @PostMapping("/publishPostsComment")
    public R publishPostsComment(PostsComment postsComment){

        boolean flag = postsCommentService.save(postsComment);
        if (flag){
            return R.ok();
        } else {
            return R.error().message("对不起，评论帖子失败！");
        }
    }
}
