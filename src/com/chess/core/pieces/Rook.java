package com.chess.core.pieces;

import com.chess.core.board.Board;
import com.chess.core.game.Side;
import com.chess.core.game.move.Move;
import static com.chess.core.service.Converter.getRowNumber;
import static com.chess.core.service.Converter.getColumnNumber;
import static com.chess.core.game.move.Move.createMove;

import java.util.HashSet;

public class Rook extends Piece {
    public Rook(Board board, int piecePosition, Side side) {
        super(board, piecePosition, side);
    }

    private final int[] OFFSETS = {-8, 8, -1, 1};

    // TODO : FIX METHOD
    public void calculateLegalMoves() {
        HashSet<Move> legalMovesCache = new HashSet<>(16);
        for (int offset : OFFSETS) {
            int position = this.getPiecePosition();
            int stepsCount = 1;

            while (isValidPosition(this.getPiecePosition(), position + offset) && stepsCount < 8) {
                position += offset;
                stepsCount++;
                Move move;

                // Tile is Empty
                if (!getBoard().getTile(position).isTileOccupied()) {
                    move = createMove(this, position, null);

                    getBoard().changeAllianceOnTile(position, getPieceSide());
                    legalMovesCache.add(move);
                }
                // Tile is Occupied
                else {
                    Piece piece = getBoard().getTile(position).getPiece();
                    // Break when tile is Occupied by the same Alliance
                    if (!piece.getPieceSide().equals(this.getPieceSide())) {
                        move = createMove(this, position, piece);

                        getBoard().changeAllianceOnTile(position, getPieceSide());
                        legalMovesCache.add(move);
                    }
                    break;
                }
            }
        } this.legalMoves = legalMovesCache;
    }

    public static boolean isValidPosition(int currentPosition, int candidatePosition) {
        return getColumnNumber(currentPosition) == getColumnNumber(candidatePosition) ||
        getRowNumber(currentPosition) == getRowNumber(candidatePosition);
    }
}
