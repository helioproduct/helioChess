package com.chess.core.game.move;

import com.chess.core.pieces.Piece;

public class AttackMove extends Move {

    private Piece attackedPiece;

    protected AttackMove(Piece pieceToMove, int destinationPosition, Piece attackedPiece) {
        super(pieceToMove, destinationPosition);
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
