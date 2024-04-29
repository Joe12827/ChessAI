package com;
import java.util.ArrayList;

public class Rook extends Piece {

    Rook(boolean white, int col, int row) {
        super(white, col, row, 5);
    }

    @Override
    public String toString() {
        return "R";
    }
    

    @Override
    public ArrayList<Move> Moves(Board board) {
        ArrayList<Move> moves = new ArrayList<Move>();

        int tempRow = row + 1;
        while (tempRow < 8) {
            if (!board.isEmpty(col, tempRow)) {
                if (board.isWhite(col, tempRow) == white) {
                    break;
                }
            }

            Move move = new Move(col, row, col, tempRow);
            moves.add(move);
            if (!board.isEmpty(col, tempRow)) {
                if (board.isWhite(col, tempRow) != white) {
                    break;
                }
            }
            
            tempRow++;
        }

        tempRow = row - 1;
        while (tempRow >= 0) {
            if (!board.isEmpty(col, tempRow)) {
                if (board.isWhite(col, tempRow) == white) {
                    break;
                }
            }

            Move move = new Move(col, row, col, tempRow);
            moves.add(move);
            if (!board.isEmpty(col, tempRow)) {
                if (board.isWhite(col, tempRow) != white) {
                    break;
                }
            }
            
            tempRow--;
        }

        int tempCol = col + 1;
        while (tempCol < 8) {
            if (!board.isEmpty(tempCol, row)) {
                if (board.isWhite(tempCol, row) == white) {
                    break;
                }
            }
            Move move = new Move(col, row, tempCol, row);
            moves.add(move);
            if (!board.isEmpty(tempCol, row)) {
                if (board.isWhite(tempCol, row) != white) {
                    break;
                }
            }
            tempCol++;
        }

        tempCol = col - 1;
        while (tempCol >= 0) {
            if (!board.isEmpty(tempCol, row)) {
                if (board.isWhite(tempCol, row) == white) {
                    break;
                }
            }
            Move move = new Move(col, row, tempCol, row);
            moves.add(move);
            if (!board.isEmpty(tempCol, row)) {
                if (board.isWhite(tempCol, row) != white) {
                    break;
                }
            }
            tempCol--;
        }

        return moves;
    }

    @Override
    public Piece copyPiece() {
        Rook newRook = new Rook(this.white, this.col, this.row);
        return newRook;
    }
    
}
