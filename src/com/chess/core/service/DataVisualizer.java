package com.chess.core.service;

import com.chess.core.board.Board;

import static com.chess.core.service.Converter.getColumnNumber;
import static com.chess.core.service.Converter.getRowNumber;

public class DataVisualizer {

    public static String arrayToString(String[] array) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            result.append(array[i]);
            if (i == array.length - 1) result.append("\n");
            else result.append(" ");
        }
        return result.toString();
    }

    public static String arrayToString(String[][] twoDimensionalArray) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String[] array : twoDimensionalArray) stringBuilder.append(arrayToString(array));
        return stringBuilder.toString();
    }

    public static String[][] visualizeBoard(Board board) {
        String[][] boardArray = new String[8][8];
        for (int i = 0; i < 64; i++) {
            int x = getColumnNumber(i);
            int y = getRowNumber(i);
            boardArray[y][x] = board.getBoardConfiguration()[i].toString();
        }
        return boardArray;
    }
}
