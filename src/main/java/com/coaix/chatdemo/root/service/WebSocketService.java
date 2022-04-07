package com.coaix.chatdemo.root.service;

import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author LiaoWei
 * @date 2022-04-07 0:57
 */

@Component
@ServerEndpoint("/ws/WebSocketService/{userId}")
public class WebSocketService {

    private Session session;

    private static CopyOnWriteArraySet<WebSocketService> webSockets = new CopyOnWriteArraySet<>();
    private static Map<Long, Session> sessionPool = new HashMap<>();

    @OnOpen
    public void onOpen(Session session, @PathParam(value = "userId") Long userId) {
        this.session = session;
        webSockets.add(this);
        sessionPool.put(userId, session);
        System.out.println(userId + " 已连接 \n【websocket消息】有新的连接，总数为:" + webSockets.size());
    }

    @OnClose
    public void onClose() {
        webSockets.remove(this);
        System.out.println("【websocket消息】连接断开，总数为:" + webSockets.size());
    }

    @OnMessage
    public void onMessage(String message) {
        System.out.println("【websocket消息】收到客户端消息:" + message);
    }

    /**
     * 此为广播消息
     */
    public void sendAllMessage(String message) {
        for (WebSocketService webSocket : webSockets) {
            System.out.println("【websocket消息】广播消息:" + message);
            try {
                webSocket.session.getAsyncRemote().sendText(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 此为单点消息
     */
    public void sendOneMessage(Long userId, String message) {
        System.out.println("【websocket消息】单点消息:" + message);
        Session session = sessionPool.get(userId);
        if (session != null) {
            try {
                session.getAsyncRemote().sendText(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
