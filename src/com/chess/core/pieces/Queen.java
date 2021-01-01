package com.chess.core.pieces;

import com.chess.core.board.Board;
import com.chess.core.game.Side;
import com.chess.core.game.move.Move;
import com.chess.core.service.Converter;

import static com.chess.core.pieces.Rook.isValidPosition;

import java.util.HashSet;

import static com.chess.core.game.move.Move.createMove;
import static com.chess.core.service.Converter.*;
import static com.chess.core.service.Converter.getPosition;

public class Queen extends Piece {
    public Queen(Board board, int piecePosition, Side side) {
        super(board, piecePosition, side);
    }

    // Row and column moves
    private final int[] ROOK_OFFSETS = {-8, 8, -1, 1};

    // Diagonal moves
    private final int[] DIRECTIONS = {-1, 1};

    @Override
    public void calculateLegalMoves() {
        HashSet<Move> legalMovesCache = new HashSet<>(24);
        // Row and column moves
        for (int offset : ROOK_OFFSETS) {
            int position = this.getPiecePosition();
            int stepsCount = 1;

            while (isValidPosition(this.getPiecePosition(), position + offset) && stepsCount < 8) {
                position += offset;
                stepsCount++;
                Move move;

                // Tile is Empty
                if (!getBoard().getTile(position).isTileOccupied()) {
                    move = createMove(this, position, null);

                    legalMovesCache.add(move);
                }
                // Tile is Occupied
                else {
                    Piece pieceOnTile = getBoard().getTile(position).getPiece();
                    // Break when tile is Occupied by the same Alliance
                    if (!pieceOnTile.getPieceSide().equals(this.getPieceSide())) {
                        if (pieceOnTile.isKing()) {
                            this.game.setCheck(pieceOnTile.getPieceSide());
                        }
                        move = createMove(this, position, pieceOnTile);
                        legalMovesCache.add(move);
                    }
                    break;
                }
            }
        }

        // Diagonal moves
        for (int direction : DIRECTIONS) {
            int x = getColumnNumber(getPiecePosition());
            int y = getRowNumber(getPiecePosition());

            // Negative diagonal
            for (int offset = 1; offset < 8; offset++) {
                int destinationX = x + offset * direction;
                int destinationY = y + offset * direction;
                Move move;
                if (Converter.isValidPosition(destinationX, destinationY)) {
                    int destination = getPosition(destinationX, destinationY);
                    // Tile is Empty
                    if (!this.getBoard().getTile(destination).isTileOccupied()) {
                        move = createMove(this, destination, null);

                        legalMovesCache.add(move);
                    }
                    // Tile is Occupied
                    else {
                        Piece pieceOnTile = getBoard().getPiece(destination);
                        if (!pieceOnTile.getPieceSide().equals(this.getPieceSide())) {
                            move = createMove(this, destination, pieceOnTile);

                            legalMovesCache.add(move);
                        } break;
                    }
                }
            }

            // Positive diagonal
            for (int offset = 1; offset < 8; offset++) {
                int destinationX = x + offset * direction;
                int destinationY = y - offset * direction;
                Move move;
                if (Converter.isValidPosition(destinationX, destinationY)) {
                    int destination = getPosition(destinationX, destinationY);
                    // Tile is empty
                    if (!this.getBoard().getTile(destination).isTileOccupied()) {
                        move = createMove(this, destination, null);
                        legalMovesCache.add(move);
                    }
                    // Tile is occupied
                    else {
                        Piece pieceOnTile = getBoard().getPiece(destination);
                        if (!pieceOnTile.getPieceSide().equals(this.getPieceSide())) {
                            if (pieceOnTile.isKing()) setCheck();
                            move = createMove(this, destination, pieceOnTile);
                            legalMovesCache.add(move);
                        } break;
                    }
                }
            }
        } this.legalMoves = legalMovesCache;
    }
}
