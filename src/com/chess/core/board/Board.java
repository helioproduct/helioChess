package com.chess.core.board;

import com.chess.core.game.Alliance;
import com.chess.core.move.Move;
import com.chess.core.pieces.*;

import static com.chess.core.service.DataVisualizer.visualizeBoard;
import static com.chess.core.service.DataVisualizer.arrayToString;

import java.util.HashSet;

import static com.chess.core.service.Converter.getRowNumber;
import static com.chess.core.service.Converter.getColumnNumber;

public class Board {
    private final Tile[] board;

    private King whiteKing;
    private King blackKing;
    
    private HashSet<Piece> currentWhitePieces = new HashSet<>();
    private HashSet<Piece> currentBlackPieces = new HashSet<>();

    public Board() {
        this.board = createEmptyBoard();
        spawnPieces();
        updateLegalMoves();
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
        if (piece.getPieceAlliance().equals(Alliance.WHITE)) this.currentWhitePieces.add(piece);
        else this.currentBlackPieces.add(piece);
    }

    public void removePiece(int position) {
        final Piece pieceToRemove = this.getPiece(position);
        if (pieceToRemove.getPieceAlliance().equals(Alliance.WHITE)) this.currentWhitePieces.remove(pieceToRemove);
        else this.currentBlackPieces.remove(pieceToRemove);
        board[position].clearTile();
    }

    public HashSet<Piece> getPieces(Alliance alliance) {
        if (alliance.equals(Alliance.WHITE)) return this.currentWhitePieces;
        else return this.currentBlackPieces;
    }

    public King getKing(Alliance alliance) {
        if (alliance.equals(Alliance.WHITE)) return this.whiteKing;
        return this.blackKing;
    }

    public void updateLegalMoves() {
        for (Piece piece : getPieces(Alliance.WHITE)) piece.calculateLegalMoves();
    }

    // Changes the alliance of tiles when calculating legal moves
    public void changeAllianceOnTile(int tilePosition, Alliance alliance) {
        this.board[tilePosition].changeAllianceOnTile(alliance);
    }

    public void changePiecePosition(final Move move) {
        final Piece pieceToMove = move.getMovedPiece();
        pieceToMove.changePiecePosition(move);

        this.removePiece(move.getCurrentPosition());
        this.setPiece(move.getDestinationPosition(), pieceToMove);
    }

    public void spawnPieces() {
        setPiece(59, new Queen(this, 59, Alliance.WHITE));

        // White
/*        setPiece(0, new Rook(this, 0, Alliance.BLACK));
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

        // Black
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
        setPiece(55, new Pawn(this ,55, Alliance.WHITE));*/

        // Saving kings
        this.blackKing = (King) this.getPiece(4);
        this.whiteKing = (King) this.getPiece(60);
    }

    public Tile[] getBoardConfiguration() {
        return this.board;
    }

    public void clearBoard() {
        for (int i = 0; i < 64; i++) this.board[i].clearTile();
        this.currentBlackPieces = new HashSet<>();
        this.currentWhitePieces = new HashSet<>();
    }

    public void visualizeLegalMoves(final int piecePosition) {
        String[][] board = visualizeBoard(this);
        try {
            final Piece piece = this.getTile(piecePosition).getPiece();
            for (Move move : piece.getLegalMoves()) {
                final int x = getColumnNumber(move.getDestinationPosition());
                final int y = getRowNumber(move.getDestinationPosition());
                board[y][x] = "*";
            }
            System.out.println(arrayToString(board));

        } catch (NullPointerException nullPointerException) {
            // Piece is null there no legal moves for it
        }
    }

    public void visualizeTileAlliance() {
        String[][] board = visualizeBoard(this);

        for (Tile tile : getBoardConfiguration()) {
            if (tile.getAllianceOnTile() != null) {
                final int x = getColumnNumber(tile.getTileCoordinate());
                final int y = getRowNumber(tile.getTileCoordinate());
                board[y][x] = tile.tileAllianceToString();
            }
        }
        System.out.println(arrayToString(board));
    }

    @Override
    public String toString() {
        return arrayToString(visualizeBoard(this));
    }
}