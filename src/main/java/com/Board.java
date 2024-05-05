package com;

import java.util.ArrayList;

public class Board {
    Piece[][] tiles = new Piece[8][8];
    ArrayList<Piece> pieces = new ArrayList<>();
    double whiteUtility;
    double blackUtility;
    double totalUtility = (double)whiteUtility / blackUtility;
    State state = State.WHITE_TURN;
    ArrayList<Object[]> moveHistory = new ArrayList<>();

    enum State {
        WHITE_TURN,
        BLACK_TURN,
        DRAW,
        WHITE_WINNER,
        BLACK_WINNER
    }

    public boolean whitesTurn() {
        return (state == State.WHITE_TURN);
    }

    public Piece[][] getTiles() {
        return tiles;
    }

    Board(boolean empty) {
        if (!empty) {
            // WHITE
            tiles[0][1] = new Pawn(true, 0, 1);
            tiles[1][1] = new Pawn(true, 1, 1);
            tiles[2][1] = new Pawn(true, 2, 1);
            tiles[3][1] = new Pawn(true, 3, 1);
            tiles[4][1] = new Pawn(true, 4, 1);
            tiles[5][1] = new Pawn(true, 5, 1);
            tiles[6][1] = new Pawn(true, 6, 1);
            tiles[7][1] = new Pawn(true, 7, 1);

            tiles[0][0] = new Rook(true, 0, 0);
            tiles[1][0] = new Knight(true, 1, 0);
            tiles[2][0] = new Bishop(true, 2, 0);
            tiles[3][0] = new Queen(true, 3, 0);
            tiles[4][0] = new King(true, 4, 0);
            tiles[5][0] = new Bishop(true, 5, 0);
            tiles[6][0] = new Knight(true, 6, 0);
            tiles[7][0] = new Rook(true, 7, 0);

            // BLACK
            tiles[0][6] = new Pawn(false, 0, 6);
            tiles[1][6] = new Pawn(false, 1, 6);
            tiles[2][6] = new Pawn(false, 2, 6);
            tiles[3][6] = new Pawn(false, 3, 6);
            tiles[4][6] = new Pawn(false, 4, 6);
            tiles[5][6] = new Pawn(false, 5, 6);
            tiles[6][6] = new Pawn(false, 6, 6);
            tiles[7][6] = new Pawn(false, 7, 6);

            tiles[0][7] = new Rook(false, 0, 7);
            tiles[1][7] = new Knight(false, 1,7);
            tiles[2][7] = new Bishop(false, 2, 7);
            tiles[3][7] = new Queen(false, 3, 7);
            tiles[4][7] = new King(false, 4, 7);
            tiles[5][7] = new Bishop(false, 5, 7);
            tiles[6][7] = new Knight(false, 6, 7);
            tiles[7][7] = new Rook(false, 7, 7);

            double startingUtility = 0;
            for (int row = 7; row >= 0; row--) {
                for (int col = 0; col < 8; col++) {
                    if (tiles[col][row] != null) {
                        pieces.add(tiles[col][row]);
                        startingUtility += tiles[col][row].value;
                    }
                }
            }
            whiteUtility = startingUtility / 2;
            blackUtility = whiteUtility;
            totalUtility = 1;
        }
        
    }

    public boolean isWhite(int col, int row) {
        return tiles[col][row].isWhite();
    }

    public boolean isEmpty(int col, int row) {
        return tiles[col][row] == null;
    }

    public ArrayList<Piece> getPieces () {
        return pieces;
    }

    public boolean equals(Board otherBoard) {
        for (int row = 7; row >= 0; row--) {
            for (int col = 0; col < 8; col++) {
                if (tiles[col][row] == null && otherBoard.tiles[col][row] == null) {
                    continue;
                }
                if (tiles[col][row] != null && otherBoard.tiles[col][row] != null) {
                    if (!tiles[col][row].equals(otherBoard.tiles[col][row])) {
                        return false;
                    }
                } else if (tiles[col][row] != null || otherBoard.tiles[col][row] != null) {
                    return false;
                }
            }
        }
        return true;
    }

