package com.chess.core.board;

import com.chess.core.game.Alliance;
import com.chess.core.pieces.Piece;

public class Tile {
    private final int coordinate;
    private Piece pieceOnTile;

    private Alliance allianceOnTile;

    /*
        Теперь board
        там надо просчитыать ходы
        и ставить альянс на плитки
    */

    public Tile(int coordinate, Piece piece) {
        this.coordinate = coordinate;
        setPiece(piece);
    }

    public int getTileCoordinate() {
        return this.coordinate;
    }

    public boolean isTileOccupied() {
        return this.pieceOnTile != null;
    }
    public void clearTile() {
        this.pieceOnTile = null;
    }

    public void setPiece(Piece pieceOnTile) {
        this.pieceOnTile = pieceOnTile;
    }
    public void setAllianceOnTile(Alliance allianceOnTile) {
        this.allianceOnTile = allianceOnTile;
    }
    public Alliance getAllianceOnTile() {
        return this.allianceOnTile;
    }

    public Piece getPiece() {
        return this.pieceOnTile;
    }

    @Override
    public String toString() {
        if (isTileOccupied()) return this.getPiece().toString();
        return "-";
    }
}
