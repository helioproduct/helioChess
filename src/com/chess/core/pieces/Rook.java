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
        HashSet<Move> legalMovesCache = new HashSet<>(16);
        final int[] OFFSETS = {-8, 8, -1, 1};

        for (int offset : OFFSETS) {
            int position = this.getPiecePosition();
            int i = 1;
            while (isValidPosition(position, position + offset) && i < 8) {
                position += offset;
                i++;
                if (!this.getBoard().getTile(position).isTileOccupied()) {
                    Move move = createMove(this, position, null);
                    legalMovesCache.add(move);
                } else {
                    Piece pieceOnTile = this.getBoard().getPiece(position);
                    if (!pieceOnTile.getPieceSide().equals(this.getPieceSide())) {
                        if (pieceOnTile.isKing()) setCheck();
                        Move move = createMove(this, position, pieceOnTile);
                        legalMovesCache.add(move);
                    }
                    break;
                }
            }
        } this.legalMoves = legalMovesCache;
    }

    public static boolean isValidPosition(int currentPosition, int candidatePosition) {
        boolean firstCondition = getColumnNumber(currentPosition) == getColumnNumber(candidatePosition);
        boolean secondCondition = getRowNumber(currentPosition) == getRowNumber(candidatePosition);
        return (firstCondition || secondCondition) && Converter.isValidPosition(candidatePosition);
    }
}