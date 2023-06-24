package com.lee.recommendbeautifulchina.controller;

import com.lee.recommendbeautifulchina.model.entity.Type;
import com.lee.recommendbeautifulchina.model.result.R;
import com.lee.recommendbeautifulchina.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName TypeController
 * @Description 类型控制器
 * @Author lee
 * @Date 2023/1/19 17:24
 * @Version 1.0
 */
@CrossOrigin
@RestController
@RequestMapping("/typeservice/type")
public class TypeController {
    /**
     * 类型服务层
     */
    @Autowired
    private TypeService typeService;

    /**
     * 添加类型
     * @param type
     * @return
     */
    @PostMapping("/addType")
    public R addType(@RequestBody Type type){
        boolean flag = typeService.save(type);
        if (!flag){
            return R.error().message("类型信息添加失败！");
        } else {
            return R.ok();
        }
    }

    /**
     * 删除类型
     * @param typeId
     * @return
     */
    @DeleteMapping("/delType/{typeId}")
    public R delType(@PathVariable("typeId") String typeId){
        boolean flag = typeService.removeById(typeId);
        if (!flag){
            return R.error().message("删除类型失败！");
        } else {
            return R.ok();
        }
    }

    /**
     * 修改类型
     * @param type
     * @return
     */
    @PostMapping("/updateType")
    public R updateType(@RequestBody Type type){
        boolean flag = typeService.updateById(type);
        if (!flag){
            return R.error().message("修改类型失败！");
        } else {
            return R.ok();
        }
    }

    /**
     * 查询所有类型信息
     * @return
     */
    @GetMapping("/findAllType")
    public R findAll(){
        List<Type> typeList = typeService.list();
        return R.ok().data("typeList",typeList);
    }

    /**
     * 按照id查询类型
     * @param typeId
     * @return
     */
    @GetMapping("/findTypeById/{typeId}")
    public R findTypeById(@PathVariable("typeId") String typeId){
        Type type = typeService.getById(typeId);
        return R.ok().data("type",type);
    }

    /**
     * 统计资源类型
     * @return
     */
    @GetMapping("/statisticsType")
    public R statisticsType(){
        long typeNum = typeService.count();
        return R.ok()
                .data("typeNum",typeNum);
    }
}
