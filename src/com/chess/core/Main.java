package com.chess.core;

import com.chess.core.board.Board;
import com.chess.core.game.Alliance;
import com.chess.core.game.player.Player;
import com.chess.core.move.Move;
import com.chess.core.pieces.*;
import com.chess.core.service.Converter;

import java.util.ArrayList;
import java.util.List;

import static com.chess.core.move.Move.createMove;

public class Main {
    public static void main(String[] args) {

        Board board = new Board();
        Move move = createMove(board, board.getPiece(48), 40, null);

        Player white = board.getPlayer(Alliance.WHITE);
        white.makeMove(48, 24);
        
        System.out.println(board);
    }
}