package com.chess.core.service;

import com.chess.core.board.Board;
import com.chess.core.board.Tile;
import com.chess.core.game.Game;
import com.chess.core.pieces.Piece;
import java.util.HashSet;

public interface GraphicConnector {

    void init(Game game);
    void drawTile(Tile tile);
    void drawBoard(Board board);
    void drawPiece(Piece piece);
    void drawPieces(HashSet<Piece> pieceList);

    void movePiece(int currentPosition, int destination);
    void showLegalMoves(int[] legalMovesPositions);
    void removePiece(int removedPiecePosition);

    void showCheckPopup();
    void showCheckMatePopup();


    void showSelectPiecePopup();
    void timeEndPopup(Game game);
    void pauseGame();

}