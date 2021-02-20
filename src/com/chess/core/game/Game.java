package com.chess.core.game;

import com.chess.core.GUI.MainFrame;
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

    public boolean isFirstClick = true;
    private Piece activePiece;

    // TODO : Delete this bullshit
    public Piece checkBy;

    private final int hashCode;

    public Game() {
        this.hashCode = (int) System.currentTimeMillis() * 31;
        this.board = new Board(this);
        this.whitePlayer = new WhitePlayer(this);
        this.blackPlayer = new BlackPlayer(this);
        this.sideToMove = Side.WHITE;

        this.mainFrame = new MainFrame();
        this.mainFrame.init(this);
    }

    public void run() {
        this.mainFrame.drawBoard(getBoard());
        this.mainFrame.drawPieces(getBoard().getPieces(Side.BLACK));
        this.mainFrame.drawPieces(getBoard().getPieces(Side.WHITE));
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

        this.mainFrame.movePiece(move.getCurrentPosition(), move.getDestinationPosition());
        this.isFirstClick = true;
        removeLegalMoves();
    }

    public void handleClick(int tilePosition) {
        // Show legal moves if click is first
        if (isFirstClick) {
            Piece clickedPiece = getBoard().getPiece(tilePosition);
            if (!isNull(clickedPiece) && clickedPiece.getPieceSide().equals(sideToMove)) {
                showLegalMoves(clickedPiece);
                activePiece = clickedPiece;
                isFirstClick = false;
            }
        }
        // Forward for processing to the current player
        else getPlayer(sideToMove).makeMove(this.activePiece, tilePosition);
    }

    public Board getBoard() {
        return this.board;
    }

    @Override
    public int hashCode() {
        return this.hashCode;
    }


    // GUI Safe methods!

    private final MainFrame mainFrame;
    private int[] activeTilesPositions;

    public void showLegalMoves(Piece piece) {
        if (this.activeTilesPositions != null) removeLegalMoves();
        this.mainFrame.showLegalMoves(piece.getLegalMovesPositions());
        this.activeTilesPositions = piece.getLegalMovesPositions();
    }

    public void removeLegalMoves() {
        this.mainFrame.removeLegalMoves(this.activeTilesPositions);
    }

    /*
    Messages
     */

    public void showCheckPopup(Piece threat) {
        this.mainFrame.showCheckPopup(threat.getClass().getSimpleName());
    }

    public void showCheckMatePopup(Player player) {
        this.mainFrame.showCheckMatePopup(player.toString());
    }

    // Shows custom Message
    public void showMessage(String customMessage) {
        this.mainFrame.showCustomPopup(customMessage);
    }

}