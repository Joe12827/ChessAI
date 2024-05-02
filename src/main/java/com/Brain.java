package com;

import java.util.ArrayList;

import com.Board.State;

public class Brain {
    boolean white;
    boolean maxPlayer;
    int maxDepth;
    int currentDepth = 0;
    int current = 0;
    int total = 0;


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
        boolean whitesTurn = board.whitesTurn();

        // ArrayList<Piece> pieces = board.getPieces();
        // for (Piece piece : pieces) {
        //     if (piece.isWhite() == whitesTurn) {
        //         moves = piece.Moves(board);
        //         for (Move move : moves) {
        //             movesTotal.add(move);
        //         }
        //     }
        // }

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
        // System.out.println("DEPTH: " + depth);
        // System.out.println(board);
        if (depth == 0) {
            node = new Node();
            minimax.getTree().setRoot(node);
            board = board.copyBoard();
            // System.out.println("Set root to " + node);
        }

        if (board.state == State.BLACK_WINNER || board.state == State.WHITE_WINNER) {
            return;
        }

        if (depth > maxDepth) {
            return;
        }

        ArrayList<Move> moves = findCurrentMoves(board);

        if (depth == 0) {
            if (total == 0) {
                total = moves.size();
            }
        }
        
        if (depth == 1) {
            current++;
            System.out.println("Searched: " + current + "/" + total);
        }


        depth++;
        // System.out.println(moves);
        for (Move move : moves) {
            // Board newBoard = board.copyBoard();
            // newBoard.makeFastMove(move);
            // Node newNode = new Node (move, newBoard.totalUtility, newBoard.whitesTurn());
            // node.addMove(newNode);
            // findAllMoves(newBoard, depth, newNode);


            board.makeFastMove(move);
            Node newNode = new Node (move, board.totalUtility, board.whitesTurn());
            node.addMove(newNode);
            findAllMoves(board, depth, newNode);
            board.reverseMove();
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
        current = 0;
        total = 0;
        System.out.println("Found all moves. Calculating minimax tree.");
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
