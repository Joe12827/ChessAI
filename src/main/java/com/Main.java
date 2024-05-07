package com;
import java.util.ArrayList;

import com.Board.State;

public class Main {
  public static void main(String[] args) {
    Board board = new Board(false);
    Brain brain = new Brain(false, 6, board);

    // System.out.println(board);
    
    // board.makeMove(new Move(4, 1, 4, 2));
    // board.makeMove(new Move(4, 6, 4, 5));
    // board.makeMove(new Move(3, 1, 3, 2));
    // board.makeMove(new Move(5, 7, 1, 3));
    // // board.makeMove(new Move(5, 1, 5, 2));
    // // board.makeMove(new Move(1, 3, 4, 0));
    

    System.out.println(board);
    // brain.findAllMoves(board, 0, null);
    // ArrayList<Piece> pieces = board.getPieces();
    // Piece piece = pieces.get(1);
    // System.out.println(piece);  

    long a = System.currentTimeMillis();


    for (int i = 0; i < 10;) {
      if (board.state == State.WHITE_WINNER || board.state == State.BLACK_WINNER) {
        board = new Board(false);
      }
      board.makeFastMove(brain.findNextBestMove(board));
      System.out.println(board);
    }
  
    long b = System.currentTimeMillis();
    
 
  }
}