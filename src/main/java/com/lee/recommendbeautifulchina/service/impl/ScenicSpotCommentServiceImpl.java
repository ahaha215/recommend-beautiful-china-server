package com.lee.recommendbeautifulchina.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lee.recommendbeautifulchina.mapper.ScenicSpotCommentMapper;
import com.lee.recommendbeautifulchina.model.entity.ScenicSpotComment;
import com.lee.recommendbeautifulchina.service.ScenicSpotCommentService;
import com.lee.recommendbeautifulchina.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName ScenicSpotCommentServiceImpl
 * @Description ScenicSpotCommentServiceImpl
 * @Author lee
 * @Date 2023/2/3 18:59
 * @Version 1.0
 */
@Service
public class ScenicSpotCommentServiceImpl extends ServiceImpl<ScenicSpotCommentMapper, ScenicSpotComment> implements ScenicSpotCommentService {

    @Override
    public int statisticsScenicSpotCommentByUserId(String userId) {

        QueryWrapper<ScenicSpotComment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_Id", userId);

        List<ScenicSpotComment> list = this.list(queryWrapper);

        int scenicSpotCommentNum = list.size();

        return scenicSpotCommentNum;
    }
}
