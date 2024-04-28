package com;

import org.springframework.stereotype.Service;

@Service
public class BoardService {

    private Board board = new Board(); // Instantiate the Board

    public String getBoardState() {
        return board.toString(); // Get current board state as a string
    }

    public void makeMove(MoveJSON json) {
        Move move = new Move(json);
        board.makeMove(move); // Execute the specified move on the board
    }
}
