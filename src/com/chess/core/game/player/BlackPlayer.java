package com.chess.core.game.player;

import com.chess.core.board.Board;
import com.chess.core.game.Alliance;

public class BlackPlayer extends Player {
    public BlackPlayer(Board board, String name) {
        super(board, Alliance.BLACK, name);
    }

    @Override
    public Player getOpponent() {
        return this.getBoard().getPlayer(Alliance.WHITE);
    }
}
