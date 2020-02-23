package com.july.test.service;

import com.july.test.entity.UserInfo;

/**
 * @author zqk
 * @since 2020/2/7
 */
public interface UserInfoService {

    UserInfo findUserById(Long id);

    UserInfo findUserByUserName(String username);

}
