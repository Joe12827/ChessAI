import java.util.ArrayList;

public abstract class Piece {
    public boolean killed = false; 
    public boolean white = false;
    public int col;
    public int row;
    
    Piece(boolean white, int col, int row) {
        this.white = white;
        this.col = col;
        this.row = row;
    }

    public boolean isWhite() {
        return white;
    }

    public void kill(){
        killed = true;
    }

    public abstract ArrayList<ArrayList<Integer>> Moves(Board board);
}