    public Board copyBoard () {
        Board boardCopy = new Board(true);

        // for (Piece piece : pieces) {
        //     if (!piece.killed) {
        //         Piece newPiece = piece.copyPiece();
        //         boardCopy.tiles[piece.col][piece.row] = piece.copyPiece();
        //         boardCopy.pieces.add(newPiece);
        //     }
        // }

        for (int row = 7; row >= 0; row--) {
            for (int col = 0; col < 8; col++) {
                if (this.tiles[col][row] != null) {
                    boardCopy.tiles[col][row] =  this.tiles[col][row].copyPiece();
                }
            }
        }

        boardCopy.state = state;
        boardCopy.whiteUtility = whiteUtility;
        boardCopy.blackUtility = blackUtility;
        boardCopy.totalUtility = totalUtility;
        return boardCopy;
    }

    public boolean makeMove(Move move) {
        Piece piece = tiles[move.getStart()[0]][move.getStart()[1]];

        // System.out.println("Make Move: " + move);
        if (state == State.WHITE_TURN && !piece.isWhite()) {
            System.out.println("Not Black's Turn");
            return false;
        }

        if (state == State.BLACK_TURN && piece.isWhite()) {
            System.out.println("Not White's Turn");
            return false;
        }

        ArrayList<Move> moves = piece.Moves(this);

        // System.out.println(moves);
        // System.out.println(move);

        if (!moves.contains(move)) {
            System.out.println("CANNOT MAKE THIS MOVE");
            return false;
        }

        // IF Castle
        if (piece.name == "King") {
            int newKingX = 0;
            int newKingY = 0;
            int newRookX = 0;
            int newRookY = 0;
            boolean castle = false;
            if (move.equals(new Move(4, 0, 7, 0))) { // Right White Castle
                newKingX = 6;
                newKingY = 0;
                newRookX = 5;
                newRookY = 0;
                castle = true;
            } else if (move.equals(new Move(4, 0, 0, 0))) { // Left White Castle
                newKingX = 2;
                newKingY = 0;
                newRookX = 3;
                newRookY = 0;
                castle = true;
            } else if (move.equals(new Move(4, 7, 7, 7))) { // Right Black Castle
                newKingX = 6;
                newKingY = 7;
                newRookX = 5;
                newRookY = 7;
                castle = true;
            } else if (move.equals(new Move(4, 7, 0, 7))) { // Left Black Castle
                newKingX = 2;
                newKingY = 7;
                newRookX = 3;
                newRookY = 7;
                castle = true;
            }

            if (castle) {
                tiles[newRookX][newRookY] = tiles[move.getStop()[0]][move.getStop()[1]];
                tiles[newRookX][newRookY].setLocation(newRookX, newRookY);
                tiles[newKingX][newKingY] = piece;
                piece.setLocation(newKingX, newKingY);

                tiles[move.getStop()[0]][move.getStop()[1]] = null;
                tiles[move.getStart()[0]][move.getStart()[1]] = null;

                if (state == State.WHITE_TURN) {
                    state = State.BLACK_TURN;
                } else {
                    state = State.WHITE_TURN;
                }

                moveHistory.add(new Object[]{move, tiles[newRookX][newRookY], true});
                return true;
            }
        }

        Piece stopPiece = tiles[move.getStop()[0]][move.getStop()[1]];
        moveHistory.add(new Object[]{move, stopPiece, false});

        if (tiles[move.getStop()[0]][move.getStop()[1]] != null) {
            double value = tiles[move.getStop()[0]][move.getStop()[1]].getValue();
            if (tiles[move.getStop()[0]][move.getStop()[1]].isWhite()) {
                whiteUtility -= value;
            } else {
                blackUtility -= value;
            }
            totalUtility = (double)whiteUtility / blackUtility;
            if (tiles[move.getStop()[0]][move.getStop()[1]] instanceof King) {
                if (state == State.WHITE_TURN) {
                    state = State.WHITE_WINNER;
                    blackUtility -= 100000;
                } else {
                    state = State.BLACK_WINNER;
                    whiteUtility -= 100000;
                }
            }
            tiles[move.getStop()[0]][move.getStop()[1]].kill();
            totalUtility = (double)whiteUtility / blackUtility;
        }

        tiles[move.getStop()[0]][move.getStop()[1]] = piece;
        tiles[move.getStart()[0]][move.getStart()[1]] = null;
        piece.setLocation(move.getStop()[0], move.getStop()[1]);

        if (state == State.WHITE_TURN) {
            state = State.BLACK_TURN;
        } else {
            state = State.WHITE_TURN;
        }

        // System.out.println(this);
        return true;
    }

