package com;

import org.springframework.stereotype.Service;

@Service
public class BoardService {

    private Board board = new Board(false); // Instantiate the Board
    private Brain brain = new Brain(false, 3, board);

    public String getBoardState() {
        return board.toString(); // Get current board state as a string
    }

    public boolean makeMove(MoveJSON json) {
        Move move = new Move(json);
        return board.makeMove(move); // Execute the specified move on the board
    }

    public Move getAIMove() {
        System.out.println("Thinking. . .");
        Move aimove = new Move(0, 6, 0, 4);
        board.makeMove(aimove);
        return aimove;
        // brain.findAllMoves(board, 0, null);
        // System.out.println("Done Thinking");
        // return brain.findNextBestMove(board);
    }
}
