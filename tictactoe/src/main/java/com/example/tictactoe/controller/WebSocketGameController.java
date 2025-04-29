package com.example.tictactoe.controller;

import com.example.tictactoe.GameState;
import com.example.tictactoe.Player;
import com.example.tictactoe.services.MatchmakingService;
import com.example.tictactoe.services.GameService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import java.util.UUID;

@Controller
public class WebSocketGameController extends TextWebSocketHandler {

  @Autowired
  private MatchmakingService matchmakingService;
  private final GameService gameService;

  @Autowired
  public WebSocketGameController(GameService gameService) {
    this.gameService = gameService;
  }

  @Override
  public void afterConnectionEstablished(WebSocketSession session) throws Exception {
    Player newPlayer = new Player(UUID.randomUUID().toString(), session);
    matchmakingService.enqueuePlayer(newPlayer);
  }

  @Override
  protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
    ObjectMapper mapper = new ObjectMapper();
    PlayerMove move = mapper.readValue(message.getPayload(), PlayerMove.class);

    GameState game = gameService.getGame(move.getGameId());

    if(game == null) return;

    if (!move.getPlayerId().equals(game.getCurrentTurnPlayerId())) {
      session.sendMessage(new TextMessage("{\"error\":\"Not your turn!\"}"));
      return;
    }
        
    if (updateGameBoard(game, move)) {
      game.switchTurn();

      String gameUpdate = mapper.writeValueAsString(game);
      game.getPlayer1().getSession().sendMessage(new TextMessage(gameUpdate));
      game.getPlayer1().getSession().sendMessage(new TextMessage(gameUpdate));
    } else {
      session.sendMessage(new TextMessage("{\"error\":\"Invalid Move!\"}"));
    }
  }

  @Override
  public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        // if a player disconnects, do......
  }

  private boolean updateGameBoard(GameState game, PlayerMove move) {
    int row = move.getRow();
    int col = move.getCol();
    char symbol = move.getPlayerId().equals(game.getPlayer1().getId()) ? 'X' : 'O';

    if (game.getBoard()[row][col] == '\u0000') {
      game.getBoard()[row][col] = symbol;
      return true;
    }
    return false;
  }
}

class PlayerMove {
  private String gameId;
  private String playerId;
  private int row;
  private int col;

  public String getGameId() {
    return gameId;
  }

  public int getCol() {
    return col;
  }

  public int getRow() {
    return row;
  }

  public String getPlayerId() {
    return playerId;
  }
}

