import java.util.ArrayList;

public class Bishop extends Piece {

    Bishop(boolean white, int col, int row) {
        super(white, col, row);
        //TODO Auto-generated constructor stub
    }

    @Override
    public String toString() {
        return "B";
    }

    @Override
    public ArrayList<ArrayList<Integer>> Moves(Board board) {
        int num = 0;
        ArrayList<ArrayList<Integer>> moves = new ArrayList<>();

        int tempCol = col + 1;
        int tempRow = row + 1;
        while (tempCol < 8 && tempRow < 8) {
            if (board.isWhite(tempCol, tempRow) == white) {
                break;
            }
            moves.add(new ArrayList<>());
            moves.get(num).add(tempCol);
            moves.get(num).add(tempRow);
            num++;
            if (board.isWhite(tempCol, tempRow) != white) {
                break;
            }
            tempCol++;
            tempRow++;
        }

        tempCol = col - 1;
        tempRow = row + 1;
        while (tempCol >= 0 && tempRow < 8) {
            if (board.isWhite(tempCol, tempRow) == white) {
                break;
            }
            moves.add(new ArrayList<>());
            moves.get(num).add(tempCol);
            moves.get(num).add(tempRow);
            num++;
            if (board.isWhite(tempCol, tempRow) != white) {
                break;
            }
            tempCol--;
            tempRow++;
        }

        tempCol = col - 1;
        tempRow = row - 1;
        while (tempCol >= 0 && tempRow >= 0) {
            if (board.isWhite(tempCol, tempRow) == white) {
                break;
            }
            moves.add(new ArrayList<>());
            moves.get(num).add(tempCol);
            moves.get(num).add(tempRow);
            num++;
            if (board.isWhite(tempCol, tempRow) != white) {
                break;
            }
            tempCol--;
            tempRow--;
        }

        tempCol = col + 1;
        tempRow = row - 1;
        while (tempCol < 8 && tempRow >= 0) {
            if (board.isWhite(tempCol, tempRow) == white) {
                break;
            }
            moves.add(new ArrayList<>());
            moves.get(num).add(tempCol);
            moves.get(num).add(tempRow);
            num++;
            if (board.isWhite(tempCol, tempRow) != white) {
                break;
            }
            tempCol++;
            tempRow--;
        }

        return moves;
    }
    
}
