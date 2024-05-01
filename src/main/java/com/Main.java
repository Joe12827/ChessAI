package com;
public class Main {
  public static void main(String[] args) {
    Board board = new Board(false);
    Brain brain = new Brain(false, 3, board);

    board.makeMove(new Move(5, 1, 5, 3));


    long a = System.currentTimeMillis();
    // for (int i = 0; i < 1000000; i++) {
    //   // Move move = new Move(1, 6, 1, 4);
    //   // Node node = new Node();
    //   Board newBoard = board.copyBoard();
    //   // newBoard.makeFastMove(move);
    //   // Node newNode = new Node(move, newBoard.totalUtility, newBoard.whitesTurn());
    //   // node.addMove(newNode);
    // }


    brain.findAllMoves(board, 0, null);

    
    long b = System.currentTimeMillis();

    System.out.println(b - a);
  }
}