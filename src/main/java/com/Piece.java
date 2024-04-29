package com;
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

    public abstract Piece copyPiece();

    public boolean inBounds (int col, int row) {
        return (col >= 0 && col <= 7 && row >= 0 && row <= 7);
    }

    public boolean isWhite() {
        return white;
    }

    public void kill(){
        killed = true;
    }

    public void setLocation(int col, int row) {
        this.col = col;
        this.row = row;
    }

    public abstract ArrayList<Move> Moves(Board board);

    protected int getValue(){
        return this.value;
    };
}