package com.lee.recommendbeautifulchina.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lee.recommendbeautifulchina.model.dto.ResetPasswordDTO;
import com.lee.recommendbeautifulchina.model.entity.User;
import com.lee.recommendbeautifulchina.model.result.R;
import com.lee.recommendbeautifulchina.model.vo.PostsVO;
import com.lee.recommendbeautifulchina.model.vo.ScenicSpotVO;
import com.lee.recommendbeautifulchina.model.vo.UserInfoVO;
import com.lee.recommendbeautifulchina.service.*;
import com.lee.recommendbeautifulchina.util.MailUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Random;

/**
 * @ClassName UserController
 * @Description 用户中心控制层
 * @Author lee
 * @Date 2023/1/19 16:15
 * @Version 1.0
 */
@CrossOrigin
@RestController
@RequestMapping("/ucenterservice/user")
@Slf4j
public class UserController {

    /**
     * 用户服务层
     */
    @Autowired
    private UserService userService;

    /**
     * 帖子服务层
     */
    @Autowired
    private PostsService postsService;

    /**
     * 帖子评论服务层
     */
    @Autowired
    private PostsCommentService postsCommentService;

    /**
     * 景点服务层
     */
    @Autowired
    private ScenicSpotService scenicSpotService;

    /**
     * 景点评论服务层
     */
    @Autowired
    private ScenicSpotCommentService scenicSpotCommentService;

    /**
     * Oss服务层
     */
    @Autowired
    private OssService ossService;


    /**
     * 添加用户信息
     * @param user
     * @return
     */
    @PostMapping("/addUser")
    public R addUser(@RequestBody User user){

        // 初始值设置 ---> 积分为0，可用状态
        user.setPoints(0);
        user.setIsDisabled("是");

        boolean flag = userService.save(user);
        if (flag){
            return R.ok();
        } else {
            return R.error().message("添加用户信息失败！");
        }
    }

    /**
     * 按照用户id删除用户信息
     * @param userId
     * @return
     */
    @DeleteMapping("/delUser/{userId}")
    public R delUser(@PathVariable("userId") String userId){

        // 查询待删除的用户信息
        User userDel = userService.getById(userId);

        // 删除用户头像
        String avatar = userDel.getAvatar();
        // https://study-helper-bucket.oss-cn-beijing.aliyuncs.com/user/avatar/1425192535736.jpg
        String[] splitStr = avatar.split("/");

        ossService.delFile(splitStr[splitStr.length - 1]);


        // 删除用户信息
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",userId);

        boolean flag = userService.remove(queryWrapper);

        if (!flag) {
            return R.error().message("删除用户信息失败！");
        }

        return R.ok();
    }

    /**
     * 修改用户信息
     * @param user
     * @return
     */
    @PostMapping("/updateUserInfo")
    public R updateUserInfo(User user){

        boolean flag = userService.updateById(user);

        if (!flag){
            return R.error().message("修改用户信息失败！");
        }

        return R.ok();
    }

    /**
     * 按照用户id查找用户信息
     * @param userId
     * @return
     */
    @GetMapping("/findUserById/{userId}")
    public R findUserById(@PathVariable("userId") String userId){
        User userById = userService.getById(userId);
        return R.ok().data("userById",userById);
    }

    /**
     * 分页查询用户信息
     * @param current
     * @param limit
     * @return
     */
    @GetMapping("/pageUserList/{current}/{limit}")
    public R pageUserList(@PathVariable("current") long current,
                          @PathVariable("limit") long limit){

        Page<User> page = new Page<>(current,limit);

        Page<User> userPage = userService.page(page, null);

        return R.ok()
                .data("total",userPage.getTotal())
                .data("pages",userPage.getPages())
                .data("hasPrevious",userPage.hasPrevious())
                .data("hasNext",userPage.hasNext())
                .data("userList",userPage.getRecords());
    }

    /**
     * 按照用户名和密码登录
     * @param user
     * @return
     */
    @PostMapping("/loginByPassword")
    public R loginByPassword(User user){

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",user.getUsername());
        queryWrapper.eq("password",user.getPassword());

        User userInfo = userService.getOne(queryWrapper);

        if (userInfo == null){
            return R.error().message("用户名或者密码错误！");
        } else {
            // String jwtToken = JwtUtils.getJwtToken(userInfo.getId(), userInfo.getUsername());
            UserInfoVO userInfoVO = new UserInfoVO();
            userInfoVO.setJwtToken("jwtToken");
            BeanUtils.copyProperties(userInfo,userInfoVO);

            return R.ok().data("userInfoVO",userInfoVO);
        }
    }

    /**
     * 发送验证码
     * @param email
     * @param session
     * @return
     */
    @PostMapping("/sendVerificationCode/{email}")
    public R sendVerificationCode(@PathVariable("email") String email, HttpSession session){

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email",email);

        User user = userService.getOne(queryWrapper);

        if (user == null){
            return R.error().code(20001).message("您尚未注册！");
        }

        // 产生验证码
        String base = "0123456789ABCDEFGabcdefg";
        int size = base.length();
        Random random = new Random();
        StringBuffer code = new StringBuffer();
        for(int i = 1;i <= 4;i++){
            //产生0到size-1的随机值
            int index = random.nextInt(size);
            //在base字符串中获取下标为index的字符
            char charAt = base.charAt(index);
            //将c放入到StringBuffer中去
            code.append(charAt);
        }

        String verificationCode = code.toString();

        // 发送验证邮件
        String text = "您好，您的验证码为："+verificationCode+"，为了您的账号安全，请您不要轻易将验证码告知他人。";
        String title = "StudyHelper-登录";

        boolean mailFlag = MailUtils.sendMail(email, text, title);

        // 将验证码存入 Session
        if (mailFlag){
            session.setAttribute("verificationCode",verificationCode);
        }

        return R.ok().data("verificationCode",verificationCode);
    }

