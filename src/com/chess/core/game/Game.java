package com.chess.core.game;

import com.chess.core.GUI.MainFrame;
import com.chess.core.board.Board;
import com.chess.core.board.Tile;
import com.chess.core.game.move.Move;
import com.chess.core.game.player.BlackPlayer;
import com.chess.core.game.player.Player;
import com.chess.core.game.player.WhitePlayer;
import com.chess.core.pieces.Piece;

import static java.util.Objects.isNull;

public class Game {

    // Game - свзяующее звено для игроков, которое предоставляет им доступ к Board
    private final Board board;
    private final WhitePlayer whitePlayer;
    private final BlackPlayer blackPlayer;

    public Side sideToMove;

    private final int hashCode;
    public final MainFrame mainFrame;

    public Piece activePiece;
    public int[] activeTilesPositions;

    public Game() {
        this.hashCode = (int) System.currentTimeMillis() * 31;
        this.board = new Board();
        this.whitePlayer = new WhitePlayer(this);
        this.blackPlayer = new BlackPlayer(this);
        this.sideToMove = Side.WHITE;

        this.mainFrame = new MainFrame();
    }

    public Game(Board board) {
        this.hashCode = (int) System.currentTimeMillis() * 31;
        this.board = board;
        this.whitePlayer = new WhitePlayer(this);
        this.blackPlayer = new BlackPlayer(this);
        this.sideToMove = Side.WHITE;

        this.mainFrame = new MainFrame();
    }

    public void run() {

        mainFrame.init(this);
        Thread GUI = new Thread(mainFrame, "GUI");
        GUI.start();

        // while true временно
        while (true) {
            this.getPlayer(sideToMove).makeMove();
        }
    }

    public Player getPlayer(Side sideOfPlayer) {
        if (sideOfPlayer.equals(Side.WHITE)) return this.whitePlayer;
        return this.blackPlayer;
    }

    public static boolean isMoveLegal(Move move) {
        return move.getMovedPiece().getLegalMoves().contains(move);
    }

    public void movePiece(Move move) {
        this.board.changePiecePosition(move);
        this.mainFrame.movePiece(move.getCurrentPosition(), move.getDestinationPosition());
    }

    private void showLegalMoves(int[] positions) {
        if (this.activeTilesPositions != null) {
            this.mainFrame.removeLegalMoves(activeTilesPositions);
        }
        this.mainFrame.showLegalMoves(positions);
        this.activeTilesPositions = positions;
    }

    public void handleClick(int tilePosition) {
        Piece clickedPiece = getBoard().getPiece(tilePosition);
        showLegalMoves(clickedPiece.getLegalMovesPositions());
    }

    public Board getBoard() {
        return this.board;
    }

    @Override
    public int hashCode() {
        return this.hashCode;
    }
}



