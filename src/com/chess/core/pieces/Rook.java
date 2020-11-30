package com.chess.core.pieces;

import com.chess.core.board.Board;
import com.chess.core.game.Alliance;
import com.chess.core.move.Move;
import static com.chess.core.service.Converter.getRowNumber;
import static com.chess.core.service.Converter.getColumnNumber;
import static com.chess.core.move.Move.createMove;

import java.util.HashSet;

public class Rook extends Piece {
    public Rook(Board board, int piecePosition, Alliance alliance) {
        super(board, piecePosition, alliance);
    }

    private final int[] OFFSETS = {-8, 8, -1, 1};

    public HashSet<Move> calculateLegalMoves() {
        HashSet<Move> legalMoves = new HashSet<>();
        for (int offset : OFFSETS) {
            int position = this.getPiecePosition();
            int stepsCount = 1;

            while (isValidPosition(this.getPiecePosition(), position + offset) && stepsCount < 8) {
                position += offset;
                stepsCount++;
                Move move;

                // Tile is Empty
                if (!getBoard().getTile(position).isTileOccupied()) {
                    move = createMove(getBoard(), this, position, null);

                    getBoard().changeAllianceOnTile(position, getPieceAlliance());
                    legalMoves.add(move);
                }
                // Tile is Occupied
                else {
                    Piece piece = getBoard().getTile(position).getPiece();
                    // Break when tile is Occupied by the same Alliance
                    if (!piece.getPieceAlliance().equals(this.getPieceAlliance())) {
                        move = createMove(getBoard(), this, position, piece);

                        getBoard().changeAllianceOnTile(position, getPieceAlliance());
                        legalMoves.add(move);
                    }
                    break;
                }
            }
        }
        return legalMoves;
    }

    public static boolean isValidPosition(int currentPosition, int candidatePosition) {
        return getColumnNumber(currentPosition) == getColumnNumber(candidatePosition) ||
        getRowNumber(currentPosition) == getRowNumber(candidatePosition);
    }
}
