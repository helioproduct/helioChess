package com.chess.core.game.player;

import com.chess.core.game.Alliance;
import com.chess.core.game.Game;
import com.chess.core.move.Move;
import com.chess.core.pieces.Piece;

public class BlackPlayer extends Player {

    public BlackPlayer(Game game) {
        super(game, Alliance.BLACK);
    }

    @Override
    public Player getOpponent() {
        return this.game.getPlayer(Alliance.WHITE);
    }

    @Override
    public void nextMove(Move lastMove) {

        // CHECK FOR CHECK
        this.game.getBoard().updateLegalMoves(Alliance.BLACK);

        if (getOpponent().isCheck()) {
            System.out.println("CHECK");
        } else {
            System.out.println("NOT CHECK");
        }

        this.game.allianceToMove = Alliance.WHITE;
        this.game.getBoard().updateLegalMoves(Alliance.WHITE);
    }
}