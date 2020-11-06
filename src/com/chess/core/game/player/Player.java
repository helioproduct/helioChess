package com.chess.core.game.player;

import com.chess.core.board.Board;
import com.chess.core.game.Alliance;
import com.chess.core.move.Move;
import com.chess.core.pieces.Piece;

import java.util.List;

public abstract class Player {

    private final Board board;
    private final Alliance alliance;
    private final String name;

    private List<Move> legalMoves;

    public Player(Board board, Alliance alliance, String name) {
        this.board = board;
        this.alliance = alliance;
        this.name = name;

        this.legalMoves = this.board.calculateLegalMoves(this.getPlayerAlliance());
    }

    public abstract Player getOpponent();

    private void updateLegalMoves() {
        this.legalMoves = getBoard().calculateLegalMoves(getPlayerAlliance());
    }

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
        if (isMoveLegal(move)) {
            this.board.changePiecePosition(move);
        }
        this.getOpponent().updateLegalMoves();
    }

    private boolean isMoveLegal(Move move) {
        //return this.legalMoves.contains(move);
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
    public boolean isCheck() {
        return false;
    }
    public Board getBoard() {
        return this.board;
    }


    public boolean isCheckMate() {
        return isCheck() && false;
    }
    public List<Move> getLegalMoves() {
        return this.legalMoves;
    }
}
