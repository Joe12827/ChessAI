package com;

import java.util.ArrayList;

public class Brain {
    boolean white;
    boolean maxPlayer;
    int maxDepth;
    int currentDepth = 0;


    MiniMax minimax;

    Brain (boolean white, int maxDepth, Board board) {
        this.white = white;
        maxPlayer = white;
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
                            // System.out.println(move);
                            movesTotal.add(move);
                        }
                    }
                }
            }
        }
        return movesTotal;
    }

    public void findAllMoves(Board board, int depth, Node node) {
        board = board.copyBoard();
        // System.out.println("DEPTH: " + depth);

        if (depth == 0) {
            node = new Node();
            minimax.getTree().setRoot(node);
            // System.out.println("Set root to " + node);
        }

        if (depth > maxDepth) {
            return;
        }

        ArrayList<Move> moves = findCurrentMoves(board);
        depth++;
        // System.out.println(moves);
        for (Move move : moves) {
            Board newBoard = board.copyBoard();
            newBoard.makeFastMove(move);
            Node newNode = new Node (move, newBoard.totalUtility, newBoard.whitesTurn());
            node.addMove(newNode);
            findAllMoves(newBoard, depth, newNode);
        }

    }

    public MiniMax getMinimax() {
        return minimax;
    }

    public void calculateMinimax (Node node) {
        if (node == null) {
            return;
        }

        // If node is a leaf node (no children), assign its utility as its Minimax value
        if (!node.hasMoves()) {
            node.setMinimaxValue(node.getTotalUtility());
            // System.out.println("END VAL: " + node.getTotalUtility());
            return;
        }

        // Recursively calculate Minimax values for child nodes
        if (node.isMaxPlayer()) {
            // If node is a Max player, find the maximum Minimax value among its children
            int maxMinimaxValue = Integer.MIN_VALUE;
            for (Node child : node.getMoves()) {
                calculateMinimax(child);
                maxMinimaxValue = Math.max(maxMinimaxValue, child.getMinimaxValue());
            }
            node.setMinimaxValue(maxMinimaxValue);
        } else {
            // If node is a Min player, find the minimum Minimax value among its children
            int minMinimaxValue = Integer.MAX_VALUE;
            for (Node child : node.getMoves()) {
                calculateMinimax(child);
                minMinimaxValue = Math.min(minMinimaxValue, child.getMinimaxValue());
            }
            node.setMinimaxValue(minMinimaxValue);
        }
    }

    public Move findNextBestMove (Board board) {
        findAllMoves(board, 0, null);
        calculateMinimax(minimax.getTree().getRoot());
        int bestValue = 0;
        Move bestMove = null;
        boolean first = true;

        for (Node node : minimax.getTree().getRoot().getMoves()) {
            // System.out.println(node.getMinimaxValue());
            if (first) {
                first = false;
                bestValue = node.getMinimaxValue();
                bestMove = node.getMove();
            } else if (node.getMinimaxValue() < bestValue) {
                bestValue = node.getMinimaxValue();
                bestMove = node.getMove();
            }
        }
        // System.out.println("MOVE: " + bestMove);

        return bestMove;
    }

}
