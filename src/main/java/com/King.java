package com;
import java.util.ArrayList;

public class King extends Piece {
    public boolean castleable = true;
    public static int[][] possibleMoves = {{-1, 1},{0, 1},{1, 1},{1, 0},{1, -1},{0, -1},{-1, -1},{-1, 0}};

    King(boolean white, int col, int row) {
        super(white, col, row, 5);
        name = "King";
    }

    King(boolean white, int col, int row, boolean castable) {
        super(white, col, row, 5);
        this.castleable = castable;
        name = "King";
    }

    @Override
    public String toString() {
        return "X";
    }

    @Override
    public void setLocation(int col, int row) {
        super.setLocation(col, row);
        castleable = false;
    }

    @Override
    public void setCastleable() {
        castleable = true;
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

        // Check Castle
        if (castleable) {
            // Right Castle
            if (!board.isEmpty(col + 3, row) && board.isEmpty(col + 2, row) && board.isEmpty(col + 1, row)) {
                if (board.getTiles()[col + 3][row] instanceof Rook) {
                    if (((Rook) board.getTiles()[col + 3][row]).castleable()) {
                        Move move = new Move(col, row, col + 3, row);
                        moves.add(move);
                    }
                }
            }
            // Left Castle
            if (!board.isEmpty(col - 4, row) && board.isEmpty(col - 1, row) && board.isEmpty(col - 2, row) && board.isEmpty(col - 3, row)) {
                if (board.getTiles()[col - 4][row] instanceof Rook) {
                    if (((Rook) board.getTiles()[col - 4][row]).castleable()) {
                        Move move = new Move(col, row, col - 4, row);
                        moves.add(move);
                    }
                }
            }
        }
        return moves;
    }

    @Override
    public Piece copyPiece() {
        King newKing = new King(this.white, this.col, this.row, this.castleable);
        return newKing;
    }

   
    
}