    /**
     * 通过验证码登录
     * @param email
     * @param verificationCode
     * @param session
     * @return
     */
    @PostMapping("/loginByVerificationCode/{email}/{verificationCode}")
    public R loginByVerificationCode(@PathVariable("email") String email,
                                     @PathVariable("verificationCode") String verificationCode,
                                     HttpSession session){

        // 获取Session中的验证码
        String sessionCode = (String) session.getAttribute("verificationCode");
        // 移出 Session 中的验证码 , 保证验证码只能用一次
        session.removeAttribute("verificationCode");

        if (verificationCode.equals(sessionCode)){
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("email",email);
            User userInfo = userService.getOne(queryWrapper);

            //String jwtToken = JwtUtils.getJwtToken(userInfo.getId(), userInfo.getUsername());

            UserInfoVO userInfoVO = new UserInfoVO();
            userInfoVO.setJwtToken("jwtToken");
            BeanUtils.copyProperties(userInfo,userInfoVO);

            return R.ok().data("userInfoVO",userInfoVO);

        } else {
            return R.error().message("验证码或邮箱错误！");
        }

    }


    @PostMapping("/login")
    public R login(@RequestBody(required = false) User user){
        //String jwtToken = JwtUtils.getJwtToken("123456", "admin");
        return R.ok().data("jwt","jwtToken");
    }

    @GetMapping("/getUserInfo")
    public R getUserInfo(HttpServletRequest request){
        //String id = JwtUtils.getMemberIdByJwtToken(request);
        User user = userService.getById("15626716272740966426");
        return R.ok().data("userInfo",user);
    }

    /**
     * 统计用户推荐资源，发布帖子，评论数
     * @param userId
     * @return
     */
    @GetMapping("/statisticsUserBehavior/{userId}")
    public R statisticsUserBehavior(@PathVariable("userId") String userId){

        // 统计用户推荐的景点
        List<ScenicSpotVO> scenicSpotStatisticsByUserId = scenicSpotService.scenicSpotStatisticsByUserId(userId, 1, 10);
        int totalScenicSpotNum = scenicSpotStatisticsByUserId.size();

        // 统计用户发布的帖子
        List<PostsVO> postsStatisticsByUserId = postsService.postsStatisticsByUserId(userId, 1, 10);

        int totalPostsNum = postsStatisticsByUserId.size();


        // 统计用户发表的评论
        int scenicSpotCommentNum = scenicSpotCommentService.statisticsScenicSpotCommentByUserId(userId);
        int postsCommentNum = postsCommentService.statisticsPostsCommentByUserId(userId);

        int totalCommentNum = scenicSpotCommentNum + postsCommentNum;

        return R.ok().data("totalScenicSpotNum",totalScenicSpotNum)
                .data("totalPostsNum",totalPostsNum)
                .data("totalCommentNum",totalCommentNum);
    }

    /**
     * 分页查询用户推荐的景点
     * @param userId
     * @param current
     * @param limit
     * @return
     */
    @GetMapping("/pageScenicSpotByUserId/{userId}/{current}/{limit}")
    public R pageScenicSpotByUserId(@PathVariable("userId") String userId,
                                  @PathVariable("current") long current,
                                  @PathVariable("limit") long limit){

        List<ScenicSpotVO> scenicSpotVOList = scenicSpotService.scenicSpotStatisticsByUserId(userId, current, limit);

        return R.ok().data("scenicSpotVOList",scenicSpotVOList);
    }

    /**
     * 分页查询用户发布的帖子
     * @param userId
     * @param current
     * @param limit
     * @return
     */
    @GetMapping("/pagePostsByUserId/{userId}/{current}/{limit}")
    public R pagePostsByUserId(@PathVariable("userId") String userId,
                               @PathVariable("current") long current,
                               @PathVariable("limit") long limit) {
        List<PostsVO> postsVOList = postsService.postsStatisticsByUserId(userId, current, limit);

        return R.ok().data("postsVOList",postsVOList);
    }

    /**
     * 重置密码
     * @param resetPasswordDTO
     * @param session
     * @return
     */
    @PostMapping("/resetPassword")
    public R resetPassword(ResetPasswordDTO resetPasswordDTO, HttpSession session){

        // 获取Session中的验证码
        String sessionCode = (String) session.getAttribute("verificationCode");
        // 移出 Session 中的验证码 , 保证验证码只能用一次
        session.removeAttribute("verificationCode");

        if (!resetPasswordDTO.getVerificationCode().equals(sessionCode)){
            // 验证码错误
            return R.error().code(20001).message("验证码错误！");
        } else {
            // 修改密码
            UpdateWrapper updateWrapper = new UpdateWrapper();
            updateWrapper.eq("email", resetPasswordDTO.getEmail());
            updateWrapper.set("password", resetPasswordDTO.getNewPassword());
            boolean resetPasswordFlag = userService.update(updateWrapper);
            if (resetPasswordFlag){
                return R.ok();
            } else {
                return R.error().message("对不起，密码修改失败！");
            }
        }
    }

    /**
     * 统计用户信息
     * @return
     */
    @GetMapping("/statisticsUserInfoBySex")
    public R statisticsUserInfoBySex(){

        QueryWrapper<User> queryWrapperOne = new QueryWrapper<>();
        queryWrapperOne.eq("sex","男");
        long maleNum = userService.count(queryWrapperOne);

        QueryWrapper<User> queryWrapperTwo = new QueryWrapper<>();
        queryWrapperTwo.eq("sex","女");
        long femaleNum = userService.count(queryWrapperTwo);

        return R.ok().data("maleNum",maleNum)
                .data("femaleNum",femaleNum);
    }
}
