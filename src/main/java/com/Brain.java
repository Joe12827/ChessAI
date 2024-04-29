package com;

import java.util.ArrayList;

public class Brain {
    boolean white;
    int maxDepth;
    int currentDepth = 0;


    MiniMax minimax;

    Brain (boolean white, int maxDepth, Board board) {
        this.white = white;
        this.maxDepth = maxDepth;
        minimax = new MiniMax(board);
    }

    public ArrayList<Move> findCurrentMoves(Board board) {
        ArrayList<Move> movesTotal = new ArrayList<>();
        ArrayList<Move> moves = new ArrayList<>();
        for (int row = 7; row >= 0; row--) {
            for (int col = 0; col < 8; col++) {
                if (!board.isEmpty(col, row)) {
                    if (board.tiles[col][row].isWhite() == board.whitesTurn()) { // If the piece is the color of the turn (white on white's turn)
                        moves = board.tiles[col][row].Moves(board);
                        for (Move move : moves) {
                            movesTotal.add(move);
                        }
                    }
                }
            }
        }
        return movesTotal;
    }

    public void findAllMoves(Board board, int depth, Node node) {
        if (depth == 0) {
            node = new Node();
            minimax.getTree().setRoot(node);
        }

        if (depth > maxDepth) {
            return;
        }

        ArrayList<Move> moves = findCurrentMoves(board);
        depth++;
        for (Move move : moves) {
            Board newBoard = board.copyBoard();
            newBoard.makeMove(move);
            Node newNode = new Node (move, board.totalUtility);
            ArrayList<Node> nodeMoves = node.getMoves();
            nodeMoves.add(newNode);
            findAllMoves(newBoard, depth, newNode);
        }
        
    }

    public Move findNextBestMove (Board board) {
        for (int i = 0; i < maxDepth; i++) {
            ArrayList<Move> moves = findCurrentMoves(board);
        }

        return null;
    }

}
