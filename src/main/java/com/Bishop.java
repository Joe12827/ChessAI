package com;
import java.util.ArrayList;

public class Bishop extends Piece {

    Bishop(boolean white, int col, int row) {
        super(white, col, row, 3);
    }

    @Override
    public String toString() {
        return "B";
    }

    @Override
    public ArrayList<Move> Moves(Board board) {
        ArrayList<Move> moves = new ArrayList<>();

        int tempCol = col + 1;
        int tempRow = row + 1;
        while (tempCol < 8 && tempRow < 8) {
            if (!board.isEmpty(tempCol, tempRow)) {
                if (board.isWhite(tempCol, tempRow) == white) {
                    break;
                }
            }
            Move move = new Move(col, row, tempCol, tempRow);
            moves.add(move);
            if (!board.isEmpty(tempCol, tempRow)) {
                if (board.isWhite(tempCol, tempRow) != white) {
                    break;
                }
            }
            tempCol++;
            tempRow++;
        }

        tempCol = col - 1;
        tempRow = row + 1;
        while (tempCol >= 0 && tempRow < 8) {
            if (!board.isEmpty(tempCol, tempRow)) {
                if (board.isWhite(tempCol, tempRow) == white) {
                    break;
                }
            }
            Move move = new Move(col, row, tempCol, tempRow);
            moves.add(move);
            if (!board.isEmpty(tempCol, tempRow)) {
                if (board.isWhite(tempCol, tempRow) != white) {
                    break;
                }
            }
            tempCol--;
            tempRow++;
        }

        tempCol = col - 1;
        tempRow = row - 1;
        while (tempCol >= 0 && tempRow >= 0) {
            if (!board.isEmpty(tempCol, tempRow)) {
                if (board.isWhite(tempCol, tempRow) == white) {
                    break;
                }
            }
            Move move = new Move(col, row, tempCol, tempRow);
            moves.add(move);
            if (!board.isEmpty(tempCol, tempRow)) {
                if (board.isWhite(tempCol, tempRow) != white) {
                    break;
                }
            }
            tempCol--;
            tempRow--;
        }

        tempCol = col + 1;
        tempRow = row - 1;
        while (tempCol < 8 && tempRow >= 0) {
            if (!board.isEmpty(tempCol, tempRow)) {
                if (board.isWhite(tempCol, tempRow) == white) {
                    break;
                }
            }
            Move move = new Move(col, row, tempCol, tempRow);
            moves.add(move);
            if (!board.isEmpty(tempCol, tempRow)) {
                if (board.isWhite(tempCol, tempRow) != white) {
                    break;
                }
            }
            tempCol++;
            tempRow--;
        }

        return moves;
    }
    
    @Override
    public Piece copyPiece() {
        Bishop newBishop = new Bishop(this.white, this.col, this.row);
        return newBishop;
    }
}
