package com;
import java.util.ArrayList;

public class Main {
  public static void main(String[] args) {
    Board board = new Board(false);
    Brain brain = new Brain(false, 9, board);

    // board.makeMove(new Move(4, 1, 4, 3));
    // board.makeMove(new Move(3, 6, 3, 4));
    // board.makeMove(new Move(6, 0, 5, 2));

    board.makeMove(new Move(3, 1, 3, 2));
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
    brain.findNextBestMove(board);
    // System.out.println(board);
    System.out.println(brain.minimax);
    // brain.findAllMoves(board, 0, null);
    // brain.calculateMinimax(brain.getMinimax().getTree().getRoot(), 0, -1000, 1000, brain.white);
    // for (int i = 0; i < 1000000; i++) {
    //   brain.calculateMinimax(brain.getMinimax().getTree().getRoot(), 0, -1000, 1000, brain.white);
    //   // piece.Moves(board);
    //   // brain.findCurrentMoves(board);
    //   // Move move = new Move(1, 6, 1, 4);
    //   // Node node = new Node();
    //   // Board newBoard = board.copyBoard();
    //   // newBoard.makeFastMove(move);
    //   // Node newNode = new Node(move, newBoard.totalUtility, newBoard.whitesTurn());
    //   // node.addMove(newNode);
    // }


    // brain.findAllMoves(board, 0, null);

    
    long b = System.currentTimeMillis();

    System.out.println(b - a);
  }
}