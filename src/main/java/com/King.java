package com;
import java.util.ArrayList;

public class King extends Piece {
    public boolean castlingDone = false;
    int[][] possibleMoves = {{-1, 1},{0, 1},{1, 1},{-1, 0},{1, 0},{-1, -1},{0, -1},{-1, -1}};

    King(boolean white, int col, int row) {
        super(white, col, row, 0);
    }

    @Override
    public String toString() {
        return "X";
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