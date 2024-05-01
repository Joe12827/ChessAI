package com;

import java.util.ArrayList;

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
        Move aiMove = brain.findNextBestMove(board);
        System.out.println("Done Thinking.");
        board.makeMove(aiMove);
        return aiMove;
        // brain.findAllMoves(board, 0, null);
        // System.out.println("Done Thinking");
        // return brain.findNextBestMove(board);
    }
}
