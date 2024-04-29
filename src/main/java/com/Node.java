package com;

import java.util.ArrayList;

public class Node {
    int totalUtility;
    Move move;
    ArrayList<Node> moves;

    Node() {
        this.totalUtility = 0;
        moves = new ArrayList<Node>();
    }

    Node(Move move, int totalUtility) {
        this.move = move;
        this.totalUtility = totalUtility;
        moves = new ArrayList<Node>();
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

    @Override
    public String toString() {
        return "TREE>>>";
    }
}