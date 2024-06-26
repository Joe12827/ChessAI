package com;
import java.util.ArrayList;

public class Pawn extends Piece {
    
    Pawn(boolean white, int col, int row) {
        super(white, col, row, 1);
        name = "Pawn";
    }

    @Override
    public String toString() {
        return "P";
    }

    @Override
    public ArrayList<Move> Moves(Board board) {
        int forward = 1;
        if (!white) {
            forward = -1;
        }
        ArrayList<Move> moves = new ArrayList<>();

        if (inBounds(col + 1, row + forward) && !board.isEmpty(col + 1, row + forward)) {
            if (board.isWhite(col + 1, row + forward) != white) {
                Move move = new Move(col, row, col + 1, row + forward);
                moves.add(move);
            }
        }

        if (inBounds(col - 1, row + forward) && !board.isEmpty(col - 1, row + forward)) {
            if (board.isWhite(col - 1, row + forward) != white) {
                Move move = new Move(col, row, col - 1, row + forward);
                moves.add(move);
            }
        }

        if (inBounds(col, row + forward) && board.isEmpty(col, row + forward)) {
            Move move = new Move(col, row, col, row + forward);
            moves.add(move);

            if ((white && row == 1) || (!white && row == 6)) {
                if (inBounds(col, row + (forward * 2)) && board.isEmpty(col, row + (forward * 2))) {
                    move = new Move(col, row, col, row + (2 * forward));
                    moves.add(move);
                }
            }

        }

        return moves;
    }

    @Override
    public Piece copyPiece() {
        Pawn newPawn = new Pawn(this.white, this.col, this.row);
        return newPawn;
    }
    
}
