package com.lee.recommendbeautifulchina.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lee.recommendbeautifulchina.model.entity.ScenicSpotComment;

/**
 * @ClassName ScenicSpotCommentService
 * @Description ScenicSpotCommentService
 * @Author lee
 * @Date 2023/2/3 18:56
 * @Version 1.0
 */
public interface ScenicSpotCommentService extends IService<ScenicSpotComment> {
    int statisticsScenicSpotCommentByUserId(String userId);
}
