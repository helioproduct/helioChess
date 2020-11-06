package com.chess.core.service;

public class Converter {
    public static boolean isValidPosition(int position) {
        return 0 <= position  && position <= 63;
    }
    public static boolean isValidPosition(int x, int y) {
        return 0 <= x && x <= 7 && 0 <= y && y <= 7;
    }

    public static int getColumnNumber(int position) {
        if (isValidPosition(position)) return position % 8;
        return -1;
    }

    public static int getRowNumber(int position) {
        if (isValidPosition(position)) return position / 8;
        return -1;
    }

    public static int getPosition(int coordinateX, int coordinateY) {
        return coordinateY * 8 + coordinateX;
    }
}
