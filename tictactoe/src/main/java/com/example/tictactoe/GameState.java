package com.example.tictactoe;

import java.util.UUID;

public class GameState {
    private String gameId;
    private Player player1;
    private Player player2;
    private char[][] board; 

    public GameState(Player player1, Player player2) {
        this.gameId = UUID.randomUUID().toString();
        this.player1 = player1;
        this.player2 = player2;
        this.board = new char[3][3];  
    }

    public String getGameId() {
        return gameId;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public char[][] getBoard() {
        return board;
    }
}

