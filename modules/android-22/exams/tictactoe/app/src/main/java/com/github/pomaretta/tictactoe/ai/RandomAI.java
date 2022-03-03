package com.github.pomaretta.tictactoe.ai;

import com.github.pomaretta.tictactoe.board.BoardAI;
import com.github.pomaretta.tictactoe.board.GameBoard;

public class Adan implements BoardAI {

    private GameBoard board;

    public Adan(GameBoard board) {
        this.board = board;
    }

    @Override
    public int search() {
        if (this.board.isGameOver()) return -1;
        int position = (int) (Math.random() * 9);
        if (!this.board.getBoardPieces()[position].isClicked()) {
            return position;
        } else {
            return this.search();
        }
    }

}
