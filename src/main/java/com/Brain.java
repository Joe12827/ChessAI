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
            minimax.setRoot(node);
            board = board.copyBoard();
            // System.out.println("Set root to " + node);
        }

        if (board.state == State.BLACK_WINNER || board.state == State.WHITE_WINNER) {
            return;
        }

        if (depth == maxDepth) {
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
            // System.out.println("Searched: " + current + "/" + total);
        }

        // if (depth == 2) {
        //     System.out.println(".");
        // }


        depth++;
        // System.out.println(moves);
        for (Move move : moves) {
            // Board newBoard = board.copyBoard();
            // newBoard.makeFastMove(move);
            // Node newNode = new Node (move, newBoard.totalUtility, newBoard.whitesTurn());
            // node.addMove(newNode);
            // findAllMoves(newBoard, depth, newNode);


            board.makeFastMove(move);
            // System.out.println(board);
            Node newNode = new Node (move, board.totalUtility, board.whitesTurn());
            node.addMove(newNode);
            if (!(board.state == State.WHITE_WINNER) && !(board.state == State.BLACK_WINNER)) {
                findAllMoves(board, depth, newNode);
            }
            board.reverseMove();
        }

    }

    public MiniMax getMinimax() {
        return minimax;
    }

    public int calculateMinimax (Board board, Node node, int depth, int alpha, int beta) {
        // If node is a leaf node (no children), assign its utility as its Minimax value

        if (depth == 0) {
            node = new Node(null, board.totalUtility, board.whitesTurn());
            minimax.setRoot(node);
            board = board.copyBoard();
            // System.out.println("ROOT");
        }

        // System.out.println(board.state);

        if (board.state == State.WHITE_WINNER || board.state == State.BLACK_WINNER) {
            node.setMinimaxValue(node.totalUtility - (maxDepth - depth));
            // System.out.println("H");
            return board.totalUtility - (maxDepth - depth);
        }

        if (depth == maxDepth) {
            // System.out.println("End Utility: " + node.totalUtility);
            node.setMinimaxValue(node.totalUtility);
            return node.totalUtility;
        }

        ArrayList<Move> moves = findCurrentMoves(board);

        // Recursively calculate Minimax values for child nodes
        if (node.isMaxPlayer()) {
            // If node is a Max player, find the maximum Minimax value among its children
            int maxMinimaxValue = Integer.MIN_VALUE;
            for (Move move : moves) {
                board.makeFastMove(move);
                Node newNode = new Node(move, board.totalUtility, board.whitesTurn());
                node.addMove(newNode);

                int value = calculateMinimax(board, newNode, depth + 1, alpha, beta);
                board.reverseMove();
                maxMinimaxValue = Math.max(maxMinimaxValue, value);
                alpha = Math.max(alpha, maxMinimaxValue);
                // node.setMinimaxValue(value);
                if (beta <= alpha) { // PRUNE!!!!
                    break;
                }
                // node.setMinimaxValue(value);
            }
            node.setMinimaxValue(maxMinimaxValue);
            return maxMinimaxValue;

        } else {
            // If node is a Min player, find the minimum Minimax value among its children
            int minMinimaxValue = Integer.MAX_VALUE;
            for (Move move : moves) {
                board.makeFastMove(move);
                Node newNode = new Node (move, board.totalUtility, board.whitesTurn());
                node.addMove(newNode);

                int value = calculateMinimax(board, newNode, depth + 1, alpha, beta);
                board.reverseMove();
                minMinimaxValue = Math.min(minMinimaxValue, value);
                beta  = Math.min(beta, minMinimaxValue);
                // node.setMinimaxValue(value);
                if (beta <= alpha) { // PRUNE!!!!
                    break;
                }
                // node.setMinimaxValue(value);
                // node.setMinimaxValue(value);
            }
            node.setMinimaxValue(minMinimaxValue);
            return minMinimaxValue;     
        }
    }

    public Move findNextBestMove (Board board) {
        // System.out.println(board);
        calculateMinimax(board, null, 0, -1000, 1000);
        int bestValue = 0;
        Move bestMove = null;
        boolean first = true;

        for (Node node : minimax.getRoot().getMoves()) {
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
        System.out.println("MOVE: " + bestMove + ": " + bestValue);

        return bestMove;
    }

}
