package com.chess.core.move;

import com.chess.core.board.Board;
import com.chess.core.pieces.Piece;

public class PeaceMove extends Move {

    protected PeaceMove(Board board, Piece pieceToMove, int destinationPosition) {
        super(board, pieceToMove, destinationPosition);
    }

    @Override
    public String toString() {
        return "Major / " + getMovedPiece().toString() + " / " + getMovedPiece().getPiecePosition() + " --> " + getDestinationPosition();
    }
}
