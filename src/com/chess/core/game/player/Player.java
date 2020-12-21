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

    public void makeMove() {

    }

    // Checks if the player is in check
    public boolean isCheck() {

        int kingPosition = getKing().getPiecePosition();
        Side sideOnKingTile = getBoard().getAllianceOnTile(kingPosition);

        if (sideOnKingTile == null) return false;
        return sideOnKingTile.equals(getOpponent().getPlayerAlliance());
    }

    public abstract void nextMove(Move lastMove);

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