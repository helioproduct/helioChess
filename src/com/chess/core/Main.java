package com.chess.core;

import com.chess.core.GUI.MainFrame;
import com.chess.core.board.Board;
import com.chess.core.game.Alliance;
import com.chess.core.pieces.Queen;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Board board = new Board();
        board.clearBoard();

        board.setPiece(52, new Queen(board, 52, Alliance.WHITE));
        board.updateLegalMoves();

        MainFrame mainFrame = new MainFrame();
        mainFrame.init();
        mainFrame.drawBoard(board);
        mainFrame.drawPieces(board.getPieces(Alliance.BLACK));
        mainFrame.drawPieces(board.getPieces(Alliance.WHITE));
        mainFrame.showCheckPopup();
        mainFrame.showCheckMatePopup();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            int position = scanner.nextInt();
            if (position == - 1) break;
            board.visualizeLegalMoves(position);
        }
    }
}