package com.chess.core.game.player;

import com.chess.core.board.Board;
import com.chess.core.game.Alliance;
import com.chess.core.game.Game;
import com.chess.core.move.Move;
import com.chess.core.pieces.King;
import com.chess.core.pieces.Piece;

import static com.chess.core.game.Game.isMoveLegal;

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
        while (true) {
            int currentPosition = scanner.nextInt();

            Piece pieceToMove = this.getBoard().getPiece(currentPosition);
            if (pieceToMove.getPieceAlliance().equals(this.game.allianceToMove)) {
                int[] moves = this.getBoard().getPiece(currentPosition).getLegalMovesPositions();
                System.out.println(Arrays.toString(moves));
                this.game.showLegalMoves(currentPosition);
                int destinationPosition = scanner.nextInt();

                Piece attackedPiece = getBoard().getPiece(destinationPosition);
                Move move = Move.createMove(getBoard(), pieceToMove, destinationPosition, attackedPiece);
                if (isMoveLegal(move)) {
                    this.game.movePiece(move);
                    nextMove();
                    break;
                }

            } else {
                System.out.println("не трожь чужое");
            }
        }
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