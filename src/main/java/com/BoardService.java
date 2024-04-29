package com;

import org.springframework.stereotype.Service;

@Service
public class BoardService {

    private Board board = new Board(false); // Instantiate the Board

    public String getBoardState() {
        return board.toString(); // Get current board state as a string
    }

    public boolean makeMove(MoveJSON json) {
        Move move = new Move(json);
        return board.makeMove(move); // Execute the specified move on the board
    }
}
