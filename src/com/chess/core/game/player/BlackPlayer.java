package com.chess.core.game.player;

import com.chess.core.game.Alliance;
import com.chess.core.game.Game;

public class BlackPlayer extends Player {

    public BlackPlayer(Game game) {
        super(game, Alliance.BLACK, "BlackPlayer");
    }

    @Override
    public Player getOpponent() {
        return this.game.getPlayer(Alliance.WHITE);
    }

    @Override
    public void nextMove() {
        this.game.allianceToMove = Alliance.WHITE;
        this.game.getBoard().updateLegalMoves(Alliance.WHITE);
    }
}