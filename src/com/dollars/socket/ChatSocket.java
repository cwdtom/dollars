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

import org.apache.ibatis.session.SqlSession;

import com.dollars.dao.UserMapper;
import com.dollars.entity.User;
import com.dollars.mybatis.MyBatisUtil;
import com.sun.istack.internal.logging.Logger;

/**
 * WebSocket∂‡»À¡ƒÃÏ
 * @author tom
 * 2015.12.14
 */
@ServerEndpoint("/chatsocket.socket")
public class ChatSocket {
	private static Logger logger = Logger.getLogger(ChatSocket.class);  
	private static final String GUEST_PREFIX = "Guest";
    private static final AtomicInteger connectionIds = new AtomicInteger(0);
    private static final Set<ChatSocket> connections = new CopyOnWriteArraySet<ChatSocket>();
 
    private final String nickname;
    private Session session;
    private String name;
 
    public ChatSocket() {
        nickname = GUEST_PREFIX;
        logger.info(GUEST_PREFIX + connectionIds.getAndIncrement());
    }
 
 
    @OnOpen
    public void start(Session session) {
        this.session = session;
        connections.add(this);
    }
 
 
    @OnClose
    public void end() {
        connections.remove(this);
        String message = "SERVER#"+name+"#close";
        logger.info(message);
        broadcast(message);
    }
 
 
    @OnMessage
    public void incoming(String message) {
        // Never trust the client
    	String[] key = message.split("#");
    	try{
	    	if(key[2].equals("connect")){
	    		name = key[0];
	    		name = name.split("\\r\\n")[0];
	    		String first = "SERVER#"+name+"#join";
	    		broadcast(first);
	    	}else{
	    		message = name+"#"+message+"#message";
	    		logger.info(message);
		        broadcast(message);
	    	}
    	}catch (ArrayIndexOutOfBoundsException e){
    		e.printStackTrace();
    		
    		SqlSession session = null;
    		User user = new User();
			try {
				session = MyBatisUtil.getSession();
				UserMapper mapper = session.getMapper(UserMapper.class);
	    		user = mapper.selectAllByName(name);
			} catch (IOException e1) {
				e1.printStackTrace();
			}finally{
				session.close();
			}
    		
    		message = name+"#"+message+"#message#"+user.getHeadImgUrl();
    		logger.info(message);
	        broadcast(message);
    	}
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
                logger.info(message);
                broadcast(message);
            }
        }
    }
}
