package com.july.test.service.impl;

import com.july.test.entity.UserInfo;
import com.july.test.service.UserInfoService;
import org.springframework.stereotype.Service;

/**
 * @author zqk
 * @since 2020/2/7
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    /**
     * 通过id查询用户信息
     *
     * @param id
     * @return com.july.test.entity.UserInfo
     * @author zqk
     * @since 2020/2/7 1:38 下午
     */
    @Override
    public UserInfo findUserById(Long id) {
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername("zengxueqi");
        userInfo.setPassword("123456");
        userInfo.setId(1L);
        return userInfo;
    }

    /**
     * 通过用户名查询用户信息
     *
     * @param username
     * @return com.july.test.entity.UserInfo
     * @author zqk
     * @since 2020/2/7 1:45 下午
     */
    @Override
    public UserInfo findUserByUserName(String username) {
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername("zengxueqi");
        userInfo.setPassword("123456");
        userInfo.setId(1L);
        return userInfo;
    }

}
