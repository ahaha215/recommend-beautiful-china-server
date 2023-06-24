package com.lee.recommendbeautifulchina.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lee.recommendbeautifulchina.mapper.ScenicSpotMapper;
import com.lee.recommendbeautifulchina.model.entity.ScenicSpot;
import com.lee.recommendbeautifulchina.model.entity.User;
import com.lee.recommendbeautifulchina.model.vo.ScenicSpotVO;
import com.lee.recommendbeautifulchina.service.ScenicSpotService;
import com.lee.recommendbeautifulchina.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ScenicSpotServiceImpl
 * @Description ScenicSpotServiceImpl
 * @Author lee
 * @Date 2023/2/3 18:59
 * @Version 1.0
 */
@Service
public class ScenicSpotServiceImpl extends ServiceImpl<ScenicSpotMapper, ScenicSpot> implements ScenicSpotService {

    @Autowired
    private UserService userService;

    @Override
    public List<ScenicSpotVO> scenicSpotStatisticsByUserId(String userId, long current, long limit) {

        Page<ScenicSpot> page = new Page<>(current,limit);

        QueryWrapper<ScenicSpot> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);

        Page<ScenicSpot> scenicSpotPage = this.page(page, queryWrapper);

        List<ScenicSpot> dataList = scenicSpotPage.getRecords();

        List<ScenicSpotVO> scenicSpotList = new ArrayList<>();

        // 遍历封装景点VO
        for (ScenicSpot scenicSpot : dataList) {

            ScenicSpotVO scenicSpotVO = new ScenicSpotVO();
            BeanUtils.copyProperties(scenicSpot,scenicSpotVO);

            // 获取推荐用户
            User user = userService.getById(userId);

            scenicSpotVO.setUser(user);

            scenicSpotList.add(scenicSpotVO);
        }

        return scenicSpotList;
    }
}
