package com.chess.core.game;

public enum Alliance {
    WHITE,
    BLACK;

    @Override
    public String toString() {
        if (this.equals(Alliance.WHITE)) return "WHITE";
        return "BLACK";
    }
}
