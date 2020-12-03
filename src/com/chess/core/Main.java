package com.chess.core;

import com.chess.core.GUI.MainFrame;
import com.chess.core.board.Board;
import com.chess.core.game.Alliance;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Board board = new Board();
        board.visualizeTileAlliance();

        MainFrame mainFrame = new MainFrame();
        mainFrame.init();
        mainFrame.drawBoard(board);
        mainFrame.drawPieces(board.getPieces(Alliance.BLACK));
        mainFrame.drawPieces(board.getPieces(Alliance.WHITE));
        mainFrame.showLegalMoves(board.getPiece(62).getLegalMovesPositions());

        System.out.println(Arrays.toString(board.getPiece(48).getLegalMovesPositions()));

    }
}