package com.chess.core.game.player;

import com.chess.core.game.Side;
import com.chess.core.game.Game;

public class BlackPlayer extends Player {

    public BlackPlayer(Game game) {
        super(game, Side.BLACK);
    }

    @Override
    public Player getOpponent() {
        return this.game.getPlayer(Side.WHITE);
    }

    @Override
    public void nextMove() {

        // CHECK FOR CHECK
        this.game.getBoard().updateLegalMoves(Side.BLACK);
        if (getOpponent().isCheck()) {
            this.game.GUI.showCheckPopup();
        }

        this.game.sideToMove = Side.WHITE;
        this.game.getBoard().updateLegalMoves(Side.WHITE);
    }
}