    public boolean makeFastMove(Move move) { // Make a move assuming its already valid
        Piece piece = tiles[move.getStart()[0]][move.getStart()[1]];
        // System.out.println("Fast move: " + move);

        // IF Castle
        if (piece.name == "King") {
            // System.out.println(move);
            int newKingX = 0;
            int newKingY = 0;
            int newRookX = 0;
            int newRookY = 0;
            boolean castle = false;
            if (move.equals(new Move(4, 0, 7, 0))) { // Right White Castle
                newKingX = 6;
                newKingY = 0;
                newRookX = 5;
                newRookY = 0;
                castle = true;
            } else if (move.equals(new Move(4, 0, 0, 0))) { // Left White Castle
                newKingX = 2;
                newKingY = 0;
                newRookX = 3;
                newRookY = 0;
                castle = true;
            } else if (move.equals(new Move(4, 7, 7, 7))) { // Right Black Castle
                newKingX = 6;
                newKingY = 7;
                newRookX = 5;
                newRookY = 7;
                castle = true;
            } else if (move.equals(new Move(4, 7, 0, 7))) { // Left Black Castle
                newKingX = 2;
                newKingY = 7;
                newRookX = 3;
                newRookY = 7;
                castle = true;
            }

            if (castle) {
                tiles[newRookX][newRookY] = tiles[move.getStop()[0]][move.getStop()[1]];
                tiles[newRookX][newRookY].setLocation(newRookX, newRookY);
                tiles[newKingX][newKingY] = piece;
                piece.setLocation(newKingX, newKingY);

                tiles[move.getStop()[0]][move.getStop()[1]] = null;
                tiles[move.getStart()[0]][move.getStart()[1]] = null;

                if (state == State.WHITE_TURN) {
                    state = State.BLACK_TURN;
                } else {
                    state = State.WHITE_TURN;
                }

                moveHistory.add(new Object[]{move, tiles[newRookX][newRookY], true});
                return true;
            }
        }


        Piece stopPiece = tiles[move.getStop()[0]][move.getStop()[1]];
        moveHistory.add(new Object[]{move, stopPiece, false});
        if (stopPiece != null) {
            double value = stopPiece.getValue();
            if (stopPiece.isWhite()) {
                whiteUtility -= value;
            } else {
                blackUtility -= value;
            }

            stopPiece.kill();
            if (stopPiece.name == "King") {
                // System.out.println("WINNER");
                if (state == State.WHITE_TURN) {
                    state = State.WHITE_WINNER;
                    blackUtility -= 100000;
                } else {
                    state = State.BLACK_WINNER;
                    whiteUtility -= 100000;
                }
            }
            totalUtility = (double)whiteUtility / blackUtility;
        }

        tiles[move.getStop()[0]][move.getStop()[1]] = piece;
        tiles[move.getStart()[0]][move.getStart()[1]] = null;
        piece.setLocation(move.getStop()[0], move.getStop()[1]);

        if (state == State.WHITE_TURN) {
            state = State.BLACK_TURN;
        } else if (state == State.BLACK_TURN) {
            state = State.WHITE_TURN;
        }

        // System.out.println(this);
        return true;
    }


