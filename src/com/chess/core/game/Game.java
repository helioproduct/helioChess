package com.chess.core.game;

public class Game {

    private final int cachedHashCode;

    public Game() {
        this.cachedHashCode = calculateHashCode();
    }

    private int calculateHashCode() {
        int result = (int) System.currentTimeMillis();
        result *= 31;
        return result;
    }

    @Override
    public int hashCode() {
        return this.cachedHashCode;
    }
}
