package com.chess.core.move;

import com.chess.core.board.Board;
import com.chess.core.pieces.Piece;

public class AttackMove extends Move {

    private Piece attackedPiece;

    protected AttackMove(Board board, Piece pieceToMove, int destinationPosition, Piece attackedPiece) {
        super(board, pieceToMove, destinationPosition);
        this.attackedPiece = attackedPiece;
    }

    public Piece getAttackedPiece() {
        return this.attackedPiece;
    }

    @Override
    public String toString() {
        return "Attack / " + getMovedPiece().toString() + " / " + getMovedPiece().getPiecePosition() + " --> " + getDestinationPosition();
    }

}
