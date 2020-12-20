package com.chess.core.pieces;

import com.chess.core.board.Board;
import com.chess.core.game.Side;
import com.chess.core.move.Move;

import java.util.HashSet;

import static com.chess.core.move.Move.createMove;
import static com.chess.core.service.Converter.isValidPosition;
import static com.chess.core.service.Converter.getRowNumber;
import static com.chess.core.service.Converter.getColumnNumber;

public class King extends Piece {
    public King(Board board, int piecePosition, Side side) {
        super(board, piecePosition, side);
    }

    private final int[] OFFSETS = {-9, -8, -7, -1, 1, 7, 8, 9};

    @Override
    public void calculateLegalMoves() {
        HashSet<Move> legalMovesCache = new HashSet<>(4);
        for (int offset : OFFSETS) {
            int destination = getPiecePosition() + offset;

            if (isValidPosition(destination) && isTheSameSquare(destination)) {
                if (!getBoard().getTile(destination).isTileOccupied()) {
                    legalMovesCache.add(createMove(getBoard(), this, destination, null));
                } else if (!getBoard().getTile(destination).getPiece().getPieceAlliance().equals(this.getPieceAlliance())){
                    Piece pieceOnTile = getBoard().getPiece(destination);
                    legalMovesCache.add(createMove(getBoard(), this, destination, pieceOnTile));
                }
            }
        } this.legalMoves = legalMovesCache;
    }

    private boolean isTheSameSquare(int destination) {
        int currentColumn = getColumnNumber(getPiecePosition());
        int currentRow = getRowNumber(getPiecePosition());

        int candidatePositionColumn = getColumnNumber(destination);
        int candidatePositionRow = getRowNumber(destination);

        return Math.abs(candidatePositionColumn - currentColumn) <= 1 &&
                Math.abs(candidatePositionRow - currentRow) <= 1;
    }
}
