package com.july.test.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * websocket操作类
 * @author zqk
 * @since 2020/2/10
 */
@ServerEndpoint(value = "/ws/server")
@Component
public class WebSocketHandle {

    private static Logger log = LoggerFactory.getLogger(WebSocketHandle.class);
    private static final AtomicInteger OnlineCount = new AtomicInteger(0);
    private static CopyOnWriteArraySet<Session> SessionSet = new CopyOnWriteArraySet<Session>();

    @PostConstruct
    public void init() {}

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session) {
        SessionSet.add(session);
        //在线连接数加1
        int cnt = OnlineCount.incrementAndGet();
        System.out.println("有新的连接进入，当前连接数目为：" + cnt + ",SessionId：" + session.getId());
        SendMessage(session, "连接成功");
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(Session session) {
        SessionSet.remove(session);
        int cnt = OnlineCount.decrementAndGet();
        System.out.println("有连接关闭，当前连接数为：" + cnt);
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("来自客户端的消息：" + message);
        SendMessage(session, "收到消息，消息内容：" + message);

    }

    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }

    /**
     * 发送消息
     * @param session
     * @param message
     */
    public static void SendMessage(Session session, String message) {
        try {
            session.getBasicRemote().sendText(message);
        } catch (IOException e) {
            System.out.println("发送消息出错：" + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 群发消息
     * @param message
     * @throws IOException
     */
    public static void BroadCastInfo(String message) throws IOException {
        for (Session session : SessionSet) {
            if (session.isOpen()) {
                SendMessage(session, message);
            }
        }
    }

    /**
     * 指定Session发送消息
     * @param sessionId
     * @param message
     * @throws IOException
     */
    public static void SendMessage(String message, String sessionId) throws IOException {
        Session session = null;
        for (Session s : SessionSet) {
            if (s.getId().equals(sessionId)) {
                session = s;
                break;
            }
        }
        if (session != null) {
            SendMessage(session, message);
        } else {
            System.out.println("没有找到你指定ID的会话：" + sessionId);
        }
    }

}
