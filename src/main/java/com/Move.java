package com;
public class Move {
    public int[] start;
    public int[] stop;

    Move(int[] start, int[] stop) {
        this.start = start;
        this.stop = stop;
    }

    Move(int xStart, int yStart, int xStop, int yStop) {
        start = new int[]{xStart, yStart};
        stop = new int[]{xStop, yStop};
    }

    Move(MoveJSON jsonData) {
        char columnChar = jsonData.getStart().charAt(0);
        char rowChar = jsonData.getStart().charAt(1);

        int xStart = columnChar - 'a';
        int yStart = Character.getNumericValue(rowChar) - 1;

        columnChar = jsonData.getStop().charAt(0);
        rowChar = jsonData.getStop().charAt(1);

        int xStop = columnChar - 'a';
        int yStop = Character.getNumericValue(rowChar) - 1;

        start = new int[]{xStart, yStart};
        stop = new int[]{xStop, yStop};
    }

    public int[] getStart() {
        return start;
    }

    public int[] getStop() {
        return stop;
    }
}
