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

    public Player(Game game, Side side) {
        this.game = game;
        this.side = side;
        this.playerKing = game.getBoard().getKing(getPlayerAlliance());
    }

    public void makeMove(Piece pieceToMove, int destination) {
        Move move = Move.createMove(pieceToMove, destination, getBoard().getPiece(destination));

        // Player making move
        if (isMoveLegal(move)) {
            this.game.movePiece(move);
            nextMove();
        }
        /*

        Something went wrong
        for e.g. player clicked on the wrong tile or clicked on another Piece
        -> Player should try new move

        handleClick back to Game.class

        */
        else {
            this.game.removeLegalMoves();
            this.game.isFirstClick = true;
        }
    }

    // Checks if the player is in check
    public boolean isCheck() {

        int kingPosition = getKing().getPiecePosition();
        Side sideOnKingTile = getBoard().getAllianceOnTile(kingPosition);

        if (sideOnKingTile == null) return false;
        return sideOnKingTile.equals(getOpponent().getPlayerAlliance());
    }

    public static boolean isMoveLegal(Move move) {
        return move.getMovedPiece().getLegalMoves().contains(move);
    }

    // TODO : CHECK MATE CALCULATION
    // Передает ход другому игроку, просчитавает шах
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