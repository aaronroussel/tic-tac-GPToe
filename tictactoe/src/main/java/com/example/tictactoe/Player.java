package com.example.tictactoe;

import org.springframework.web.socket.WebSocketSession;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class Player {
  private String id;
  @JsonIgnore
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

