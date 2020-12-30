package com.chess.core.GUI;

import com.chess.core.game.Game;
import com.chess.core.game.Side;
import com.chess.core.game.move.Move;
import com.chess.core.pieces.Piece;

public class GUIThread extends Thread {

    private final MainFrame mainFrame;
    private final Game game;

    public int[] activeTilesPositions;

    public GUIThread(Game game) {
        this.game = game;
        this.mainFrame = new MainFrame();
    }

    @Override
    public void run() {
        this.mainFrame.init(this.game);
        this.mainFrame.drawBoard(game.getBoard());
        this.mainFrame.drawPieces(game.getBoard().getPieces(Side.BLACK));
        this.mainFrame.drawPieces(game.getBoard().getPieces(Side.WHITE));
    }

    public void movePiece(Move move) {
        this.mainFrame.movePiece(move.getCurrentPosition(), move.getDestinationPosition());
    }

    public void showLegalMoves(Piece piece) {
        if (this.activeTilesPositions != null) removeLegalMoves();
        this.mainFrame.showLegalMoves(piece.getLegalMovesPositions());
        this.activeTilesPositions = piece.getLegalMovesPositions();
    }

    public void removeLegalMoves() {
        this.mainFrame.removeLegalMoves(this.activeTilesPositions);
    }

    public void removePiece(Piece piece) {
        this.mainFrame.removePiece(piece.getPiecePosition());
    }

    // Messages
    public void showCheckPopup() {
        this.mainFrame.showCheckPopup();
    }
    public void showCheckMatePopup() {
        this.mainFrame.showCheckMatePopup();
    }
}