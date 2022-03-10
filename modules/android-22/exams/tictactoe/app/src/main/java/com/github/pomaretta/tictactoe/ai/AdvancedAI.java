package com.github.pomaretta.tictactoe.ai;

import com.github.pomaretta.tictactoe.board.BoardAI;
import com.github.pomaretta.tictactoe.board.BoardPiece;
import com.github.pomaretta.tictactoe.board.GameBoard;

public class AdvancedAI implements BoardAI {

    private GameBoard board;
    private boolean hard;
    private boolean playerTurn;

    public AdvancedAI(GameBoard board, boolean hard, boolean playerTurn) {
        this.board = board;
        this.hard = hard;
        this.playerTurn = playerTurn;
    }

    @Override
    public int search() throws Exception {
        return this.bestMove(this.board.getBoardPieces());
    }

    private int randomMove(BoardPiece[] pieces) throws Exception {
        if (!this.board.piecesLeft() || this.board.isGameOver()) throw new Exception("No more pieces");
        int position = (int) (Math.random() * 9);
        if (!pieces[position].isClicked()) {
            return position;
        } else {
            return this.randomMove(pieces);
        }
    }

    private int bestMove(BoardPiece[] pieces) throws Exception {

        int bestMove = -1;
        for (int i = 0; i < pieces.length; i++) {

            // Descartamos si está clickado
            if (pieces[i].isClicked()) continue;

            int playerFlag = this.playerTurn ? 1 : 0;
            int aiFlag = this.playerTurn ? 0 : 1;


            // Clickamos para la AI
            pieces[i].clickedBy = aiFlag;
            if (this.board.isWinner(pieces, aiFlag)) {
                bestMove = i;
                pieces[i].clickedBy = -1;
                if (hard) break;
            }
            pieces[i].clickedBy = -1;

            // Clickamos para el player
            pieces[i].clickedBy = playerFlag;
            if (this.board.isWinner(pieces, playerFlag)) {
                bestMove = i;
                pieces[i].clickedBy = -1;
                break;
            }
            pieces[i].clickedBy = -1;
        }

        if (bestMove != -1) return bestMove;

        // Random
        return randomMove(pieces);
    }

}
