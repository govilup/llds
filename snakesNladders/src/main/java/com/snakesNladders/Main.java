package com.snakesNladders;

import com.snakesNladders.models.Board;
import com.snakesNladders.models.Ladder;
import com.snakesNladders.models.Player;
import com.snakesNladders.models.Snake;
import com.snakesNladders.service.Game;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Snake snake1 = new Snake(14, 5);
        Snake snake2 = new Snake(99, 10);
        Snake snake3 = new Snake(45, 36);

        Ladder ladder1 = new Ladder(10, 28);
        Ladder ladder2 = new Ladder(18, 42);
        Ladder ladder3 = new Ladder(61, 74);

        Player player1 = new Player("Govil");
        Player player2 = new Player("Preeti");

        Game game = new Game();
        game.setLadders(Arrays.asList(ladder1,ladder2, ladder3));
        game.setPlayers(Arrays.asList(player1, player2));
        game.setSnakes(Arrays.asList(snake1, snake2, snake3));

        game.start();
    }
}