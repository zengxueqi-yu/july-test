package com.july.test.controller;

import com.july.test.config.WebSocketHandle;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author zqk
 * @since 2020/2/10
 */
@RestController
@RequestMapping("/ws")
public class TestController {

    /**
     * 测试消息
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
