package com.chess.core.pieces;

import com.chess.core.board.Board;
import com.chess.core.game.Side;
import com.chess.core.game.move.Move;

import java.util.HashSet;

import static com.chess.core.service.Converter.getColumnNumber;
import static com.chess.core.service.Converter.getRowNumber;
import static com.chess.core.service.Converter.isValidPosition;
import static com.chess.core.game.move.Move.createMove;

public class Pawn extends Piece {
    public Pawn(Board board, int piecePosition, Side side) {
        super(board, piecePosition, side);
    }

    @Override
    public void calculateLegalMoves() {
        HashSet<Move> legalMovesCache = new HashSet<>(6);

        int classicOffset = 8 * getDirection();
        int jumpOffset = 16 * getDirection();

        // Classic move
        int destination = getPiecePosition() + classicOffset;
        if (isValidPosition(destination)) {
            if (!this.getBoard().getTile(destination).isTileOccupied()) {
                legalMovesCache.add(createMove(this, destination, null));
            }
        }

        // Jump move
        if (isAbleToJump()) {
            destination = getPiecePosition() + jumpOffset;
            if (isValidPosition(destination)) {
                if (!getBoard().getTile(getPiecePosition() + classicOffset).isTileOccupied()
                        && isValidPosition(destination)) {
                    if (!getBoard().getTile(destination).isTileOccupied()) {
                        legalMovesCache.add(createMove( this, destination, null));
                    }
                }
            }
        }

        // TODO : Превращение пешки
        // TODO : En-Passant move

        legalMovesCache.addAll(calculateAttackMoves());
        this.legalMoves = legalMovesCache;
    }

    private HashSet<Move> calculateAttackMoves() {
        HashSet<Move> attackMoveCache = new HashSet<>(2);
        int attackOffsetLeft = 9 * getDirection();
        int attackOffsetRight = 7 * getDirection();

        // Left-Attack move
        int destination = getPiecePosition() + attackOffsetLeft;

        if (isValidPosition(destination) && isValidColumn(destination)) {
            if (getBoard().getTile(destination).isTileOccupied()) {
                Piece pieceOnTile = getBoard().getPiece(destination);
                if (!pieceOnTile.getPieceSide().equals(this.getPieceSide())) {
                    if (pieceOnTile.isKing()) {
                        this.game.setCheck(pieceOnTile.getPieceSide());
                    }
                    attackMoveCache.add(createMove(this, destination, pieceOnTile));
                }
            }
        }

        // Right-Attack move
        destination = getPiecePosition() + attackOffsetRight;
        if (isValidPosition(destination) && isValidColumn(destination)) {
            if (getBoard().getTile(destination).isTileOccupied()) {
                Piece pieceOnTile = getBoard().getPiece(destination);
                if (!pieceOnTile.getPieceSide().equals(this.getPieceSide())) {
                    if (pieceOnTile.isKing()) {
                        this.game.setCheck(pieceOnTile.getPieceSide());
                    }
                    attackMoveCache.add(createMove( this, destination, pieceOnTile));
                }
            }
        }
        return attackMoveCache;
    }

    private int getDirection() {
        if (getPieceSide().equals(Side.WHITE)) return -1;
        else return 1;
    }

    private boolean isAbleToJump() {
        if (getPieceSide().equals(Side.WHITE)) return getRowNumber(getPiecePosition()) == 6;
        return getRowNumber(getPiecePosition()) == 1;
    }

    private boolean isValidColumn(int candidateCoordinate) {
        int currentColumn = getColumnNumber(this.getPiecePosition());
        int candidateColumn = getColumnNumber(candidateCoordinate);
        return Math.abs(candidateColumn - currentColumn) <= 1;
    }
}
