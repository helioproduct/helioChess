package com.chess.core.board;

import com.chess.core.game.Alliance;
import com.chess.core.pieces.Piece;

public class Tile {
    private final int coordinate;
    private Piece pieceOnTile;

    private Alliance allianceOnTile;

    public Tile(int coordinate, Piece piece) {
        this.coordinate = coordinate;
        setPiece(piece);
        this.allianceOnTile = null;
    }

    public int getTileCoordinate() {
        return this.coordinate;
    }

    public boolean isTileOccupied() {
        return this.pieceOnTile != null;
    }

    public Piece getPiece() {
        return this.pieceOnTile;
    }

    public void clearTile() {
        this.pieceOnTile = null;
    }

    public void setPiece(Piece pieceOnTile) {
        this.pieceOnTile = pieceOnTile;
    }

    public void changeAllianceOnTile(Alliance allianceOnTile) {
        this.allianceOnTile = allianceOnTile;
    }

    public Alliance getAllianceOnTile() {
        return this.allianceOnTile;
    }

    public String tileAllianceToString() {
        if (this.allianceOnTile == null) return "-";
        else if (this.allianceOnTile.equals(Alliance.WHITE)) return "W";
        return "B";
    }

    @Override
    public String toString() {
        if (isTileOccupied()) return this.getPiece().toString();
        return "-";
    }
}
