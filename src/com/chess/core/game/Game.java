package com.chess.core.game;

import com.chess.core.GUI.MainFrame;
import com.chess.core.board.Board;
import com.chess.core.game.player.BlackPlayer;
import com.chess.core.game.player.Player;
import com.chess.core.game.player.WhitePlayer;

public class Game {

    // Game - свзяующее звено для игроков, которое предоставляет им доступ к Board
    private final Board board;
    private final WhitePlayer whitePlayer;
    private final BlackPlayer blackPlayer;

    public Alliance allianceToMove;

    private final int hashCode;

    public Game() {
        this.hashCode = (int) System.currentTimeMillis() * 31;
        this.board = new Board();
        this.whitePlayer = new WhitePlayer(this);
        this.blackPlayer = new BlackPlayer(this);
        this.allianceToMove = Alliance.WHITE;
    }

    public void run() {
        // while is not check mate

        MainFrame mainFrame = new MainFrame();
        mainFrame.drawBoard(this.getBoard());
        mainFrame.drawPieces(this.getBoard().getPieces(Alliance.BLACK));
        mainFrame.drawPieces(this.getBoard().getPieces(Alliance.WHITE));
        mainFrame.init();

        while (true) {
            this.getPlayer(allianceToMove).makeMove();
        }
    }

    public Player getPlayer(Alliance allianceOfPlayer) {
        if (this.allianceToMove.equals(Alliance.WHITE)) return this.whitePlayer;
        return this.blackPlayer;
    }

    public Board getBoard() {
        return this.board;
    }

    @Override
    public int hashCode() {
        return this.hashCode;
    }
}
