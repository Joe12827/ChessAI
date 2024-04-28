package com;
public class Game {
    public Board board;
    public State state = State.WHITE_TURN;
    public Player whitePlayer;
    public Player blackPlayer;

    enum State {
        WHITE_TURN,
        BLACK_TURN,
        DRAW,
        WHITE_WINNER,
        BLACK_WINNER
    }

    Game(Player whitePlayer, Player blackPlayer) {
        board = new Board();
        this.whitePlayer = whitePlayer;
        this.blackPlayer = blackPlayer;
    }

    public void nextTurn() {
        if (state == State.WHITE_TURN) {
            state = State.BLACK_TURN;
        } else {
            state = State.WHITE_TURN;
        }
    }

    public void play() {
        while (true) {
            Move move = null;
            if (state == State.WHITE_TURN) {
                move = whitePlayer.getMove(board);
            } else {
                move = blackPlayer.getMove(board);
            }
            board.makeMove(move);
            nextTurn();
        }
    }

}
