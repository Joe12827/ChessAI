import java.util.ArrayList;

public class Pawn extends Piece {
    
    Pawn(boolean white, int col, int row) {
        super(white, col, row);
        //TODO Auto-generated constructor stub
    }

    @Override
    public String toString() {
        return "P";
    }

    @Override
    public ArrayList<ArrayList<Integer>> Moves(Board board) {
        int num = 0;
        ArrayList<ArrayList<Integer>> moves = new ArrayList<>();
        if (board.isWhite(col + 1, row + 1) != white) {
            moves.add(new ArrayList<>());
            moves.get(num).add(col + 1);
            moves.get(num).add(row + 1);
            num++;
        }
        if (board.isWhite(col - 1, row + 1) != white) {
            moves.add(new ArrayList<>());
            moves.get(num).add(col - 1);
            moves.get(num).add(row + 1);
            num++;
        }
        if (board.isEmpty(col, row + 1)) {
            moves.add(new ArrayList<>());
            moves.get(num).add(col);
            moves.get(num).add(row + 1);
            num++;
        }
        if (board.isEmpty(col, row + 2)) {
            moves.add(new ArrayList<>());
            moves.get(num).add(col);
            moves.get(num).add(row + 2);
            num++;
        }
        return moves;
    }
    
}
