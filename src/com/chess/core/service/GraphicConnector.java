package com.chess.core.service;

import com.chess.core.board.Board;
import com.chess.core.board.Tile;
import com.chess.core.game.Game;
import com.chess.core.pieces.Piece;

import java.util.ArrayList;

public interface GraphicConnector {

    void init();
    void drawTile(Tile tile);   // tile.getColor();
    void drawBoard(Board board);    // Я так понимаю здесь ты будешь вызывать drawTile();
    void drawPieces(Piece piece);   // Use piece.getColor();
    void drawPieces(ArrayList<Piece> pieceList);

    void movePiece(int currentPosition, int destination);
    void showLegalMoves(int[] legalMovesPositions);
    void removePiece(int removedPiecePosition);

    void showCheckPopup();
    void showCheckMatePopup();
    void timeEndPopup(Game game);
    void pauseGame();

}
