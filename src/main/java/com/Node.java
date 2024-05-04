package com;

import java.util.ArrayList;

public class Node {
    int totalUtility;
    int minimaxValue;
    Move move;
    ArrayList<Node> moves;
    boolean isMaxPlayer;

    Node() {
        this.totalUtility = 0;
        moves = new ArrayList<Node>();
    }

    public boolean isMaxPlayer() {
        return isMaxPlayer;
    }

    public void addMove (Node node) {
        moves.add(node);
    }

    Node(Move move, int totalUtility, boolean isMaxPlayer) {
        this.move = move;
        this.totalUtility = totalUtility;
        this.isMaxPlayer = isMaxPlayer;
        moves = new ArrayList<Node>();
    }

    public Move getMove() {
        return move;
    }

    public void setMinimaxValue(int minimaxValue) {
        this.minimaxValue = minimaxValue;
    }

    public int getTotalUtility() {
        return totalUtility;
    }

    public ArrayList<Node> getMoves() {
        return moves;
    }

    public void setMoves(ArrayList<Node> moves) {
        this.moves = moves;
    }

    public boolean hasMoves() {
        return moves.size() != 0;
    }

    public int getMinimaxValue() {
        return minimaxValue;
    }

    @Override
    public String toString() {
        String str = "[ ";
        for (Node node : moves) {
            str += node.move;
            str += "(" + node.minimaxValue + ") | ";
        }
        str += " ]";
        return str;
    }
}