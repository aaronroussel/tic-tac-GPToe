
package com.example.tictactoe.services;

import com.example.tictactoe.GameState;
import com.example.tictactoe.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.concurrent.ConcurrentLinkedQueue;

@Service
public class MatchmakingService {

    private ConcurrentLinkedQueue<Player> waitingPlayers = new ConcurrentLinkedQueue<>();

    @Autowired
    private GameService gameService;

    private ObjectMapper mapper = new ObjectMapper();

    // Method to add players to matchmaking queue
    public void enqueuePlayer(Player player) {
        waitingPlayers.offer(player);
    }

    @Scheduled(fixedRate = 1000) 
    public void matchPlayers() {
        while (waitingPlayers.size() >= 2) {
            Player player1 = waitingPlayers.poll();
            Player player2 = waitingPlayers.poll();

            if (player1 != null && player2 != null) {
                GameState game = new GameState(player1, player2);
                gameService.addGame(game);
                sendGameStartMessage(game);
            } else {
                if (player1 != null) waitingPlayers.offer(player1);
                if (player2 != null) waitingPlayers.offer(player2);
                break;
            }
        }
    }

    private void sendGameStartMessage(GameState game) {
        try {
            String gameJson = mapper.writeValueAsString(game);

            game.getPlayer1().getSession().sendMessage(new TextMessage(gameJson));
            game.getPlayer2().getSession().sendMessage(new TextMessage(gameJson));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

