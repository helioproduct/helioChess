package com.chess.core.pieces;

import com.chess.core.board.Board;
import com.chess.core.game.Alliance;
import com.chess.core.move.Move;

import java.util.List;

public abstract class Piece {

    private final Board board;

    private int position;
    private final Alliance alliance;
    private final String pieceName;

    private final int cachedHashCode;

    public Piece(Board board, int position, Alliance alliance) {
        this.board = board;
        this.position = position;
        this.alliance = alliance;
        this.pieceName = this.getClass().getSimpleName();

        this.cachedHashCode = calculateHashCode();
    }

    public abstract List<Move> calculateLegalMoves();

    public void changePiecePosition(final Move move) {
        this.position = move.getDestinationPosition();
    }

    public Board getBoard() {
        return this.board;
    }
    public int getPiecePosition() {
        return this.position;
    }
    public String getPieceName() {
        return this.pieceName;
    }
    public Alliance getPieceAlliance() {
        return this.alliance;
    }


    private int calculateHashCode() {
        int result = 1;
        result = 31 * result + getPieceAlliance().hashCode();
        result = 31 * result + getPieceName().hashCode();
        return result;
    }

    @Override
    public int hashCode() {
        return this.cachedHashCode;
    }

    @Override
    public String toString() {
        if (getPieceAlliance().equals(Alliance.WHITE)) return String.valueOf(this.getPieceName().toCharArray()[0]);
        return String.valueOf(this.getPieceName().toCharArray()[0]).toLowerCase();
    }
}
