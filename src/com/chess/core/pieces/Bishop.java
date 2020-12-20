package com.chess.core.pieces;

import com.chess.core.board.Board;
import com.chess.core.game.Side;
import com.chess.core.move.Move;
import static com.chess.core.move.Move.createMove;

import java.util.HashSet;

import static com.chess.core.service.Converter.*;

public class Bishop extends Piece {
    public Bishop(Board board, int piecePosition, Side side) {
        super(board, piecePosition, side);
    }

    private final int[] DIRECTIONS = {-1, 1};

    @Override
    public void calculateLegalMoves() {
        HashSet<Move> legalMovesCache = new HashSet<>(20);

        final int[] DIRECTIONS = {-1, 1};

        for (int direction : DIRECTIONS) {
            int x = getColumnNumber(getPiecePosition());
            int y = getRowNumber(getPiecePosition());

            // Negative diagonal
            for (int offset = 1; offset < 8; offset++) {
                int destinationX = x + offset * direction;
                int destinationY = y + offset * direction;

                Move move;

                if (isValidPosition(destinationX, destinationY)) {
                    int destination = getPosition(destinationX, destinationY);

                    // Tile is empty
                    if (!this.getBoard().getTile(destination).isTileOccupied()) {
                        move = createMove(getBoard(), this, destination, null);
                        getBoard().changeAllianceOnTile(destination, getPieceAlliance());
                        legalMovesCache.add(move);
                    }
                    // Tile is occupied
                    else {
                        Piece pieceOnTile = getBoard().getPiece(destination);
                        if (!pieceOnTile.getPieceAlliance().equals(this.getPieceAlliance())) {
                            move = createMove(getBoard(), this, destination, pieceOnTile);
                            getBoard().changeAllianceOnTile(destination, getPieceAlliance());
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

                if (isValidPosition(destinationX, destinationY)) {
                    int destination = getPosition(destinationX, destinationY);

                    // Tile is empty
                    if (!this.getBoard().getTile(destination).isTileOccupied()) {
                        move = createMove(getBoard(), this, destination, null);
                        getBoard().changeAllianceOnTile(destination, getPieceAlliance());
                        legalMovesCache.add(move);
                    }
                    // Tile is occupied
                    else {
                        Piece pieceOnTile = getBoard().getPiece(destination);
                        if (!pieceOnTile.getPieceAlliance().equals(this.getPieceAlliance())) {
                            move = createMove(getBoard(), this, destination, pieceOnTile);
                            getBoard().changeAllianceOnTile(destination, getPieceAlliance());
                            legalMovesCache.add(move);
                        } break;
                    }
                }
            }
        } this.legalMoves = legalMovesCache;
    }
}
