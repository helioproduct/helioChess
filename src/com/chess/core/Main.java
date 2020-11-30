package com.chess.core;

import com.chess.core.GUI.MainFrame;
import com.chess.core.board.Board;
import com.chess.core.game.Alliance;
import com.chess.core.pieces.Piece;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Board board = new Board();

        MainFrame mainFrame = new MainFrame();
        mainFrame.init();
        mainFrame.drawBoard(board);


        mainFrame.showCheckPopup();
        mainFrame.showCheckMatePopup();

        board.visualizeLegalMoves(48);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            int position = scanner.nextInt();
            if (position == -1) break;
            board.visualizeLegalMoves(position);
        }
    }
}