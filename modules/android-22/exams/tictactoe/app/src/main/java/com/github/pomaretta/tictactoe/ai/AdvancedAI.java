package com.github.pomaretta.tictactoe.ai;

import com.github.pomaretta.tictactoe.board.BoardAI;
import com.github.pomaretta.tictactoe.board.BoardPiece;
import com.github.pomaretta.tictactoe.board.GameBoard;

import java.util.ArrayList;

public class MediumAI implements BoardAI {

    private GameBoard board;

    public MediumAI(GameBoard board) {
        this.board = board;
    }

    @Override
    public int search() throws Exception {
        return this.search(this.board.getBoardPieces());
    }

    private BoardPiece[] getEmptyIndexes(BoardPiece[] boardPieces) {
        ArrayList<BoardPiece> pieces = new ArrayList<>();
        for (BoardPiece boardPiece : boardPieces) {
            if (boardPiece.isClicked()) {
                pieces.add(null);
                continue;
            }
            pieces.add(boardPiece);
        }
        return pieces.toArray(new BoardPiece[0]);
    }

    private int search(BoardPiece[] pieces) {

        BoardPiece[] emptyIndexes = this.getEmptyIndexes(pieces);

        int bestMove = -1;
        for (int i = 0; i < emptyIndexes.length; i++) {

            // Descartamos si está clickado
            if (emptyIndexes[i] == null) continue;

            // Clickamos para la AI
            pieces[i].clickedBy = 0;
            if (this.board.isWinner(pieces, 0)) {
                bestMove = i;
                pieces[i].clickedBy = -1;
                break;
            }

            // Clickamos para el player
            pieces[i].clickedBy = 1;
            if (this.board.isWinner(pieces, 1)) {
                bestMove = i;
            }

            pieces[i].clickedBy = -1;
        }

        if (bestMove != -1) return bestMove;

        for (int i = 0; i < emptyIndexes.length; i++) {
            if (emptyIndexes[i] == null) continue;
            return i;
        }

        return -1;
    }

}
