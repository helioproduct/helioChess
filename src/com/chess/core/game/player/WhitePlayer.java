package com.chess.core.game.player;

import com.chess.core.game.Side;
import com.chess.core.game.Game;
import com.chess.core.game.move.Move;

public class WhitePlayer extends Player {

    public WhitePlayer(Game game) {
        super(game, Side.WHITE);
    }

    @Override
    public Player getOpponent() {
        return this.game.getPlayer(Side.BLACK);
    }

    @Override
    public void nextMove(Move lastMove) {

        // CHECK FOR CHECK;
        this.game.getBoard().updateLegalMoves(Side.WHITE);

        if (getOpponent().isCheck()) {
            System.out.println("CHECK");
        } else {
            System.out.println("NOT CHECK");
        }

        this.game.sideToMove = Side.BLACK;
        this.game.getBoard().updateLegalMoves(Side.BLACK);
    }
}