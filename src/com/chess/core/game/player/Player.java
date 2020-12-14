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
    private final King playerKing;

    public Player(Game game, Alliance alliance) {
        this.game = game;
        this.alliance = alliance;
        this.playerKing = game.getBoard().getKing(getPlayerAlliance());
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
                    nextMove(move);
                    break;
                }

            } else {
                System.out.println("не трожь чужое");
            }
        }
    }

    // Checks if the player is in check
    public boolean isCheck() {

        int kingPosition = getKing().getPiecePosition();
        Alliance allianceOnKingTile = getBoard().getAllianceOnTile(kingPosition);

        if (allianceOnKingTile == null) return false;
        return allianceOnKingTile.equals(getOpponent().getPlayerAlliance());
    }

    public abstract void nextMove(Move lastMove);

    public abstract Player getOpponent();

    public Alliance getPlayerAlliance() {
        return this.alliance;
    }

    public King getKing() {
        return this.playerKing;
    }

    public Board getBoard() {
        return this.game.getBoard();
    }

    @Override
    public String toString() {
        return this.getPlayerAlliance().toString();
    }
}