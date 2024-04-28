import java.util.ArrayList;

public abstract class Piece {
    public boolean killed = false; 
    public boolean white = false;
    public int col;
    public int row;
    public int value;
    
    Piece(boolean white, int col, int row, int value) {
        this.white = white;
        this.col = col;
        this.row = row;
        this.value = value;
    }

    public boolean isWhite() {
        return white;
    }

    public void kill(){
        killed = true;
    }

    public abstract ArrayList<ArrayList<Integer>> Moves(Board board);

    protected int getValue(){
        return this.value;
    };
}