package com.chess.core.GUI;

import com.chess.core.board.Board;
import com.chess.core.board.Tile;
import com.chess.core.game.Game;
import com.chess.core.pieces.Piece;
import com.chess.core.service.GraphicConnector;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
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
        frame.setResizable(false);
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
        int piecePosition = piece.getPiecePosition();
        Image pieceImage = getPieceImage(piece);

        GTile tile = (GTile) board.getComponent(piecePosition);
        tile.drawPiece(pieceImage);
    }

    private Image getPieceImage(Piece piece) {
        Color pieceColor = piece.getColor();
        String pieceName = piece.getPieceName();
        String imagePath, color;

        if (pieceColor == Color.WHITE) {
            color = "white";
        } else {
            color = "black";
        }

        imagePath = "pieces/" + color + "/" + pieceName + ".png";
        System.out.println(imagePath);

        Image pieceImage = null;
        try {
            pieceImage = ImageIO.read(new File(getClass().getResource("/images/" + imagePath).toURI()));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        return pieceImage;
    }

    @Override
    public void drawPieces(ArrayList<Piece> pieceList) {
        pieceList.forEach(this::drawPiece);
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

}
