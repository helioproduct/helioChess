package com.chess.core.game;

import com.chess.core.GUI.MainFrame;
import com.chess.core.board.Board;
import com.chess.core.game.player.BlackPlayer;
import com.chess.core.game.player.Player;
import com.chess.core.game.player.WhitePlayer;
import com.chess.core.move.Move;

public class Game {

    // Game - свзяующее звено для игроков, которое предоставляет им доступ к Board
    private final Board board;
    private final WhitePlayer whitePlayer;
    private final BlackPlayer blackPlayer;

    public Alliance allianceToMove;

    private final int hashCode;
    public final MainFrame mainFrame;

    private int previousClick = -1;
    private int lastClick = -1;

    public Game() {
        this.hashCode = (int) System.currentTimeMillis() * 31;
        this.board = new Board();
        this.whitePlayer = new WhitePlayer(this);
        this.blackPlayer = new BlackPlayer(this);
        this.allianceToMove = Alliance.WHITE;

        this.mainFrame = new MainFrame();
    }

    public Game(Board board) {
        this.hashCode = (int) System.currentTimeMillis() * 31;
        this.board = board;
        this.whitePlayer = new WhitePlayer(this);
        this.blackPlayer = new BlackPlayer(this);
        this.allianceToMove = Alliance.WHITE;

        this.mainFrame = new MainFrame();
    }

    public void run() {

        mainFrame.init();
        mainFrame.drawBoard(board);
        mainFrame.drawPieces(board.getPieces(Alliance.BLACK));
        mainFrame.drawPieces(board.getPieces(Alliance.WHITE));

        // while is not check mate
        while (true) {
            this.getPlayer(allianceToMove).makeMove();
        }
    }

    public Player getPlayer(Alliance allianceOfPlayer) {
        if (this.allianceToMove.equals(Alliance.WHITE)) return this.whitePlayer;
        return this.blackPlayer;
    }

    public void showLegalMoves(int position) {
        this.mainFrame.showLegalMoves(getBoard().getPiece(position).getLegalMovesPositions());
    }

    public static boolean isMoveLegal(Move move) {
        if (move.getMovedPiece().getLegalMoves().contains(move)) {
            System.out.println("move is legal");
            return true;
        }
        System.out.println("move is not legal");
        return false;
    }

    public void movePiece(Move move) {
        this.board.changePiecePosition(move);
        this.mainFrame.movePiece(move.getCurrentPosition(), move.getDestinationPosition());
    }

    public void handleClick(int tilePosition) {
        this.previousClick = this.lastClick;
        this.lastClick = tilePosition;
    }

    public Board getBoard() {
        return this.board;
    }

    @Override
    public int hashCode() {
        return this.hashCode;
    }
}
