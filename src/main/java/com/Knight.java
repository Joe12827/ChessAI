package com;
import java.util.ArrayList;

public class Knight extends Piece {
    int[][] possibleMoves = {{-1, 2},{1, 2},{2, 1},{2, -1},{-2, 1},{-2, -1},{-2, -1},{-2, 1}};

    Knight(boolean white, int col, int row) {
        super(white, col, row, 3);
    }

    @Override
    public String toString() {
        return "K";
    }

    @Override
    public ArrayList<Move> Moves(Board board) {
        ArrayList<Move> moves = new ArrayList<>();
        for (int[] setMove : possibleMoves) {
            if (col + setMove[0] >= 0 && col + setMove[0] <= 7 && row + setMove[1] >= 0 && row + setMove[1] <= 7) {
                if (board.isWhite(col + setMove[0], row + setMove[1]) != white){
                    Move move = new Move(col, row, col + setMove[0], row + setMove[1]);
                    moves.add(move);
                }
            }
                
        }
        return moves;
    }

   
    
}
