package com.chess.core.game;

import com.chess.core.GUI.GUIThread;
import com.chess.core.board.Board;
import com.chess.core.game.move.Move;
import com.chess.core.game.player.BlackPlayer;
import com.chess.core.game.player.Player;
import com.chess.core.game.player.WhitePlayer;
import com.chess.core.pieces.Piece;

import java.util.Arrays;

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
        getPlayer(sideOnCheck).isCheck = true;
    }

    public void movePiece(Move move) {
        board.changePiecePosition(move);
        GUI.movePiece(move);
        isFirstClick = true;
        GUI.removeLegalMoves();
    }

    public void removeLegalMoves() {
        this.GUI.removeLegalMoves();
    }

    public void handleClick(int tilePosition) {

        // Показать активные клетки, если нажатие первое
        if (isFirstClick) {
            Piece clickedPiece = getBoard().getPiece(tilePosition);
            if (!isNull(clickedPiece) && clickedPiece.getPieceSide().equals(sideToMove)) {

                System.out.println(clickedPiece);
                for (Move move : clickedPiece.getLegalMoves()) System.out.println(move);
                System.out.println();

                GUI.showLegalMoves(clickedPiece);
                activePiece = clickedPiece;
                // Обнулить значение
                isFirstClick = false;
            }
        }

        // Пробросить на обработку текущему игроку
        else {
            getPlayer(sideToMove).makeMove(activePiece, tilePosition);
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

