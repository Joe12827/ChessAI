package com;

import java.util.ArrayList;
import java.util.Random;

import com.Board.State;

public class Brain {
    boolean white;
    boolean maxPlayer;
    int maxDepth;
    int currentDepth = 0;
    int current = 0;
    int total = 0;


    MiniMax minimax;
    TranspositionTable stateTable;

    Brain (boolean white, int maxDepth, Board board) {
        this.white = white;
        maxPlayer = white;
        this.maxDepth = maxDepth;
        minimax = new MiniMax(board);
        stateTable = new TranspositionTable();
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

    public static double utilityDifference (Board board) {
        double whiteUtility = board.whiteUtility;
        double blackUtility = board.blackUtility;
        return (((whiteUtility/(blackUtility + 1))+(blackUtility/(whiteUtility + 1)))-1) * (whiteUtility - blackUtility);
    }

    public void orderMoves(ArrayList<Move> moves, Board board) {
        ArrayList<MoveUtility> moveUtilityList = new ArrayList<>();
        double newUtility;
        double currentUtility;
        double utilityChange; // Always positive
        boolean whitesTurn = board.whitesTurn();
        
        for (Move move : moves) {

            currentUtility = utilityDifference(board);
            board.makeFastMove(move);
            newUtility = utilityDifference(board);
            board.reverseMove();

            if (board.state == State.WHITE_TURN) {
                utilityChange = currentUtility - newUtility;
            } else {
                utilityChange = newUtility - currentUtility;
            }
            moveUtilityList.add(new MoveUtility(move, utilityChange));
        }
        moveUtilityList.sort(null);
        moves.clear();
        for (MoveUtility moveUtility : moveUtilityList) {
            moves.add(moveUtility.move);
        }
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
            Node newNode = new Node (move, utilityDifference(board), board.whitesTurn());
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


    public double calculateMinimax (Board board, Node node, int depth, double alpha, double beta) {
        // If node is a leaf node (no children), assign its utility as its Minimax value
        if (depth == 0) {
            node = new Node(null, boardEvaluation(board), board.whitesTurn());
            minimax.setRoot(node);
            board = board.copyBoard();
            // System.out.println("ROOT");
        }

        ArrayList<Move> moves = findCurrentMoves(board);
        orderMoves(moves, board);
        // System.out.println(board.state);

        if (board.state == State.WHITE_WINNER || board.state == State.BLACK_WINNER) {
            node.setMinimaxValue(node.totalUtility);
            System.out.println("H");
            return boardEvaluation(board);
        }

        if (depth == maxDepth) {
            // if (node.totalUtility > 100 || node.totalUtility < -100) {
            //     System.out.println("End Utility: " + node.totalUtility);
            // }
            
            node.setMinimaxValue(node.totalUtility);
            return node.totalUtility;
        }

        // Recursively calculate Minimax values for child nodes
        if (node.isMaxPlayer()) {
            // If node is a Max player, find the maximum Minimax value among its children
            double maxMinimaxValue = Integer.MIN_VALUE;
            for (Move move : moves) {
                board.makeFastMove(move);
                Node newNode = new Node(move, boardEvaluation(board), !node.isMaxPlayer());
                node.addMove(newNode);

                double value = calculateMinimax(board, newNode, depth + 1, alpha, beta);
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
            double minMinimaxValue = Integer.MAX_VALUE;
            for (Move move : moves) {
                board.makeFastMove(move);
                Node newNode = new Node (move, boardEvaluation(board), !node.isMaxPlayer());
                node.addMove(newNode);

                double value = calculateMinimax(board, newNode, depth + 1, alpha, beta);
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

    public double boardEvaluation (Board board) {
        double pieceValueRatio = utilityDifference(board);
        double moveCount = findCurrentMoves(board).size();

        if (!board.whitesTurn()) {
            moveCount = moveCount * -1;
        }

        double eval = pieceValueRatio;
        eval += (moveCount / 5000);

        return eval;
    }

    public Move findNextBestMove (Board board) {
        // System.out.println(board);

        Move bestMove = null;
        bestMove = stateTable.checkState(board, maxDepth);
        if (bestMove != null) {
            System.out.println("State in state table (no calc needed)");
            return bestMove;
        }

        Random rand = new Random();
        calculateMinimax(board, null, 0, -100000, 100000);
        double bestValue;
        if (board.whitesTurn()) {
            bestValue = 0;
        } else {
            bestValue = 100;
        }

        boolean first = true;
        boolean tie = false;
        System.out.println(minimax.getRoot());
        for (Node node : minimax.getRoot().getMoves()) {
            // System.out.println(node.getMinimaxValue());
            if (first) {
                first = false;
                bestValue = node.getMinimaxValue();
                bestMove = node.getMove();
            } else if (board.whitesTurn()) {
                if (node.getMinimaxValue() > bestValue) {
                    bestValue = node.getMinimaxValue();
                    bestMove = node.getMove();
                } else if (node.getMinimaxValue() == bestValue && rand.nextBoolean()) {
                    bestMove = node.getMove();
                }
            } else {
                if (node.getMinimaxValue() < bestValue) {
                    bestValue = node.getMinimaxValue();
                    bestMove = node.getMove();
                } else if (node.getMinimaxValue() == bestValue && rand.nextBoolean()) {
                    bestMove = node.getMove();
                }
            }
        }
        System.out.printf("MOVE: " + bestMove + ": %.3f\n", bestValue);
        if (!tie) { // If there was a clear best move add it (no ties)
            System.out.println("ADDED STATE");
            stateTable.addMove(board, maxDepth, bestMove);
        } else {
            System.out.println("TIE");
        }
        
        return bestMove;
    }

}
