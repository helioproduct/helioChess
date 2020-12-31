package com.chess.core.pieces;

import com.chess.core.board.Board;
import com.chess.core.game.Side;
import com.chess.core.game.move.Move;
import com.chess.core.service.Converter;

import static com.chess.core.service.Converter.getRowNumber;
import static com.chess.core.service.Converter.getColumnNumber;
import static com.chess.core.game.move.Move.createMove;

import java.util.HashSet;

public class Rook extends Piece {
    public Rook(Board board, int piecePosition, Side side) {
        super(board, piecePosition, side);
    }



    public void calculateLegalMoves() {
        final int[] offsets = {8};

        HashSet<Move> legalMovesCache = new HashSet<>();
        for (int offset : offsets) {
            int position = this.getPiecePosition();
            while (isValidPosition(getPiecePosition(), position + offset)) {
                position += offset;
            }
            System.out.println(offset);
        }
    }

    public static boolean isValidPosition(int currentPosition, int candidatePosition) {
        return (getColumnNumber(currentPosition) == getColumnNumber(candidatePosition) ||
        getRowNumber(currentPosition) == getRowNumber(candidatePosition))
                && Converter.isValidPosition(candidatePosition);
    }
}
