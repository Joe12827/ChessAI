package com;
import java.util.ArrayList;

import com.Board.State;

public class Main {
  public static void main(String[] args) {
    Board board = new Board(false);
    Brain brain = new Brain(false, 7, board);

    System.out.println(board);
    
    // board.makeMove(new Move(3, 1, 3, 2));
    // board.makeMove(new Move(4, 6, 4, 5));
    // board.makeMove(new Move(4, 1, 4, 2));
    // board.makeMove(new Move(5, 7, 1, 3));
    // board.makeMove(new Move(5, 1, 5, 2));

    // System.out.println(board);
    // brain.findAllMoves(board, 0, null);
    // ArrayList<Piece> pieces = board.getPieces();
    // Piece piece = pieces.get(1);
    // System.out.println(piece);  

    long a = System.currentTimeMillis();
    // brain.calculateMinimax(board, null, 0, -1000, 1000);
    // System.out.println(brain.minimax);
    // brain.findNextBestMove(board);
    // System.out.println(board);
    // brain.findAllMoves(board, 0, null);
    // brain.findAllMoves(board, 0, null);
    // brain.calculateMinimax(brain.getMinimax().getTree().getRoot(), 0, -1000, 1000, brain.white);
    // for (int i = 0; i < 1000000; i++) {
    //   // brain.calculateMinimax(brain.getMinimax().getTree().getRoot(), 0, -1000, 1000, brain.white);
    //   // piece.Moves(board);
    //   brain.findCurrentMoves(board);
    //   // Board newBoard = board.copyBoard();
    //   // Move move = new Move(1, 6, 1, 4);
    //   // Node node = new Node();
    //   // Board newBoard = board.copyBoard();
    //   // newBoard.makeFastMove(move);
    //   // Node newNode = new Node(move, newBoard.totalUtility, newBoard.whitesTurn());
    //   // node.addMove(newNode);
    // }

    board.makeMove(new Move(4, 1, 4, 3));
    board.makeMove(new Move(2, 6, 2, 4));
    System.out.println(board);

    for (int i = 0; i < 10;) {
      if (board.state == State.WHITE_WINNER || board.state == State.BLACK_WINNER) {
        board = new Board(false);
        board.makeMove(new Move(4, 1, 4, 3));
        board.makeMove(new Move(2, 6, 2, 4));
        i++;
      }
      board.makeFastMove(brain.findNextBestMove(board));
      System.out.println(board);
    }
    


    long b = System.currentTimeMillis();
    System.out.println(b - a);

    // System.out.println(move);


    // System.out.println(board.whiteUtility / board.blackUtility);

    // ArrayList<Move> moves = brain.findCurrentMoves(board);

    // System.out.println(moves);
    // System.out.println("-");

    // brain.orderMoves(moves, board);

    // System.out.println(moves);
  }
}