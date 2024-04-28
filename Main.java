public class Main {
    public static void main(String[] args) {
        Board board = new Board();
        System.err.println(board);
        int[] start = {0, 1};
        int[] stop = {0, 2};
        board.makeMove(start, stop);
        System.err.println(board);
  }
}