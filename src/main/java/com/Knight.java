package com;
import java.util.ArrayList;

public class Knight extends Piece {
    public static int[][] possibleMoves = {{-1, 2},{1, 2},{2, 1},{2, -1},{-2, 1},{-2, -1},{-1, -2},{1, -2}};

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
            if (inBounds(col + setMove[0], row + setMove[1])) {
                if (!board.isEmpty(col + setMove[0], row + setMove[1])) {
                    if (board.isWhite(col + setMove[0], row + setMove[1]) != white){
                        Move move = new Move(col, row, col + setMove[0], row + setMove[1]);
                        moves.add(move);
                    }
                } else {
                    Move move = new Move(col, row, col + setMove[0], row + setMove[1]);
                    moves.add(move);
                }
            }
        }
        return moves;
    }

    @Override
    public Piece copyPiece() {
        Knight newKnight = new Knight(this.white, this.col, this.row);
        return newKnight;
    }
    
}
