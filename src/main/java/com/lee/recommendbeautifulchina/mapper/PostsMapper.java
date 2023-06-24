package com.lee.recommendbeautifulchina.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lee.recommendbeautifulchina.model.entity.Posts;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName PostsMapper
 * @Description 帖子 Mapper
 * @Author lee
 * @Date 2023/1/19 15:46
 * @Version 1.0
 */
@Mapper
public interface PostsMapper extends BaseMapper<Posts> {
}
