package com.chess.core.pieces;

import com.chess.core.board.Board;
import com.chess.core.game.Side;
import com.chess.core.game.move.Move;

import java.awt.*;
import java.util.HashSet;

public abstract class Piece {

    private final Board board;

    private int position;
    private final Side side;
    private final String pieceName;

    // LegalMoves
    protected HashSet<Move> legalMoves = new HashSet<>(32);

    private int numberOfMoves = 0;
    private final int cachedHashCode;

    // GUI
    private final Color color;

    public Piece(Board board, int position, Side side) {
        this.board = board;
        this.position = position;
        this.side = side;
        this.pieceName = this.getClass().getSimpleName();

        this.cachedHashCode = calculateHashCode();

        if (this.side.equals(Side.WHITE)) this.color = Color.WHITE;
        else this.color = Color.BLACK;
    }

    public abstract void calculateLegalMoves();

    public void changePiecePosition(final Move move) {
        this.position = move.getDestinationPosition();
    }

    public int getNumberOfMoves() {
        return this.numberOfMoves;
    }

    public void increaseNumberOfMoves() {
        this.numberOfMoves += 1;
    }

    public Board getBoard() {
        return this.board;
    }
    public int getPiecePosition() {
        return this.position;
    }
    public String getPieceName() {
        return this.pieceName;
    }
    public Side getPieceSide() {
        return this.side;
    }
    public HashSet<Move> getLegalMoves() {
        return this.legalMoves;
    }

    private int calculateHashCode() {
        int hash = 1;
        if (this.getPieceSide().equals(Side.WHITE)) hash *= 11;
        else hash *= 31;
        hash += this.pieceName.hashCode();
        return hash;
    }

    public int[] getLegalMovesPositions() {
        int[] positions = new int[this.legalMoves.size()];
        int i = 0;
        for (Move move : this.legalMoves) {
            positions[i] = move.getDestinationPosition();
            i += 1;
        }
        return positions;
    }

    @Override
    public String toString() {
        if (getPieceSide().equals(Side.WHITE)) return String.valueOf(this.getPieceName().toCharArray()[0]);
        return String.valueOf(this.getPieceName().toCharArray()[0]).toLowerCase();
    }

    @Override
    public int hashCode() {
        return this.cachedHashCode;
    }

    // GUI
    public Color getColor() {
        return this.color;
    }
}