    public void reverseMove () {
        if (moveHistory.size() == 0) {
            System.out.println("No last move");
            return;
        }
        Move lastMove = (Move)moveHistory.getLast()[0];
        Piece stopPiece = (Piece)moveHistory.getLast()[1]; // Piece that was taken

        if ((Boolean)moveHistory.getLast()[2]) {
            int oldKingX = 0;
            int oldKingY = 0;
            int oldRookX = 0;
            int oldRookY = 0;
            boolean castle = false;
            if (lastMove.equals(new Move(4, 0, 7, 0))) { // Right White Castle
                oldKingX = 6;
                oldKingY = 0;
                oldRookX = 5;
                oldRookY = 0;
                castle = true;
            } else if (lastMove.equals(new Move(4, 0, 0, 0))) { // Left White Castle
                oldKingX = 2;
                oldKingY = 0;
                oldRookX = 3;
                oldRookY = 0;
                castle = true;
            } else if (lastMove.equals(new Move(4, 7, 7, 7))) { // Right Black Castle
                oldKingX = 6;
                oldKingY = 7;
                oldRookX = 5;
                oldRookY = 7;
                castle = true;
            } else if (lastMove.equals(new Move(4, 7, 0, 7))) { // Left Black Castle
                oldKingX = 2;
                oldKingY = 7;
                oldRookX = 3;
                oldRookY = 7;
                castle = true;
            }

            if (castle) {
                // System.out.println("REVERSING CASTLE");
                // System.out.println(lastMove);
                tiles[lastMove.getStart()[0]][lastMove.getStart()[1]] = tiles[oldKingX][oldKingY];
                tiles[lastMove.getStop()[0]][lastMove.getStop()[1]] = stopPiece;
                tiles[lastMove.getStart()[0]][lastMove.getStart()[1]].setLocation(lastMove.getStart()[0], lastMove.getStart()[1]);
                stopPiece.setLocation(lastMove.getStop()[0], lastMove.getStop()[1]);

                tiles[oldKingX][oldKingY] = null;
                tiles[oldRookX][oldRookY] = null;

                // Due to revsersing castle, set the rook and king to castable again
                tiles[lastMove.getStart()[0]][lastMove.getStart()[1]].setCastleable();
                stopPiece.setCastleable();

                if (state == State.WHITE_TURN) {
                    state = State.BLACK_TURN;
                } else {
                    state = State.WHITE_TURN;
                }
                moveHistory.removeLast();
                return;
            }
        }

        tiles[lastMove.getStart()[0]][lastMove.getStart()[1]] = tiles[lastMove.getStop()[0]][lastMove.getStop()[1]];
        tiles[lastMove.getStart()[0]][lastMove.getStart()[1]].setLocation(lastMove.getStart()[0], lastMove.getStart()[1]);
        tiles[lastMove.getStop()[0]][lastMove.getStop()[1]] = stopPiece;
        if (stopPiece != null) {
            stopPiece.setLocation(lastMove.getStop()[0], lastMove.getStop()[1]);
            if (stopPiece.isWhite()) {
                whiteUtility += stopPiece.value;
            } else {
                blackUtility += stopPiece.value;
            }
            totalUtility = (double)whiteUtility / blackUtility;
        }

        if (state == State.WHITE_TURN) {
            state = State.BLACK_TURN;
        } else if (state == State.BLACK_TURN)  {
            state = State.WHITE_TURN;
        } else if (state == State.WHITE_WINNER)  {
            state = State.WHITE_TURN;
            blackUtility += 100000;
        } else if (state == State.BLACK_WINNER)  {
            state = State.BLACK_TURN;
            blackUtility += 100000;
        }

        totalUtility = (double)whiteUtility / blackUtility;

        moveHistory.removeLast();
    }

    @Override
    public String toString() {
        String str = "";
        str += "-----------------";
        str += "\n";
        for (int r = 7; r >= 0; r--) {
            for (int c = 0; c < 8; c++) {
                str += "|";
                if (tiles[c][r] != null) {
                    str += tiles[c][r];
                } else {
                    str += " ";
                }
            }
            str += "|";
            if (r == 4) {
                str += " B: " + String.format ("%.3f", blackUtility);
            }
            if (r == 3) {
                str += " W: " + String.format ("%.3f", whiteUtility);
            }
            str += "\n";
            str += "-----------------";
            if (r == 4) {
                if (state == State.WHITE_TURN) {
                    str += " Turn: WHITE";
                } else {
                    str += " Turn: BLACK";
                }
                str += "  Ratio: " + String.format ("%.3f", totalUtility);
            }
            str += "\n";
        }

    
        // str += brain.getMinimax().toString();

        // if (brain.white && state == State.WHITE_TURN) {
        //     str += brain.findCurrentMoves(this);
        // } else {
        //     str += brain.findCurrentMoves(this);
        // }
        return str;
    }
}