package com.chess.core;

import com.chess.core.GUI.MainFrame;
import com.chess.core.board.Board;
import com.chess.core.game.Alliance;
import com.chess.core.game.Game;
import com.chess.core.move.Move;
import com.chess.core.pieces.Piece;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        game.run();
    }
}
