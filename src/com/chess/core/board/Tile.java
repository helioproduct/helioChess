package com.chess.core.board;

import com.chess.core.game.Side;
import com.chess.core.pieces.Piece;

import java.awt.*;
import com.chess.core.GUI.ColorPalette;
import com.chess.core.service.Converter;

public class Tile {
    private final int coordinate;
    private Piece pieceOnTile;

    private Side sideOnTile;
    private final Color color, legalColor;

    public Tile(int coordinate, Piece piece) {
        this.coordinate = coordinate;

        if ((Converter.getColumnNumber(coordinate) + Converter.getRowNumber(coordinate)) % 2 == 0) {
            this.color = ColorPalette.TILE_LIGHT;
            this.legalColor = ColorPalette.TILE_LEGAL_LIGHT;
        }
        else {
            this.color = ColorPalette.TILE_DARK;
            this.legalColor = ColorPalette.TILE_LEGAL_DARK;
        }

        setPiece(piece);
        this.sideOnTile = null;
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

    public void changeAllianceOnTile(Side sideOnTile) {
        this.sideOnTile = sideOnTile;
    }

    public Side getSideOnTile() {
        return this.sideOnTile;
    }

    public String tileAllianceToString() {
        if (this.sideOnTile == null) return "-";
        else if (this.sideOnTile.equals(Side.WHITE)) return "W";
        return "B";
    }

    @Override
    public String toString() {
        if (isTileOccupied()) return this.getPiece().toString();
        return "-";
    }

    // GUI
    public Color getColor() {
        return this.color;
    }

    public Color getLegalColor() {
        return this.legalColor;
    }
}
