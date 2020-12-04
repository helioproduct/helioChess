package com.chess.core;

import com.chess.core.GUI.MainFrame;
import com.chess.core.board.Board;
import com.chess.core.game.Alliance;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Board board = new Board();
        for (int i = 0; i < 64; i++) board.visualizeLegalMoves(i);

        MainFrame mainFrame = new MainFrame();
        mainFrame.init();
        mainFrame.drawBoard(board);
        mainFrame.drawPieces(board.getPieces(Alliance.BLACK));
        mainFrame.drawPieces(board.getPieces(Alliance.WHITE));

        Scanner scan = new Scanner(System.in);
        int position = scan.nextInt();
        mainFrame.showLegalMoves(board.getPiece(position).getLegalMovesPositions());
        while (true) {
            position = scan.nextInt();
            mainFrame.showLegalMoves(board.getPiece(position).getLegalMovesPositions());
        }
    }
}