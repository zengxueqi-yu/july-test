package com.july.test.entity;

import com.july.test.jenum.SexEnum;
import lombok.Data;

/**
 * 测试用户信息
 * @author zqk
 * @since 2020/1/17
 */
@Data
public class UserInfo {

    /**
     * 用户id
     */
    private Long id;
    /**
     * 姓名
     */
    private String username;
    /**
     * 密码
     * @ingore
     */
    private String password;
    /**
     * 性别
     */
    private SexEnum sex;
    /**
     * 手机号
     */
    private String mobile;

}
