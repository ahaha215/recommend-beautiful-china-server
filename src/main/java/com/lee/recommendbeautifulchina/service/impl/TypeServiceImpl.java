package com.lee.recommendbeautifulchina.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lee.recommendbeautifulchina.mapper.TypeMapper;
import com.lee.recommendbeautifulchina.model.entity.Type;
import com.lee.recommendbeautifulchina.service.TypeService;
import org.springframework.stereotype.Service;

/**
 * @ClassName TypeServiceImpl
 * @Description
 * @Author lee
 * @Date 2023/1/19 17:22
 * @Version 1.0
 */
@Service
public class TypeServiceImpl extends ServiceImpl<TypeMapper, Type> implements TypeService {
}
