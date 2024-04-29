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
        boolean response = board.makeMove(move); // Execute the specified move on the board
        brain.findAllMoves(board, 0, null);
        System.out.println("Done");
        return response;
    }
}
