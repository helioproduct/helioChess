package com.chess.core.game.player;

import com.chess.core.board.Tile;
import com.chess.core.game.Alliance;
import com.chess.core.game.Game;
import com.chess.core.move.Move;
import com.chess.core.pieces.King;
import com.chess.core.pieces.Piece;

public class WhitePlayer extends Player {

    public WhitePlayer(Game game) {
        super(game, Alliance.WHITE);
    }

    @Override
    public Player getOpponent() {
        return this.game.getPlayer(Alliance.BLACK);
    }

    @Override
    public void nextMove(Move lastMove) {

        // CHECK FOR CHECK;
        this.game.getBoard().updateLegalMoves(Alliance.WHITE);

        if (getOpponent().isCheck()) {
            System.out.println("CHECK");
        } else {
            System.out.println("NOT CHECK");
        }

        this.game.allianceToMove = Alliance.BLACK;
        this.game.getBoard().updateLegalMoves(Alliance.BLACK);
    }
}