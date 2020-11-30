package com.chess.core.pieces;

import com.chess.core.board.Board;
import com.chess.core.game.Alliance;
import com.chess.core.move.Move;
import static com.chess.core.service.Converter.getColumnNumber;
import static com.chess.core.service.Converter.getRowNumber;
import static com.chess.core.service.Converter.isValidPosition;
import static com.chess.core.move.Move.createMove;

import java.util.HashSet;

public class Pawn extends Piece {
    public Pawn(Board board, int piecePosition, Alliance alliance) {
        super(board, piecePosition, alliance);
    }

    @Override
    public HashSet<Move> calculateLegalMoves() {
        HashSet<Move> legalMoves = new HashSet<>();

        int classicOffset = 8 * getDirection();
        int jumpOffset = 16 * getDirection();

        // Classic move
        int destination = getPiecePosition() + classicOffset;
        if (isValidPosition(destination)) {
            if (!this.getBoard().getTile(destination).isTileOccupied()) {
                legalMoves.add(createMove(getBoard(), this, destination, null));
            }
        }

        // Jump move
        if (isAbleToJump()) {
            destination = getPiecePosition() + jumpOffset;
            if (isValidPosition(destination)) {
                if (!getBoard().getTile(getPiecePosition() + classicOffset).isTileOccupied()
                        && isValidPosition(destination)) {
                    if (!getBoard().getTile(destination).isTileOccupied()) {
                        legalMoves.add(createMove(getBoard(), this, destination, null));
                    }
                }
            }
        }

        // TODO : Превращение пешки
        // TODO : En-Passant move

        legalMoves.addAll(calculateAttackMoves());

        return legalMoves;
    }

    private HashSet<Move> calculateAttackMoves() {
        HashSet<Move> attackMoves = new HashSet<>();

        int attackOffsetLeft = 9 * getDirection();
        int attackOffsetRight = 7 * getDirection();

        // Left-Attack move
        int destination = getPiecePosition() + attackOffsetLeft;

        if (isValidPosition(destination) && isValidColumn(destination)) {
            if (getBoard().getTile(destination).isTileOccupied()) {
                Piece pieceOnTile = getBoard().getPiece(destination);
                if (!pieceOnTile.getPieceAlliance().equals(this.getPieceAlliance())) {
                    attackMoves.add(createMove(getBoard(), this, destination, pieceOnTile));

                    // Changing Alliance On Tile
                    getBoard().changeAllianceOnTile(destination, getPieceAlliance());
                }
            }
        }

        // Right-Attack move
        destination = getPiecePosition() + attackOffsetRight;
        if (isValidPosition(destination) && isValidColumn(destination)) {
            if (getBoard().getTile(destination).isTileOccupied()) {
                Piece pieceOnTile = getBoard().getPiece(destination);
                if (!pieceOnTile.getPieceAlliance().equals(this.getPieceAlliance())) {
                    attackMoves.add(createMove(getBoard(), this, destination, pieceOnTile));

                    // Changing Alliance On Tile
                    getBoard().changeAllianceOnTile(destination, getPieceAlliance());
                }
            }
        }

        return attackMoves;
    }

    private int getDirection() {
        if (getPieceAlliance().equals(Alliance.WHITE)) return -1;
        else return 1;
    }

    private boolean isAbleToJump() {
        if (getPieceAlliance().equals(Alliance.WHITE)) return getRowNumber(getPiecePosition()) == 6;
        return getRowNumber(getPiecePosition()) == 1;
    }

    private boolean isValidColumn(int candidateCoordinate) {
        int currentColumn = getColumnNumber(this.getPiecePosition());
        int candidateColumn = getColumnNumber(candidateCoordinate);
        return Math.abs(candidateColumn - currentColumn) <= 1;
    }
}
