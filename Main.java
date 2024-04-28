public class Main {
    public static void main(String[] args) {
        Board board = new Board();
        System.err.println(board);

        Move move = new Move(0, 1, 0, 2);
        board.makeMove(move);

        move = new Move(0, 7, 0, 2);
        board.makeMove(move);
        System.err.println(board);
  }
}