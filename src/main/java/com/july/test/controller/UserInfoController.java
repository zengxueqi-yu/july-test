package com.july.test.controller;

import com.july.test.config.ApiResult;
import com.july.test.config.WebSocketHandle;
import com.july.test.entity.UserInfo;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 测试用户控制器
 * @author zqk
 * @since 2020/1/17
 */
@RestController
@RequestMapping("/user")
public class UserInfoController {

    /**
     * 获取用户信息
     * @param userInfo
     * @return
     * @author zqk
     * @since 2020/1/17 11:26 上午
     * @deprecated
     */
    @PostMapping("/getUserInfo")
    public ApiResult<UserInfo> getUserInfo(@RequestBody UserInfo userInfo){
        return ApiResult.ok(userInfo);
    }

    /**
     * 获取所有用户信息
     * @param userInfo
     * @return com.july.test.config.ApiResult<java.util.List<com.july.test.entity.UserInfo>>
     * @author zqk
     * @since 2020/1/24 9:42 下午
     */
    @PostMapping("/getUserInfoList")
    public ApiResult<List<UserInfo>> getUserInfoList(@RequestBody UserInfo userInfo){
        List<UserInfo> userInfoList = new ArrayList<>();
        userInfoList.add(userInfo);
        return ApiResult.ok(userInfoList);
    }

    /**
     * 指定会话ID发消息
     *
     * @param message 消息内容
     * @param id      连接会话ID
     * @return
     */
    @GetMapping("/sendOne")
    public String sendOneMessage(String message,String id) {
        try {
            WebSocketHandle.SendMessage(message, id);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "ok";
    }

}
