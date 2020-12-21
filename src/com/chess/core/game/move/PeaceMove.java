package com.chess.core.game.move;

import com.chess.core.pieces.Piece;

public class PeaceMove extends Move {

    protected PeaceMove(Piece pieceToMove, int destinationPosition) {
        super(pieceToMove, destinationPosition);
    }

    @Override
    public String toString() {
        return "Peace / " + getMovedPiece().toString() + " / " + getMovedPiece().getPiecePosition() + " --> " + getDestinationPosition();
    }
}
