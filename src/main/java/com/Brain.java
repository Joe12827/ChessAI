package com;

import java.util.ArrayList;

public class Brain {
    boolean white;

    Brain (boolean white) {
        this.white = white;
    }

    public ArrayList<Move> findAllMoves(Board board) {
        ArrayList<Move> movesTotal = new ArrayList<>();
        ArrayList<Move> moves = new ArrayList<>();
        for (int row = 7; row >= 0; row--) {
            for (int col = 0; col < 8; col++) {
                if (!board.isEmpty(col, row)) {
                    if (board.tiles[col][row].isWhite() == white) {
                        moves = board.tiles[col][row].Moves(board);
                        for (Move move : moves) {
                            movesTotal.add(move);
                        }
                    }
                }
            }
        }
        return movesTotal;
    }
}
