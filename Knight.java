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
