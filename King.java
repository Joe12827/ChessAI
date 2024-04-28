import java.util.ArrayList;

public class King extends Piece {
    public boolean castlingDone = false;
    int[][] possibleMoves = {{-1, 1},{0, 1},{1, 1},{-1, 0},{1, 0},{-1, -1},{0, -1},{-1, -1}};

    King(boolean white, int col, int row) {
        super(white, col, row);
    }

    @Override
    public String toString() {
        return "X";
    }


    @Override
    public ArrayList<ArrayList<Integer>> Moves(Board board) {
        int num = 0;
        ArrayList<ArrayList<Integer>> moves = new ArrayList<>();
        for (int[] move : possibleMoves) {
            if (board.isWhite(move[0], move[1]) != white){
                moves.add(new ArrayList<>());
                moves.get(num).add(move[0]);
                moves.get(num).add(move[1]);
                num++; 
            }
        }
        return moves;
    }

   
    
}
