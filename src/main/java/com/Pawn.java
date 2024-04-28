package com;
import java.util.ArrayList;

public class Pawn extends Piece {
    
    Pawn(boolean white, int col, int row) {
        super(white, col, row, 1);
    }

    @Override
    public String toString() {
        return "P";
    }

    @Override
    public ArrayList<Move> Moves(Board board) {

        ArrayList<Move> moves = new ArrayList<>();
        if (board.isWhite(col + 1, row + 1) != white) {
            Move move = new Move(col, row, col + 1, row + 1);
            moves.add(move);
        }
        if (board.isWhite(col - 1, row + 1) != white) {
            Move move = new Move(col, row, col - 1, row + 1);
            moves.add(move);
        }
        if (board.isEmpty(col, row + 1)) {
            Move move = new Move(col, row, col, row + 1);
            moves.add(move);
        }
        if (board.isEmpty(col, row + 2)) {
            if ((white && row == 1) || (!white && row == 6)) {
                Move move = new Move(col, row, col, row + 2);
                moves.add(move);
            }
        }
        return moves;
    }
    
}
