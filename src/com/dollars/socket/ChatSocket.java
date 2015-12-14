package com.dollars.socket;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/chatsocket.socket")
public class ChatSocket {
	private static final String GUEST_PREFIX = "Guest";
    private static final AtomicInteger connectionIds = new AtomicInteger(0);
    private static final Set<ChatSocket> connections = new CopyOnWriteArraySet<ChatSocket>();
 
    private final String nickname;
    private Session session;
 
    public ChatSocket() {
        nickname = GUEST_PREFIX + connectionIds.getAndIncrement();
    }
 
 
    @OnOpen
    public void start(Session session) {
        this.session = session;
        connections.add(this);
        String message = "sever#"+nickname+"#join";
        System.out.println(message);
        broadcast(message);
    }
 
 
    @OnClose
    public void end() {
        connections.remove(this);
        String message = "sever#"+nickname+"#close";
        System.out.println(message);
        broadcast(message);
    }
 
 
    @OnMessage
    public void incoming(String message) {
        // Never trust the client
    	message = nickname+"#"+message+"#message";
    	System.out.println(message);
        broadcast(message);
    }
 
 
    private void broadcast(String msg) {
        for (ChatSocket client : connections) {
            try {
                client.session.getBasicRemote().sendText(msg);
            } catch (IOException e) {
                connections.remove(client);
                try {
                    client.session.close();
                } catch (IOException e1) {
                    // Ignore
                }
                String message = "sever#"+nickname+"#close";
                System.out.println(message);
                broadcast(message);
            }
        }
    }
}
