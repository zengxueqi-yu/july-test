package com.july.test;

import com.july.test.entity.UserInfo;
import com.july.test.service.UserInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author zqk
 * @since 2020/2/10
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class JdbcTemplateTest {

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
