package com.lee.recommendbeautifulchina.model.result;

/**
 * @ClassName ResultCode
 * @Description 返回对象代码
 * @Author lee
 * @Date 2023/1/19 14:49
 * @Version 1.0
 */
public interface ResultCode {
    /**
     * 返回成功状态码
     */
    public static Integer SUCCESS = 20000;

    /**
     * 返回失败状态码
     */
    public static Integer ERROR = 20001;
}
