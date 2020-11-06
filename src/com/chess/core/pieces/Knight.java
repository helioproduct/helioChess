package com.chess.core.pieces;

import com.chess.core.board.Board;
import com.chess.core.game.Alliance;
import com.chess.core.move.Move;

import static com.chess.core.service.Converter.getColumnNumber;
import static com.chess.core.service.Converter.isValidPosition;

import static com.chess.core.move.Move.createMove;

import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {
    public Knight(Board board, int piecePosition, Alliance alliance) {
        super(board, piecePosition, alliance);
    }

    private final int[] OFFSETS = {-17, -15, -10, -6, 6, 10, 15, 17};

    @Override
    public List<Move> calculateLegalMoves() {
        List<Move> legalMoves = new ArrayList<>();
        for (int offset : OFFSETS) {
            int destination = getPiecePosition() + offset;
            if (isValidPosition(destination) && isValidColumn(getPiecePosition(), destination)) {
                Move move;
                if (getBoard().getTile(destination).isTileOccupied()) {
                    Piece piece = getBoard().getTile(destination).getPiece();
                    if (!piece.getPieceAlliance().equals(this.getPieceAlliance())) {
                        move = createMove(getBoard(), this, destination, piece);
                        legalMoves.add(move);
                    }
                }
                else {
                    move = createMove(getBoard(), this, destination, null);
                    legalMoves.add(move);
                }
            }
        }
        return legalMoves;
    }

    // Difference between columns less than 2
    public boolean isValidColumn (final int currentCoordinate, final int candidateDestinationCoordinate) {
        int currentColumn = getColumnNumber(currentCoordinate);
        int candidateDestinationColumn = getColumnNumber(candidateDestinationCoordinate);
        return Math.abs(candidateDestinationColumn - currentColumn) <= 2;
    }
}


