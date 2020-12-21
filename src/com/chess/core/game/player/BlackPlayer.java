package com.chess.core.game.player;

import com.chess.core.game.Side;
import com.chess.core.game.Game;
import com.chess.core.game.move.Move;

public class BlackPlayer extends Player {

    public BlackPlayer(Game game) {
        super(game, Side.BLACK);
    }

    @Override
    public Player getOpponent() {
        return this.game.getPlayer(Side.WHITE);
    }

    @Override
    public void nextMove(Move lastMove) {

        // CHECK FOR CHECK
        this.game.getBoard().updateLegalMoves(Side.BLACK);

        if (getOpponent().isCheck()) {
            System.out.println("CHECK");
        } else {
            System.out.println("NOT CHECK");
        }

        this.game.sideToMove = Side.WHITE;
        this.game.getBoard().updateLegalMoves(Side.WHITE);
    }
}