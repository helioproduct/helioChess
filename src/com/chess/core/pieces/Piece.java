package com.chess.core.pieces;

import com.chess.core.board.Board;
import com.chess.core.game.Game;
import com.chess.core.game.Side;
import com.chess.core.game.move.Move;

import java.awt.*;
import java.util.HashSet;

public abstract class Piece {

    protected final Game game;
    private final Board board;

    private int position;
    private final Side side;

    protected HashSet<Move> legalMoves = new HashSet<>(32);

    private int movesAmount = 0;
    private final int cachedHashCode;

    private final Color color;

    public Piece(Board board, int position, Side side) {
        this.board = board;
        this.game = board.game;

        this.position = position;
        this.side = side;

        this.cachedHashCode = calculateHashCode();

        if (this.side.equals(Side.WHITE)) this.color = Color.WHITE;
        else this.color = Color.BLACK;
    }

    public abstract void calculateLegalMoves();

    public void changePiecePosition(final Move move) {
        this.position = move.getDestinationPosition();
    }

    public int getMovesAmount() {
        return this.movesAmount;
    }

    public void increaseMovesAmount() {
        this.movesAmount += 1;
    }

    public Board getBoard() {
        return this.board;
    }

    public int getPiecePosition() {
        return this.position;
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
        hash += this.getClass().getSimpleName().hashCode();
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
        if (getPieceSide().equals(Side.WHITE)) {
            return String.valueOf(getClass().getSimpleName().toCharArray()[0]);
        }
        return String.valueOf(getClass().getSimpleName().toCharArray()[0]).toLowerCase();
    }

    @Override
    public int hashCode() {
        return this.cachedHashCode;
    }

    public Color getColor() {
        return this.color;
    }

    public boolean isKing() {
        return false;
    }
}