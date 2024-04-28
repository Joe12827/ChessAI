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

    public int[] getStart() {
        return start;
    }

    public int[] getStop() {
        return stop;
    }
}
