package com.chess.core.game;

public enum Side {
    WHITE,
    BLACK;

    public Side getOpposite() {
        if (this.equals(Side.WHITE)) return Side.BLACK;
        else return Side.WHITE;
    }

    @Override
    public String toString() {
        if (this.equals(Side.WHITE)) return "W";
        return "B";
    }
}
