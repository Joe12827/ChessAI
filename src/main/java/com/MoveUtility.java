package com;

public class MoveUtility implements Comparable<MoveUtility> {

    Move move;
    int utilityDifference;  // Always positive (Black is trying to get the max)

    public MoveUtility (Move move, int utilityDifference) {
        this.move = move;
        this.utilityDifference = utilityDifference;
    }

    @Override
    public int compareTo(MoveUtility other) {
        return Integer.compare(utilityDifference, other.utilityDifference);
    }
}
