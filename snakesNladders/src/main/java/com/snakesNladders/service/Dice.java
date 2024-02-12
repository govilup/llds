package com.snakesNladders.service;

import java.util.Random;

public class Dice {

    private Dice() {}

    private static boolean allowMultipleDiceRollsOnSix;
    public static final int DEFAULT_NO_OF_DICE = 1;

    private static int roll() {
        return new Random().nextInt(6) + 1;
    }

    public static int rollDice() {
        int val = roll();
        if (val == 6 && allowMultipleDiceRollsOnSix) {
            return rollDice() + val;
        }
        return val;
    }

    public void setAllowMultipleDiceRollsOnSix(boolean allowMultipleDiceRollsOnSix) {
        Dice.allowMultipleDiceRollsOnSix = allowMultipleDiceRollsOnSix;
    }
}
