package com.chess.core.game.player;

import com.chess.core.game.Side;
import com.chess.core.game.Game;

public class WhitePlayer extends Player {

    public WhitePlayer(Game game) {
        super(game, Side.WHITE);
    }

    @Override
    public Player getOpponent() {
        return this.game.getPlayer(Side.BLACK);
    }

    @Override
    public void nextMove() {

        // CHECK FOR CHECK;
        this.game.getBoard().updateLegalMoves(Side.WHITE);

        if (getOpponent().isCheck()) {
            System.out.println("CHECK");
            System.out.println();
        } else {
            System.out.println("NOT CHECK");
            System.out.println();
        }

        this.game.sideToMove = Side.BLACK;
        this.game.getBoard().updateLegalMoves(Side.BLACK);
    }
}