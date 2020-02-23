package com.july.test;

import com.july.test.entity.UserInfo;
import com.july.test.service.UserInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)

public class TestApplicationTests {

    @Resource
    private UserInfoService userInfoService;

    @Test
    public void insertUser(){
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername("vayne");
        userInfo.setPassword("123456");
        userInfo.setMobile("14708222420");
    }

}
