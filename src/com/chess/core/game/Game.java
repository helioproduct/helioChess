package com.chess.core.game;

import com.chess.core.GUI.GUIThread;
import com.chess.core.board.Board;
import com.chess.core.game.move.Move;
import com.chess.core.game.player.BlackPlayer;
import com.chess.core.game.player.Player;
import com.chess.core.game.player.WhitePlayer;
import com.chess.core.pieces.Piece;

import static java.util.Objects.isNull;

public class Game {

    private final Board board;
    private final WhitePlayer whitePlayer;
    private final BlackPlayer blackPlayer;

    public Side sideToMove;

    private final int hashCode;

    public GUIThread GUI;

    public boolean isFirstClick = true;
    private Piece activePiece;

    public Piece checkBy;

    public Game() {
        this.hashCode = (int) System.currentTimeMillis() * 31;
        this.board = new Board(this);
        this.whitePlayer = new WhitePlayer(this);
        this.blackPlayer = new BlackPlayer(this);
        this.sideToMove = Side.WHITE;
        this.GUI = new GUIThread(this);
    }

    public void run() {
        GUI.start();
    }

    public Player getPlayer(Side sideOfPlayer) {
        if (sideOfPlayer.equals(Side.WHITE)) return this.whitePlayer;
        return this.blackPlayer;
    }

    public void setCheck(Side sideOnCheck) {
        this.getPlayer(sideOnCheck).isCheck = true;
    }

    public void setPieceCheckBy(Piece piece) {
        this.checkBy = piece;
    }

    public void movePiece(Move move) {
        this.board.changePiecePosition(move);
        this.GUI.movePiece(move);
        this.isFirstClick = true;
        this.GUI.removeLegalMoves();
    }

    public void handleClick(int tilePosition) {
        // Show legal moves if click is first
        if (isFirstClick) {
            Piece clickedPiece = getBoard().getPiece(tilePosition);
            if (!isNull(clickedPiece) && clickedPiece.getPieceSide().equals(sideToMove)) {
                GUI.showLegalMoves(clickedPiece);
                activePiece = clickedPiece;
                isFirstClick = false;
            }
        }

        // Forward for processing to the current player
        else {
            getPlayer(sideToMove).makeMove(this.activePiece, tilePosition);
        }
    }

    public Board getBoard() {
        return this.board;
    }

    @Override
    public int hashCode() {
        return this.hashCode;
    }
}