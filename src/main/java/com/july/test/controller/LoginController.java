package com.july.test.controller;

import com.july.test.config.Result;
import com.july.test.entity.UserInfo;
import com.july.test.service.UserInfoService;
import com.july.test.util.ToolUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Token登陆
 * @author zqk
 * @since 2020/2/7
 */
@RestController
@RequestMapping("/api")
public class LoginController {

    @Resource
    private UserInfoService userInfoService;

    /**
     * Token登陆方法
     * @param userInfo
     * @return com.july.test.config.Result
     * @author zqk
     * @since 2020/2/7 8:24 下午
     */
    @PostMapping("/login")
    public Result login(@RequestBody UserInfo userInfo) {
        UserInfo userInfo1 = userInfoService.findUserByUserName(userInfo.getUsername());
        if (userInfo1 == null) {
            return Result.error("登录失败,用户不存在");
        } else {
            if (!userInfo1.getPassword().equals(userInfo.getPassword())) {
                return Result.error("登录失败,密码错误");
            } else {
                //生成Token
                String token = ToolUtil.getToken(userInfo1);
                return Result.ok(token);
            }
        }
    }

    @GetMapping("/token")
    public String testToken() {
        return "你已通过验证";
    }

}
