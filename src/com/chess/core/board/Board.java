package com.chess.core.board;

import com.chess.core.game.Alliance;
import com.chess.core.game.player.BlackPlayer;
import com.chess.core.game.player.Player;
import com.chess.core.game.player.WhitePLayer;
import com.chess.core.move.Move;
import com.chess.core.pieces.*;

import static com.chess.core.service.DataVisualizer.visualizeBoard;
import static com.chess.core.service.DataVisualizer.arrayToString;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static com.chess.core.service.Converter.getRowNumber;
import static com.chess.core.service.Converter.getColumnNumber;

public class Board {
    private Tile[] board;

    private List<Piece> whitePieces = new ArrayList<>();
    private List<Piece> blackPieces = new ArrayList<>();

    private final Player whitePlayer;
    private final Player blackPlayer;

    public Board() {
        this.board = createEmptyBoard();
        spawnPieces();

        this.whitePlayer = new WhitePLayer(this, "WhitePLayer");
        this.blackPlayer = new BlackPlayer(this, "BlackPlayer");

        // Board init cache
        try {
            File history = new File("src/com/chess/core/game/history");
            PrintStream printStream = new PrintStream(history);
            printStream.println("   Board has been created");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Tile[] createEmptyBoard() {
        Tile[] tiles = new Tile[64];
        for (int i = 0; i < 64; i++) tiles[i] = new Tile(i, null);
        return tiles;
    }

    public Tile getTile(int position) {
        return this.board[position];
    }
    public Piece getPiece(int position)  {
        return this.board[position].getPiece();
    }

    public void setPiece(int position, Piece piece) {
        this.board[position].setPiece(piece);
        if (piece.getPieceAlliance().equals(Alliance.WHITE)) whitePieces.add(piece);
        else blackPieces.add(piece);
    }
    public void removePiece(int position) {
        getPieces(this.getPiece(position).getPieceAlliance()).remove(this.getPiece(position));
        board[position].clearTile();
    }

    public List<Piece> getPieces(Alliance alliance) {
        if (alliance.equals(Alliance.WHITE)) return whitePieces;
        else return blackPieces;
    }

    public Player getPlayer(Alliance alliance) {
        if (alliance.equals(Alliance.WHITE)) return this.whitePlayer;
        else return this.blackPlayer;
    }

    public void changePiecePosition(final Move move) {
        final Piece pieceToMove = move.getMovedPiece();
        pieceToMove.changePiecePosition(move);

        this.removePiece(move.getCurrentPosition());
        this.setPiece(move.getDestinationPosition(), pieceToMove);
    }

    public void spawnPieces() {
        setPiece(0, new Rook(this, 0, Alliance.BLACK));
        setPiece(1, new Knight(this, 1, Alliance.BLACK));
        setPiece(2, new Bishop(this, 2, Alliance.BLACK));
        setPiece(3, new Queen(this, 3, Alliance.BLACK));
        setPiece(4, new King(this, 4, Alliance.BLACK));
        setPiece(5, new Bishop(this, 5, Alliance.BLACK));
        setPiece(6, new Knight(this, 6, Alliance.BLACK));
        setPiece(7, new Rook(this, 7, Alliance.BLACK));
        setPiece(8, new Pawn(this, 8, Alliance.BLACK));
        setPiece(9, new Pawn(this, 9, Alliance.BLACK));
        setPiece(10, new Pawn(this, 10, Alliance.BLACK));
        setPiece(11, new Pawn(this, 11, Alliance.BLACK));
        setPiece(12, new Pawn(this, 12, Alliance.BLACK));
        setPiece(13, new Pawn(this, 13, Alliance.BLACK));
        setPiece(14, new Pawn(this, 14, Alliance.BLACK));
        setPiece(15, new Pawn(this, 15, Alliance.BLACK));

        setPiece(56, new Rook(this, 56, Alliance.WHITE));
        setPiece(57, new Knight(this, 57, Alliance.WHITE));
        setPiece(58, new Bishop(this, 58, Alliance.WHITE));
        setPiece(59, new Queen(this, 59, Alliance.WHITE));
        setPiece(60, new King(this, 60, Alliance.WHITE));
        setPiece(61, new Bishop(this, 61, Alliance.WHITE));
        setPiece(62, new Knight(this, 62, Alliance.WHITE));
        setPiece(63, new Rook(this, 63, Alliance.WHITE));
        setPiece(48, new Pawn(this, 48, Alliance.WHITE));
        setPiece(49, new Pawn(this, 49, Alliance.WHITE));
        setPiece(50, new Pawn(this, 50, Alliance.WHITE));
        setPiece(51, new Pawn(this, 51, Alliance.WHITE));
        setPiece(52, new Pawn(this, 52, Alliance.WHITE));
        setPiece(53, new Pawn(this, 53, Alliance.WHITE));
        setPiece(54, new Pawn(this, 54, Alliance.WHITE));
        setPiece(55, new Pawn(this ,55, Alliance.WHITE));
    }

    public Tile[] getBoardConfig() {
        return this.board;
    }
    public void clearBoard() {
        for (int i = 0; i < 64; i++) {
            this.board[i].clearTile();
        }
    }

    public List<Move> calculateLegalMoves(Alliance alliance) {
        List<Move> legalMoves = new ArrayList<>();
        for (Piece piece : this.getPieces(alliance)) {
            legalMoves.addAll(piece.calculateLegalMoves());
        }
        return legalMoves;
    }

    public void visualizeLegalMoves(final int piecePosition) {
        String[][] board = visualizeBoard(this);
        final Piece piece = this.getTile(piecePosition).getPiece();
        List<Move> legalMoves = piece.calculateLegalMoves();
        for (Move move : legalMoves) {
            final int x = getColumnNumber(move.getDestinationPosition());
            final int y = getRowNumber(move.getDestinationPosition());
            board[y][x] = "*";
        }
        System.out.println(arrayToString(board));
    }

    @Override
    public String toString() {
        return arrayToString(visualizeBoard(this));
    }
}
