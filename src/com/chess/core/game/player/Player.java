package com.chess.core.game.player;

import com.chess.core.board.Board;
import com.chess.core.game.Alliance;
import com.chess.core.game.Game;
import com.chess.core.move.Move;
import com.chess.core.pieces.King;
import java.util.List;
import java.util.Scanner;

public abstract class Player {

    public final Game game;
    private final Alliance alliance;
    private final String playerName;

    private List<Move> legalMoves;
    private King king;

    public Player(Game game, Alliance alliance, String playerName) {
        this.game = game;
        this.alliance = alliance;
        this.playerName = playerName;
        // Saving king
        this.king = getBoard().getKing(getPlayerAlliance());
    }

    public void makeMove() {
        // this.game.getBoard().changePiecePosition
        // something happens
        Scanner scanner = new Scanner(System.in);
        String scannedString = scanner.nextLine();
        System.out.println(scannedString);

        // Checking for check and or check and mate or something else

        this.nextMove();
    }

    public abstract void nextMove();

    public abstract Player getOpponent();

    public Alliance getPlayerAlliance() {
        return this.alliance;
    }

    public King getKing() {
        return this.king;
    }

    public Board getBoard() {
        return this.game.getBoard();
    }

    public String getPlayerName() {
        return this.playerName;
    }
}