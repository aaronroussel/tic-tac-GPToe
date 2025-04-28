package com.example.tictactoe.services;

import com.example.tictactoe.GameState;
import org.springframework.stereotype.Service;
import java.util.concurrent.ConcurrentHashMap;


@Service
public class GameService {
  private ConcurrentHashMap<String, GameState> currentGames = new ConcurrentHashMap<>();

  public void addGame(GameState game) {
    currentGames.put(game.getGameId(), game);
  }

  public GameState getGame(String gameId) {
    return currentGames.get(gameId);
  }

  public void removeGame(String gameId) {
    currentGames.remove(gameId);
  }
}
