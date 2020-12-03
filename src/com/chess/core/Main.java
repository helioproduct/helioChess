package com.chess.core;

import com.chess.core.GUI.MainFrame;
import com.chess.core.board.Board;
import com.chess.core.game.Alliance;

public class Main {
    public static void main(String[] args) {
        Board board = new Board();
        board.visualizeTileAlliance();

        MainFrame mainFrame = new MainFrame();
        mainFrame.init();
        mainFrame.drawBoard(board);
        mainFrame.drawPieces(board.getPieces(Alliance.BLACK));
        mainFrame.drawPieces(board.getPieces(Alliance.WHITE));
        mainFrame.showCheckPopup();
        mainFrame.showCheckMatePopup();

        for (int i = 48; i < 64; i++) board.visualizeLegalMoves(i);
    }
}