package com.chess.core.pieces;

import com.chess.core.board.Board;
import com.chess.core.game.Alliance;
import com.chess.core.move.Move;
import com.chess.core.service.Converter;

import static com.chess.core.pieces.Rook.isValidPosition;

import java.util.ArrayList;
import java.util.List;

import static com.chess.core.move.Move.createMove;
import static com.chess.core.service.Converter.*;
import static com.chess.core.service.Converter.getPosition;

public class Queen extends Piece {
    public Queen(Board board, int piecePosition, Alliance alliance) {
        super(board, piecePosition, alliance);
    }

    // Row and column moves
    private final int[] ROOK_OFFSETS = {-8, 8, -1, 1};

    // Diagonal moves
    private final int[] DIRECTIONS = {-1, 1};

    @Override
    public List<Move> calculateLegalMoves() {
        List<Move> legalMoves = new ArrayList<>();

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
                    move = createMove(getBoard(), this, position, null);
                    legalMoves.add(move);
                }
                // Tile is Occupied
                else {
                    Piece piece = getBoard().getTile(position).getPiece();
                    // Break when tile is Occupied by the same Alliance
                    if (!piece.getPieceAlliance().equals(this.getPieceAlliance())) {
                        move = createMove(getBoard(), this, position, piece);
                        legalMoves.add(move);
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
                        move = createMove(getBoard(), this, destination, null);
                        legalMoves.add(move);
                    }
                    // Tile is Occupied
                    else {
                        Piece pieceOnTile = getBoard().getPiece(destination);
                        if (!pieceOnTile.getPieceAlliance().equals(this.getPieceAlliance())) {
                            move = createMove(getBoard(), this, destination, pieceOnTile);
                            legalMoves.add(move);
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
                        move = createMove(getBoard(), this, destination, null);
                        legalMoves.add(move);
                    }
                    // Tile is occupied
                    else {
                        Piece pieceOnTile = getBoard().getPiece(destination);
                        if (!pieceOnTile.getPieceAlliance().equals(this.getPieceAlliance())) {
                            move = createMove(getBoard(), this, destination, pieceOnTile);
                            legalMoves.add(move);
                        } break;
                    }
                }
            }
        }

        return legalMoves;
    }
}
