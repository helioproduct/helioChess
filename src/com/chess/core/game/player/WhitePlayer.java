package com.chess.core.game.player;

import com.chess.core.game.Alliance;
import com.chess.core.game.Game;

public class WhitePlayer extends Player {

    public WhitePlayer(Game game) {
        super(game, Alliance.WHITE, "WhitePlayer");
    }

    @Override
    public Player getOpponent() {
        return this.game.getPlayer(Alliance.BLACK);
    }

    @Override
    public void nextMove() {
        this.game.allianceToMove = Alliance.BLACK;
        this.game.getBoard().updateLegalMoves(Alliance.BLACK);
    }
}