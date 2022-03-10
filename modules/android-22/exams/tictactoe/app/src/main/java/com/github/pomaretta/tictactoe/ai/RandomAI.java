package com.github.pomaretta.tictactoe.ai;

import com.github.pomaretta.tictactoe.board.BoardAI;
import com.github.pomaretta.tictactoe.board.GameBoard;

public class RandomAI implements BoardAI {

    private GameBoard board;

    public RandomAI(GameBoard board) {
        this.board = board;
    }

    @Override
    public int search() throws Exception {
        if (!this.board.piecesLeft() || this.board.isGameOver()) throw new Exception("No more pieces");
        int position = (int) (Math.random() * 9);
        if (!this.board.getBoardPieces()[position].isClicked()) {
            return position;
        } else {
            return this.search();
        }
    }

}
