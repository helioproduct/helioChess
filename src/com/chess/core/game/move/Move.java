package com.chess.core.game.move;

import com.chess.core.pieces.Piece;

public abstract class Move {

    private final Piece movedPiece;

    private final int currentPosition;
    private final int destinationPosition;

    private final int hashCode;

    protected Move(Piece pieceToMove, int destinationPosition) {
        this.movedPiece = pieceToMove;
        this.currentPosition = pieceToMove.getPiecePosition();
        this.destinationPosition = destinationPosition;
        this.hashCode = calculateHashCode();
    }

    public static Move createMove(Piece movedPiece, int destinationPosition, Piece attackedPiece) {
        if (attackedPiece == null) return new PeaceMove(movedPiece, destinationPosition);
        return new AttackMove(movedPiece, destinationPosition, attackedPiece);
    }

    public Piece getMovedPiece() {
        return this.movedPiece;
    }

    public int getCurrentPosition() {
        return this.currentPosition;
    }
    public int getDestinationPosition() {
        return this.destinationPosition;
    }

    private int calculateHashCode() {
        int hash = 1;
        hash *= (currentPosition + 1);
        hash *= (destinationPosition + 1);
        hash += movedPiece.hashCode();
        return hash;
    }

    @Override
    public int hashCode() {
        return this.hashCode;
    }

    @Override
    public boolean equals(Object o) {
        return this.hashCode() == o.hashCode();
    }
}