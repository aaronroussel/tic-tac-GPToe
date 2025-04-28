package com.example.tictactoe;

import java.lang.reflect.Array;

public class GameState {
  private int[] board;
  private int gameId;
  private String[] players;

  /*
   * players[0] == "X" on game board
   * players[1] == "O" on game board
   */

  GameState(String player1, String player2) {
    int[] newBoard = {-1, -1, -1, -1, -1, -1, -1, -1, -1};
    board = newBoard;
    gameId = 1;
    String[] newPlayers = {player1, player2};
    players = newPlayers;
  }

  private void updateBoard(int index, String player) {
    if (player == players[0]) {
      board[index] = 1;
    } else {
      board[index] = -1;
    }
  }

  public int[] getState() {
    return board;
  }

  public String[] getPlayers() {
    return players;
  }

  public int getGameId() {
    return gameId;
  }

}
