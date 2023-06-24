package com.lee.recommendbeautifulchina.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lee.recommendbeautifulchina.model.entity.PostsToLike;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName PostsToLikeMapper
 * @Description 帖子喜欢 Mapper
 * @Author lee
 * @Date 2023/1/19 15:46
 * @Version 1.0
 */
@Mapper
public interface PostsToLikeMapper extends BaseMapper<PostsToLike> {
}
