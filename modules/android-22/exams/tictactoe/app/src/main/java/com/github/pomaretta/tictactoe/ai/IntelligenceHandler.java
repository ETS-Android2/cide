package com.github.pomaretta.tictactoe;

public class IntelligenceHandler extends Thread {

    private GameBoard board;

    public IntelligenceHandler(GameBoard board) {
        this.board = board;
    }

    @Override
    public void run() {

        while (!board.isGameOver()) {
            try {

                // Wait to AI Turn
                if (board.isTurn()) {
                    // CPU Comsuption
                    sleep(100);
                    return;
                }

                // Search position
                int position = this.board.getBoardAI().search();
                System.out.println("Position: " + position);

                // Thread Sleep 1.5 Seconds
                // sleep(1500);

                // Execute fillButton
                //this.board.fillButton(this.board.getBoardPieces()[position]);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
