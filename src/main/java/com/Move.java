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

    @Override
    public String toString() {
        return (start[0] + 1) + "," + (start[1] + 1) + " > " + (stop[0] + 1) + "," + (stop[1] + 1);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Move)) {
            return false;
        }
         
        // typecast o to Complex so that we can compare data members 
        Move c = (Move) obj;

        return (this.start[0] == c.start[0] && 
                this.start[1] == c.start[1] &&
                this.stop[0] == c.stop[0] &&
                this.stop[1] == c.stop[1]);
    }
}
