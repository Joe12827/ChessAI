package com;

import org.springframework.stereotype.Service;

@Service
public class BoardService {

    private Board board = new Board(false); // Instantiate the Board
    private Brain brain = new Brain(false, 7, board); // Depth MAX: 7

    public String getBoardState() {
        return board.toString(); // Get current board state as a string
    }

    public boolean makeMove(MoveJSON json) {
        Move move = new Move(json);
        return board.makeMove(move); // Execute the specified move on the board
    }

    public Move getAIMove() {
        System.out.println("Thinking. . .");
        long start = System.currentTimeMillis();
        Move aiMove = brain.findNextBestMove(board);
        long end = System.currentTimeMillis();
        long time = end - start;
        System.out.println("Done Thinking: " + time);
        board.makeMove(aiMove);
        return aiMove;
        // brain.findAllMoves(board, 0, null);
        // System.out.println("Done Thinking");
        // return brain.findNextBestMove(board);
    }

    public Move getLastMove() {
        Move lastMove = (Move)board.moveHistory.getLast()[0];
        return lastMove;
    }
}
