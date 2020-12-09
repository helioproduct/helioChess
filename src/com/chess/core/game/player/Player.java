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
        Scanner scanner = new Scanner(System.in);

        int currentPosition = scanner.nextInt();
        int destinationPosition = scanner.nextInt();

        Piece movedPiece = getBoard().getPiece(currentPosition);
        Piece attackedPiece = getBoard().getPiece(destinationPosition);

        if (movedPiece.getPieceAlliance().equals(this.getPlayerAlliance())) {
            Move move = Move.createMove(getBoard(), movedPiece, destinationPosition, attackedPiece);
            if (isMoveLegal(move)) this.getBoard().changePiecePosition(move);
            else {
                System.out.println("move is not legal");
            }
        } else {
            System.out.println("go fuck yourself");
        }

        this.nextMove();
        System.out.println(getBoard());
    }

    private boolean isMoveLegal(Move move) {
        return move.getMovedPiece().getLegalMoves().contains(move);
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