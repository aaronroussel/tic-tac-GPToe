package com.example.tictactoe;

import com.example.tictactoe.controller.WebSocketGameController;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
  private final WebSocketGameController webSocketGameController; 

  public WebSocketConfig(WebSocketGameController webSocketGameController) {
    this.webSocketGameController = webSocketGameController;
  }

  @Override
  public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
    registry.addHandler(webSocketGameController, "/game").setAllowedOrigins("*");
  }
}
