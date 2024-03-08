package com.nighthawk.spring_portfolio;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/game")
public class GameWebSocketEndpoint {

    @OnOpen
    public void onOpen(Session session) {
        System.out.println("New connection");
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        // Logic to handle incoming messages from clients
    }

    @OnClose
    public void onClose(Session session) {
        // Logic when a client disconnects
    }

    @OnError
    public void onError(Throwable error) {
        // Error handling logic
    }
}
