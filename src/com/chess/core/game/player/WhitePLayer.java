package com.chess.core.game.player;

import com.chess.core.board.Board;
import com.chess.core.game.Alliance;
import com.chess.core.game.player.Player;

public class WhitePLayer extends Player {
    public WhitePLayer(Board board, String name) {
        super(board, Alliance.WHITE, name);
    }

    @Override
    public Player getOpponent() {
        return this.getBoard().getPlayer(Alliance.BLACK);
    }
}
