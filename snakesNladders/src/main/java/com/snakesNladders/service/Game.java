package com.snakesNladders.service;

import com.snakesNladders.models.Board;
import com.snakesNladders.models.Ladder;
import com.snakesNladders.models.Player;
import com.snakesNladders.models.Snake;

import java.util.*;

public class Game {

    private Board board;
    private Queue<Player> players;
    private int numberOfPlayers;

    private int noOfDice;
    private boolean shouldGameContinueTillLastPlayer;
    private static final int DEFAULT_BOARD_SIZE = 100;

    public Game(int boardSize) {
        this.board = new Board(boardSize);
        this.players = new LinkedList<>();
        this.noOfDice = Dice.DEFAULT_NO_OF_DICE;
    }

    public Game() {
        this(DEFAULT_BOARD_SIZE);
    }

    public void setPlayers(List<Player> playerList) {
        this.numberOfPlayers = playerList.size();
        Map<String, Integer> playerPieces = new HashMap<>();
        for(Player player : playerList) {
            players.add(player);
            playerPieces.put(player.getId(), 0);
        }
        board.setPlayerPieces(playerPieces);
    }

    public void setSnakes(List<Snake> snakes) {
        board.setSnakes(snakes);
    }

    public void setLadders(List<Ladder> ladders) {
        board.setLadders(ladders);
    }

    public boolean isShouldGameContinueTillLastPlayer() {
        return shouldGameContinueTillLastPlayer;
    }

    public void setShouldGameContinueTillLastPlayer(boolean shouldGameContinueTillLastPlayer) {
        this.shouldGameContinueTillLastPlayer = shouldGameContinueTillLastPlayer;
    }

    public void start() {
        while(!isGameCompleted()) {
            int rollVal = Dice.rollDice();
            Player currPlayer = players.poll();
            move(currPlayer, rollVal);
            if (hasPlayerWon(currPlayer)) {
                System.out.println(currPlayer.getName() + " wins the game.");
                board.getPlayerPieces().remove(currPlayer.getId());
            } else {
                players.add(currPlayer);
            }
        }
    }

    private boolean hasPlayerWon(Player player) {
        int winningPosition = board.getSize();
        int playerPosition = board.getPlayerPieces().get(player.getId());
        return winningPosition == playerPosition;
    }

    private void move(Player player, int rollVal) {
        int oldPosition = board.getPlayerPieces().get(player.getId());
        int newPosition = oldPosition + rollVal;

        int boardSize = board.getSize();

        if(newPosition > boardSize) {
            newPosition = oldPosition;
        } else {
            newPosition = playerPositionAfterGoingThroughSnakesAndLadders(player, newPosition);
        }
        board.getPlayerPieces().put(player.getId(), newPosition);
        System.out.println(player.getName() + " rolled a " + rollVal + " and moved from " + oldPosition +" to " + newPosition);
    }

    private int playerPositionAfterGoingThroughSnakesAndLadders(Player player, int newPosition) {
        int previousPosition = 0;

        while (previousPosition != newPosition) {
            previousPosition = newPosition;

            if (board.getSnakes().containsKey(previousPosition)) {
                newPosition = board.getSnakes().get(previousPosition);
                System.out.println(player.getName() + " bitten by snake and reached to position " + newPosition);
            }

            if (board.getLadders().containsKey(previousPosition)) {
                newPosition = board.getLadders().get(previousPosition);
                System.out.println(player.getName() + " climbed by ladder, to position " + newPosition);
            }
        }
        return newPosition;
    }

    private boolean isGameCompleted() {
        if (shouldGameContinueTillLastPlayer) {
            return players.size() == 1; // Logic means the player is has come last in the game
        }
        int currentNoOfPlayers = players.size();
        return numberOfPlayers > currentNoOfPlayers;
    }
}
