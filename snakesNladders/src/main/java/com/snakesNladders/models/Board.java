package com.snakesNladders.models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {

    private int size;
    private Map<Integer, Integer> snakes;
    private Map<Integer, Integer> ladders;
    private Map<String, Integer> playerPieces;

    public Board(int boardSize) {
        this.size = boardSize;
        this.snakes = new HashMap<>();
        this.ladders = new HashMap<>();
        this.playerPieces = new HashMap<>();
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Map<Integer, Integer> getSnakes() {
        return snakes;
    }

    public void setSnakes(List<Snake> snakeList) {
        Map<Integer, Integer> map = new HashMap<>();
        for (Snake snake : snakeList) {
            map.put(snake.start(), snake.end());
        }
        this.snakes = map;
    }

    public Map<Integer, Integer> getLadders() {
        return ladders;
    }

    public void setLadders(List<Ladder> ladderList) {
        Map<Integer, Integer> map = new HashMap<>();
        for (Ladder ladder: ladderList) {
            map.put(ladder.start(), ladder.end());
        }
        this.ladders = map;
    }

    public Map<String, Integer> getPlayerPieces() {
        return playerPieces;
    }

    public void setPlayerPieces(Map<String, Integer> playerPieces) {
        this.playerPieces = playerPieces;
    }
}
