package com;

import java.util.ArrayList;

public class TranspositionTable {
    ArrayList<TranspositionNode> states;
    int stateCount;

    public class TranspositionNode {
        Board board;
        int depth;
        Move bestMove;
        TranspositionNode(Board board, int depth, Move bestMove) {
            this.board = board;
            this.depth = depth;
            this.bestMove = bestMove;
        }
    }

    TranspositionTable() {
        states = new ArrayList<>();
        stateCount = 0;
    }

    public void addMove(Board board, int depth, Move bestMove) {
        TranspositionNode state = new TranspositionNode(board.copyBoard(), depth, bestMove);
        states.add(state);
        stateCount++;
    }

    public int getStateCount() {
        return stateCount;
    }

    public Move checkState (Board board, int depth) {
        for (TranspositionNode state : states) {
            if (state.board.equals(board)) {
                if (state.depth >= depth) {
                    return state.bestMove;
                }
            }
        }
        return null;
    }
}
