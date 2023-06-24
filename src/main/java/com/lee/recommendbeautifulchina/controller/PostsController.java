package com.lee.recommendbeautifulchina.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lee.recommendbeautifulchina.model.dto.PostsAuditDTO;
import com.lee.recommendbeautifulchina.model.entity.Posts;
import com.lee.recommendbeautifulchina.model.entity.PostsComment;
import com.lee.recommendbeautifulchina.model.entity.User;
import com.lee.recommendbeautifulchina.model.result.R;
import com.lee.recommendbeautifulchina.model.vo.PostsCommentVO;
import com.lee.recommendbeautifulchina.model.vo.PostsVO;
import com.lee.recommendbeautifulchina.service.PostsCommentService;
import com.lee.recommendbeautifulchina.service.PostsService;
import com.lee.recommendbeautifulchina.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName PostsController
 * @Description 帖子控制层
 * @Author lee
 * @Date 2023/1/19 15:56
 * @Version 1.0
 */
@CrossOrigin
@RestController
@RequestMapping("/postsservice/posts")
@Slf4j
public class PostsController {
    /**
     * 帖子服务层
     */
    @Autowired
    private PostsService postsService;

    /**
     * 帖子评论服务层
     */
    @Autowired
    private PostsCommentService postsCommentService;

    /**
     * 用户服务层
     */
    @Autowired
    private UserService userService;


    /**
     * 分页展示帖子
     */
    @GetMapping("/pageConditionPostsList/{condition}/{current}/{limit}")
    public R pageConditionPostsList(@PathVariable("condition") String condition,
                                    @PathVariable("current") long current,
                                    @PathVariable("limit") long limit){

        Page<Posts> page= new Page<>(current,limit);

        // 分页获取帖子信息
        QueryWrapper<Posts> queryWrapper = new QueryWrapper<>();

        // 判断分页查询条件
        if ("待审核".equals(condition)){
            queryWrapper.eq("audit","待审核");
        } else {
            queryWrapper.ne("audit","待审核");
        }

        Page<Posts> postsPage = postsService.page(page, queryWrapper);

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

        return R.ok()
                .data("total",postsPage.getTotal())
                .data("pages",postsPage.getPages())
                .data("hasPrevious",postsPage.hasPrevious())
                .data("hasNext",postsPage.hasNext())
                .data("postsVOList",postsVOList);
    }

    /**
     * 审核帖子
     */
    @PostMapping("/auditPosts")
    public R auditPosts(@RequestBody PostsAuditDTO postsAuditDTO){

        UpdateWrapper<Posts> updateWrapper = new UpdateWrapper<>();

        updateWrapper.eq("id",postsAuditDTO.getId());
        updateWrapper.set("audit",postsAuditDTO.getAuditStr());

        boolean flag = postsService.update(updateWrapper);

        if (flag){
            return R.ok();
        } else {
            return R.error().message("审核失败！");
        }
    }

    /**
     * 删除帖子
     */
    @DeleteMapping("/delPosts/{postsId}")
    @Transactional
    public R delPosts(@PathVariable("postsId") String postsId){

        // 获取待删除的帖子
        Posts delPosts = postsService.getById(postsId);

        // 删除帖子评论
        QueryWrapper<PostsComment> commentQueryWrapper = new QueryWrapper<>();
        commentQueryWrapper.eq("posts_id",postsId);
        postsCommentService.remove(commentQueryWrapper);

        // 删除帖子
        boolean removePostsFlag = postsService.removeById(postsId);


        if (!removePostsFlag){
            return R.error().message("删除帖子失败！");
        } else {
            return R.ok();
        }
    }

    /**
     * 按照id查找帖子
     */
    @GetMapping("/findPostsById/{postsId}")
    public R findPostsById(@PathVariable("postsId") String postsId){

        log.info("postsId:" + postsId);

        // 获取帖子信息
        Posts posts = postsService.getById(postsId);

        PostsVO postsVO = new PostsVO();
        BeanUtils.copyProperties(posts,postsVO);

        User user = userService.getById(posts.getUserId());
        postsVO.setUser(user);

        return R.ok().data("postsVO",postsVO);
    }


    /**
     * 分页展示用户发布的帖子
     */
    @GetMapping("/postsStatisticsByUserId/{userId}/{current}/{limit}")
    public R postsStatisticsByUserId(@PathVariable("userId") String userId,
                                     @PathVariable("current") long current,
                                     @PathVariable("limit") long limit){

        QueryWrapper<Posts> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);

        Page<Posts> page= new Page<>(current,limit);

        // 分页获取帖子信息
        Page<Posts> postsPage = postsService.page(page, queryWrapper);

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

        return R.ok()
                .data("total",postsPage.getTotal())
                .data("pages",postsPage.getPages())
                .data("hasPrevious",postsPage.hasPrevious())
                .data("hasNext",postsPage.hasNext())
                .data("postsVOList",postsVOList);
    }

    /**
     * 发布帖子
     * @param posts
     * @return
     */
    @PostMapping("/publishPosts")
    public R publishPosts(Posts posts){

        // 设置帖子刚发布的点赞数为0，和审核状态为 “未审核”
        posts.setLikes(0);
        posts.setAudit("待审核");

        boolean flag = postsService.save(posts);
        if (!flag){
            return R.error().message("用户发布帖子失败！");
        }
        return R.ok();
    }

    /**
     * 统计微话数目
     * @return
     */
    @GetMapping("/statisticsPosts")
    public R statisticsPosts(){
        long postsNum = postsService.count();
        return R.ok()
                .data("postsNum",postsNum);
    }
}
