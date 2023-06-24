package com.lee.recommendbeautifulchina.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lee.recommendbeautifulchina.model.entity.Type;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName TypeMapper
 * @Description 类型持久层
 * @Author lee
 * @Date 2023/1/19 17:21
 * @Version 1.0
 */
@Mapper
public interface TypeMapper extends BaseMapper<Type> {
}
