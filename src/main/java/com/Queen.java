package com;
import java.util.ArrayList;

public class Queen extends Piece {

    Queen(boolean white, int col, int row) {
        super(white, col, row, 9);
        name = "Queen";
    }

    @Override
    public String toString() {
        return "Q";
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

        tempRow = row + 1;
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

        tempCol = col + 1;
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
        Queen newQueen = new Queen(this.white, this.col, this.row);
        return newQueen;
    }
    
}
