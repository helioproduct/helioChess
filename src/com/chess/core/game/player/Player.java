package com.chess.core.game.player;

import com.chess.core.board.Board;
import com.chess.core.game.Side;
import com.chess.core.game.Game;
import com.chess.core.game.move.Move;
import com.chess.core.pieces.King;
import com.chess.core.pieces.Piece;

public abstract class Player {

    public final Game game;
    private final Side side;
    private final King playerKing;

    public boolean isCheck;

    public Player(Game game, Side side) {
        this.game = game;
        this.side = side;
        this.playerKing = game.getBoard().getKing(getPlayerAlliance());
        this.isCheck = false;
    }

    public void makeMove(Piece pieceToMove, int destination) {
        if (this.isCheck()) {
            System.out.println("Do something with that");
        } else {
            Move move = Move.createMove(pieceToMove, destination, getBoard().getPiece(destination));
            if (isMoveLegal(move)) {
                this.game.movePiece(move);
                nextMove();
            } else {
                this.game.removeLegalMoves();
                this.game.isFirstClick = true;
            }
        }
    }

    private void handleCheck() {

    }

    public static boolean isMoveLegal(Move move) {
        return move.getMovedPiece().getLegalMoves().contains(move);
    }

    public boolean isCheck() {
        return this.isCheck;
    }

    public abstract void nextMove();

    public abstract Player getOpponent();

    public Side getPlayerAlliance() {
        return this.side;
    }

    public King getKing() {
        return this.playerKing;
    }

    public Board getBoard() {
        return this.game.getBoard();
    }

    @Override
    public String toString() {
        return this.getPlayerAlliance().toString();
    }
}