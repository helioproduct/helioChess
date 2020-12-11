package com.chess.core;

import com.chess.core.GUI.MainFrame;
import com.chess.core.board.Board;
import com.chess.core.game.Alliance;
import com.chess.core.game.Game;
import com.chess.core.move.Move;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Board board = new Board();
        //board.visualizeTileAlliance();

        MainFrame mainFrame = new MainFrame();
        mainFrame.init();
        mainFrame.drawBoard(board);
        mainFrame.drawPieces(board.getPieces(Alliance.BLACK));
        mainFrame.drawPieces(board.getPieces(Alliance.WHITE));
        
        
        Scanner scanner = new Scanner(System.in);
        while (true) {
            int number = scanner.nextInt();
            if (number == -1) break;
            mainFrame.showLegalMoves(board.getPiece(number).getLegalMovesPositions());
        }

    }
}
