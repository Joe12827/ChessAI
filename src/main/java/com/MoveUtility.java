package com;

public class MoveUtility implements Comparable<MoveUtility> {

    Move move;
    double utilityDifference;  // Always positive (Black is trying to get the max)

    public MoveUtility (Move move, double utilityDifference) {
        this.move = move;
        this.utilityDifference = utilityDifference;
    }

    @Override
    public int compareTo(MoveUtility other) {
        return Double.compare(utilityDifference, other.utilityDifference);
    }
}
