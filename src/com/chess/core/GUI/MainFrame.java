package com.chess.core.GUI;

import com.chess.core.board.Board;
import com.chess.core.board.Tile;
import com.chess.core.game.Game;
import com.chess.core.pieces.Piece;
import com.chess.core.service.GraphicConnector;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MainFrame implements GraphicConnector {

    JFrame frame;
    JPanel game, board;

    public void init() {
        frame = new JFrame("helioChess");
        game = new JPanel();
        game.setLayout(null);
        game.setSize(new Dimension(400, 400));
        frame.setContentPane(game);

        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    @Override
    public void drawTile(Tile tile) {
        board.add(new GTile(tile.getTileCoordinate(), tile.getColor()));
    }

    @Override
    public void drawBoard(Board board) {
        this.board = new JPanel();
        this.board.setSize(new Dimension(400, 400));
        this.board.setLayout(new GridLayout(8, 8, 0, 0));
        for (int i = 0; i < 64; i ++) {
            drawTile(board.getTile(i));
        }
        frame.getContentPane().add(this.board);
        frame.getContentPane().setPreferredSize(new Dimension(this.board.getWidth(), this.board.getHeight()));
        frame.pack();
    }

    @Override
    public void drawPiece(Piece piece) {

    }

    @Override
    public void drawPieces(ArrayList<Piece> pieceList) {

    }

    @Override
    public void movePiece(int currentPosition, int destination) {

    }

    @Override
    public void showLegalMoves(int[] legalMovesPositions) {

    }

    @Override
    public void removePiece(int removedPiecePosition) {

    }

    @Override
    public void showCheckPopup() {

    }

    @Override
    public void showCheckMatePopup() {

    }

    @Override
    public void timeEndPopup(Game game) {

    }

    @Override
    public void pauseGame() {

    }

    public void drawGamePanel(Color lightTile, Color darkTile, int size) {



        // set size of main frame to fit game panel
        frame.getContentPane().setPreferredSize(new Dimension(frame.getContentPane().getSize().width, frame.getContentPane().getSize().height));
        frame.pack();
    }

    public void drawPauseMenu() {

    }

}
