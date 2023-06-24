package com.lee.recommendbeautifulchina.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lee.recommendbeautifulchina.mapper.ScenicSpotToTypeMapper;
import com.lee.recommendbeautifulchina.model.entity.ScenicSpotToType;
import com.lee.recommendbeautifulchina.service.ScenicSpotToTypeService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName ScenicSpotToTypeServiceImpl
 * @Description ScenicSpotToTypeServiceImpl
 * @Author lee
 * @Date 2023/2/3 18:59
 * @Version 1.0
 */
@Service
public class ScenicSpotToTypeServiceImpl extends ServiceImpl<ScenicSpotToTypeMapper, ScenicSpotToType> implements ScenicSpotToTypeService {

    @Override
    public List<ScenicSpotToType> selectAllScenicSpotToTypeId() {
        QueryWrapper<ScenicSpotToType> wrapper = new QueryWrapper<>();
        wrapper.select("distinct type_id");
        List<ScenicSpotToType> list = baseMapper.selectList(wrapper);
        return list;
    }
}
