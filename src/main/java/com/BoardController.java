package com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/board")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping("/state")
    public String getBoardState() {
        return boardService.getBoardState(); // Return current board state
    }

    @PostMapping("/move")
    public boolean makeMove(@RequestBody MoveJSON json) {
        return boardService.makeMove(json); // Execute the specified move;
    }

    @GetMapping("/getaimove")
    public Move getAIMove() {
        return boardService.getAIMove();
    }
    
}