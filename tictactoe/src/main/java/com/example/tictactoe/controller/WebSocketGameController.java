package com.example.tictactoe.controller;

import com.example.tictactoe.Player;
import com.example.tictactoe.services.MatchmakingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import java.util.UUID;

@Controller
public class WebSocketGameController extends TextWebSocketHandler {

    @Autowired
    private MatchmakingService matchmakingService;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        Player newPlayer = new Player(UUID.randomUUID().toString(), session);
        matchmakingService.enqueuePlayer(newPlayer);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // put stuff for handling moves here
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        // if a player disconnects, do......
    }
}

