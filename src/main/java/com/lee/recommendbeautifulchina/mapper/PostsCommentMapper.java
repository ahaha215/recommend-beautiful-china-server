package com.lee.recommendbeautifulchina.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lee.recommendbeautifulchina.model.entity.PostsComment;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName PostsCommentMapper
 * @Description 帖子评论
 * @Author lee
 * @Date 2023/1/19 15:46
 * @Version 1.0
 */
@Mapper
public interface PostsCommentMapper extends BaseMapper<PostsComment> {
}
