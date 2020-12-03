package com.chess.core.game.player;

import com.chess.core.board.Board;
import com.chess.core.game.Alliance;
import com.chess.core.game.Game;
import com.chess.core.move.Move;
import com.chess.core.pieces.King;
import com.chess.core.pieces.Piece;

import java.util.*;

public abstract class Player {

    public final Game game;
    private final Alliance alliance;
    private final String playerName;

    public Player(Game game, Alliance alliance, String playerName) {
        this.game = game;
        this.alliance = alliance;
        this.playerName = playerName;
    }

    public void makeMove() {

        // something happens

        // Checking for check and or check and mate or something else

        this.nextMove();
    }

    public void updateLegalMoves() {
        List<Move> legalMoves = new ArrayList<>();
    }

    public abstract void nextMove();

    public abstract Player getOpponent();

    public Alliance getPlayerAlliance() {
        return this.alliance;
    }

    public King getKing() {
        return null;
    }

    public Board getBoard() {
        return this.game.getBoard();
    }

    public String getPlayerName() {
        return this.playerName;
    }
}