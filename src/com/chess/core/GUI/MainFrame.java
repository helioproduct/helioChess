package com.chess.core.GUI;

import com.chess.core.board.Board;
import com.chess.core.board.Tile;
import com.chess.core.game.Game;
import com.chess.core.game.Side;
import com.chess.core.pieces.Piece;
import com.chess.core.service.GraphicConnector;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashSet;

public class MainFrame implements GraphicConnector, Runnable {

    JFrame frame;
    JPanel gamePanel, board;
    Game game;

    @Override
    public void run() {
        drawBoard(game.getBoard());
        drawPieces(game.getBoard().getPieces(Side.BLACK));
        drawPieces(game.getBoard().getPieces(Side.WHITE));
    }

    @Override
    public void init(Game game) {
        this.game = game;

        frame = new JFrame("helioChess");
        gamePanel = new JPanel();
        gamePanel.setLayout(null);
        gamePanel.setPreferredSize(new Dimension(524, 632));
        gamePanel.setBackground(Color.decode("#D3975C"));
        frame.setContentPane(gamePanel);

        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    @Override
    public void drawTile(Tile tile) {
        board.add(new GTile(tile.getTileCoordinate(), tile.getColor(), tile.getLegalColor(), game));
    }

    @Override
    public void drawBoard(Board board) {
        frame.setResizable(false);
        this.board = new JPanel();
        this.board.setSize(new Dimension(400, 400));
        this.board.setBorder(BorderFactory.createMatteBorder(12, 12, 12, 12, Color.decode("#7D532A")));
        this.board.setLocation(62, 80);
        this.board.setLayout(new GridLayout(8, 8, 0, 0));
        for (int i = 0; i < 64; i ++) {
            drawTile(board.getTile(i));
        }
        frame.getContentPane().add(this.board);
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

        Image pieceImage = null;
        try {
            pieceImage = ImageIO.read(new File(getClass().getResource("/images/" + imagePath).toURI()));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        return pieceImage;
    }

    @Override
    public void drawPieces(HashSet<Piece> pieceList) {
        pieceList.forEach(this::drawPiece);
    }

    @Override
    public void movePiece(int currentPosition, int destination) {
        GTile currentTile = (GTile) board.getComponent(currentPosition);
        GTile destinationTile = (GTile) board.getComponent(destination);

        Image pieceImage = currentTile.getPieceImage();

        if (pieceImage == null) {
            System.out.println("There is no piece on this tile");
            return;
        }

        currentTile.removePiece();
        destinationTile.drawPiece(pieceImage);
    }

    @Override
    public void showLegalMoves(int[] legalMovesPositions) {
        for (int i:legalMovesPositions) {
            GTile tile = (GTile) board.getComponent(i);
            tile.makeTargeted();
        }
        board.repaint();
    }

    public void removeLegalMoves(int[] legalMovesPositions) {
        for (int i:legalMovesPositions) {
            GTile tile = (GTile) board.getComponent(i);
            tile.makeOrdinary();
        }
        board.repaint();
    }

    @Override
    public void removePiece(int removedPiecePosition) {
        GTile tile = (GTile) board.getComponent(removedPiecePosition);
        tile.removePiece();
    }

    @Override
    public void showCheckPopup() {
        JOptionPane.showMessageDialog(frame, "Check!", "Message", JOptionPane.WARNING_MESSAGE);
    }

    @Override
    public void showCheckMatePopup() {
        JOptionPane.showMessageDialog(frame, "Check Mate!", "Message", JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public void timeEndPopup(Game game) {

    }

    @Override
    public void pauseGame() {

    }

}
