package com.chess.core.pieces;

import com.chess.core.board.Board;
import com.chess.core.game.Alliance;
import com.chess.core.move.Move;

import java.awt.*;
import java.util.HashSet;
import java.util.Iterator;

public abstract class Piece {

    private final Board board;

    private int position;
    private final Alliance alliance;
    private final String pieceName;

    // LegalMoves
    protected HashSet<Move> legalMoves = new HashSet<>(32);

    private final int cachedHashCode;

    // GUI
    private final Color color;

    public Piece(Board board, int position, Alliance alliance) {
        this.board = board;
        this.position = position;
        this.alliance = alliance;
        this.pieceName = this.getClass().getSimpleName();

        this.cachedHashCode = calculateHashCode();

        if (this.alliance.equals(Alliance.WHITE)) this.color = Color.WHITE;
        else this.color = Color.BLACK;
    }

    public abstract void calculateLegalMoves();

    public void changePiecePosition(final Move move) {
        this.position = move.getDestinationPosition();
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
    public Alliance getPieceAlliance() {
        return this.alliance;
    }
    public HashSet<Move> getLegalMoves() {
        return this.legalMoves;
    }

    private int calculateHashCode() {
        int result = 1;
        result = 31 * result + getPieceAlliance().hashCode();
        result = 31 * result + getPieceName().hashCode();
        return result;
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
    public int hashCode() {
        return this.cachedHashCode;
    }

    @Override
    public String toString() {
        if (getPieceAlliance().equals(Alliance.WHITE)) return String.valueOf(this.getPieceName().toCharArray()[0]);
        return String.valueOf(this.getPieceName().toCharArray()[0]).toLowerCase();
    }

    // GUI
    public Color getColor() {
        return this.color;
    }
}
