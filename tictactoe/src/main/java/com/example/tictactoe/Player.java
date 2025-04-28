package com.example.tictactoe;

import org.springframework.web.socket.WebSocketSession;

public class Player {
    private String id;
    private WebSocketSession session;

    public Player(String id, WebSocketSession session) {
        this.id = id;
        this.session = session;
    }

    public String getId() {
        return id;
    }

    public WebSocketSession getSession() {
        return session;
    }
}

