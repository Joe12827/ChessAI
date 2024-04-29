package com;
import java.util.ArrayList;

public class King extends Piece {
    public boolean castlingDone = false;
    int[][] possibleMoves = {{-1, 1},{0, 1},{1, 1},{-1, 0},{1, 0},{-1, -1},{0, -1},{-1, -1}};

    King(boolean white, int col, int row) {
        super(white, col, row, 100);
    }

    King(boolean white, int col, int row, boolean castlingDone) {
        super(white, col, row, 100);
        this.castlingDone = castlingDone;
    }

    @Override
    public String toString() {
        return "X";
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
        King newKing = new King(this.white, this.col, this.row, this.castlingDone);
        return newKing;
    }

   
    
}
