package com;

import java.util.ArrayList;

public class Board {
    Piece[][] tiles = new Piece[8][8];
    int whiteUtility = 39;
    int blackUtility = 39;
    int totalUtility = whiteUtility - blackUtility;

    Board() {
        tiles = new Piece[8][8];
        // WHITE
        tiles[0][1] = new Pawn(true, 0, 1);
        tiles[1][1] = new Pawn(true, 1, 1);
        tiles[2][1] = new Pawn(true, 2, 1);
        tiles[3][1] = new Pawn(true, 3, 1);
        tiles[4][1] = new Pawn(true, 4, 1);
        tiles[5][1] = new Pawn(true, 5, 1);
        tiles[6][1] = new Pawn(true, 6, 1);
        tiles[7][1] = new Pawn(true, 7, 1);

        tiles[0][0] = new Rook(true, 0, 0);
        tiles[1][0] = new Knight(true, 1, 0);
        tiles[2][0] = new Bishop(true, 2, 0);
        tiles[3][0] = new Queen(true, 3, 0);
        tiles[4][0] = new King(true, 4, 0);
        tiles[5][0] = new Bishop(true, 5, 0);
        tiles[6][0] = new Knight(true, 6, 0);
        tiles[7][0] = new Rook(true, 7, 0);

        // BLACK
        tiles[0][6] = new Pawn(false, 0, 6);
        tiles[1][6] = new Pawn(false, 1, 6);
        tiles[2][6] = new Pawn(false, 2, 6);
        tiles[3][6] = new Pawn(false, 3, 6);
        tiles[4][6] = new Pawn(false, 4, 6);
        tiles[5][6] = new Pawn(false, 5, 6);
        tiles[6][6] = new Pawn(false, 6, 6);
        tiles[7][6] = new Pawn(false, 7, 6);

        tiles[0][7] = new Rook(false, 0, 7);
        tiles[1][7] = new Knight(false, 1,7);
        tiles[2][7] = new Bishop(false, 2, 7);
        tiles[3][7] = new King(false, 3, 7);
        tiles[4][7] = new Queen(false, 4, 7);
        tiles[5][7] = new Bishop(false, 5, 7);
        tiles[6][7] = new Knight(false, 6, 7);
        tiles[7][7] = new Rook(false, 7, 7);
    }

    public boolean isWhite(int col, int row) {
        return tiles[col][row].isWhite();
    }

    public boolean isEmpty(int col, int row) {
        return tiles[col][row] == null;
    }

    public boolean makeMove(Move move) {
        Piece piece = tiles[move.getStart()[0]][move.getStart()[1]];
        ArrayList<Move> moves = piece.Moves(this);

        if (!moves.contains(move)) {
            System.out.println("CANNOT MAKE THIS MOVE");
            return false;
        }

        if (tiles[move.getStop()[0]][move.getStop()[1]] != null) {
            int value = tiles[move.getStop()[0]][move.getStop()[1]].getValue();
            if (tiles[move.getStop()[0]][move.getStop()[1]].isWhite()) {
                whiteUtility -= value;
            } else {
                blackUtility -= value;
            }
            totalUtility = whiteUtility - blackUtility;
            tiles[move.getStop()[0]][move.getStop()[1]].kill();
        }

        tiles[move.getStop()[0]][move.getStop()[1]] = tiles[move.getStart()[0]][move.getStart()[1]];
        tiles[move.getStart()[0]][move.getStart()[1]] = null;
        System.err.println(this);
        return true;
    }

    @Override
    public String toString() {
        String str = "";
        str += "-----------------";
        str += "\n";
        for (int r = 7; r >= 0; r--) {
            for (int c = 0; c < 8; c++) {
                str += "|";
                if (tiles[c][r] != null) {
                    str += tiles[c][r];
                } else {
                    str += " ";
                }
            }
            str += "|";
            if (r == 4) {
                str += " B: " + blackUtility;
            }
            if (r == 3) {
                str += " W: " + whiteUtility;
            }
            str += "\n";
            str += "-----------------";
            str += "\n";
        }
        return str;
    }
}