package com.chess.core.game.player;

import com.chess.core.board.Board;
import com.chess.core.game.Alliance;
import com.chess.core.move.Move;
import com.chess.core.pieces.King;
import com.chess.core.pieces.Piece;

import java.util.ArrayList;
import java.util.List;

public abstract class Player {

    private final Board board;
    private final Alliance alliance;
    private final String name;

    protected boolean isCheck;
    private List<Move> legalMoves;
    private King playerKing;

    public Player(Board board, Alliance alliance, String name) {
        this.board = board;
        this.alliance = alliance;
        this.name = name;
        this.legalMoves = calculateLegalMoves();
        this.playerKing = board.getKing(getPlayerAlliance());
    }

    public abstract Player getOpponent();

    public void makeMove(int currentPosition, int destination) {
        Piece pieceToMove = null;
        Piece pieceToAttack = null;

        try {
            pieceToMove = this.board.getPiece(currentPosition);
            pieceToAttack = this.board.getPiece(destination);
        } catch (NullPointerException e) {
            // TODO : GUI Implementation of doing everything
        }

        Move move = Move.createMove(this.board, pieceToMove, destination, pieceToAttack);

        if (isMoveLegal(move)) this.board.changePiecePosition(move);

        this.getOpponent().calculateLegalMoves();
    }

    private List<Move> calculateLegalMoves() {
        List<Move> legalMoves = new ArrayList<>();
        for (Piece piece : getActivePieces()) legalMoves.addAll(piece.calculateLegalMoves());
        return legalMoves;
    }

    // Use it for manual updating legal moves
    public void updateLegalMoves() {
        this.legalMoves = calculateLegalMoves();
    }

    public boolean isMoveLegal(Move move) {
        for (Move legalMove : legalMoves) {
            if (move.equals(legalMove)) return true;
        }
        return false;
    }

    public Alliance getPlayerAlliance() {
        return this.alliance;
    }

    public List<Piece> getActivePieces() {
        return this.board.getPieces(this.getPlayerAlliance());
    }

    public King getPlayerKing() {
        return this.playerKing;
    }

    public List<Move> getLegalMoves() {
        return this.legalMoves;
    }

    public Board getBoard() {
        return this.board;
    }
}