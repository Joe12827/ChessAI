import java.util.ArrayList;

public class Rook extends Piece {

    Rook(boolean white, int col, int row) {
        super(white, col, row);
    }

    @Override
    public String toString() {
        return "R";
    }

    @Override
    public ArrayList<ArrayList<Integer>> Moves(Board board) {
        int num = 0;
        ArrayList<ArrayList<Integer>> moves = new ArrayList<>();

        int tempRow = row + 1;
        while (tempRow < 8) {
            if (board.isWhite(col, tempRow) == white) {
                break;
            }
            moves.add(new ArrayList<>());
            moves.get(num).add(col);
            moves.get(num).add(tempRow);
            num++;
            if (board.isWhite(col, tempRow) != white) {
                break;
            }
            tempRow++;
        }

        tempRow = row - 1;
        while (tempRow >= 0) {
            if (board.isWhite(col, tempRow) == white) {
                break;
            }
            moves.add(new ArrayList<>());
            moves.get(num).add(col);
            moves.get(num).add(tempRow);
            num++;
            if (board.isWhite(col, tempRow) != white) {
                break;
            }
            tempRow--;
        }

        int tempCol = col + 1;
        while (tempCol < 8) {
            if (board.isWhite(tempCol, row) == white) {
                break;
            }
            moves.add(new ArrayList<>());
            moves.get(num).add(tempCol);
            moves.get(num).add(row);
            num++;
            if (board.isWhite(tempCol, row) != white) {
                break;
            }
            tempCol++;
        }

        tempCol = col - 1;
        while (tempCol >= 0) {
            if (board.isWhite(tempCol, row) == white) {
                break;
            }
            moves.add(new ArrayList<>());
            moves.get(num).add(tempCol);
            moves.get(num).add(row);
            num++;
            if (board.isWhite(tempCol, row) != white) {
                break;
            }
            tempCol++;
        }

        return moves;
    }
    
}
