package com.chess.core.game;

public enum Side {
    WHITE,
    BLACK;

    @Override
    public String toString() {
        if (this.equals(Side.WHITE)) return "W";
        return "B";
    }
}
