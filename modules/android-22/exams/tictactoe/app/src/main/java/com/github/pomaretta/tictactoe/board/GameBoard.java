package com.github.pomaretta.tictactoe;

import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.gridlayout.widget.GridLayout;

public class GameBoard {

    private final GridLayout gameLayout;
    private final Difficulty difficulty;

    private BoardPiece[] boardPieces;
    private BoardAI boardAI;
    private boolean turn;
    private boolean gameOver;

    public GameBoard(GridLayout layout, Difficulty difficulty) {
        this.gameLayout = layout;
        this.difficulty = difficulty;
    }

    public void start() {

        // Vaciar el grid layout.
        this.gameLayout.removeAllViews();
        this.boardPieces = new BoardPiece[9];

        // Empieza el player
        this.turn = true;

        this.populateBoard(3,3);

        // Start AI
        switch (this.difficulty) {
            case EASY:
                this.boardAI = new Adan(this);
            case MEDIUM:
                this.boardAI = new Joshua(this);
        }

        // Create IntelligenceHandler
        new IntelligenceHandler(this).start();
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public BoardPiece[] getBoardPieces() {
        return boardPieces;
    }

    public GridLayout getGameLayout() {
        return gameLayout;
    }

    public BoardAI getBoardAI() {
        return boardAI;
    }

    public boolean isTurn() {
        return turn;
    }

    private void populateBoard(int rows, int columns) {

        for (int i = 0; i < (rows * columns); i++) {
            Button button = new Button(this.gameLayout.getContext());
            button.setLayoutParams(new ViewGroup.LayoutParams(200,200));
            // button.setBackgroundResource(R.drawable.game_o);
            button.setBackgroundColor((int) R.color.purple_500);
            BoardPiece piece = new BoardPiece(button);

            piece.getButton().setOnClickListener(ev -> {
                // If this isClicked do nothing
                if (piece.isClicked()) return;
                if (!isTurn()) {
                    System.out.println("No es el turno del jugador");
                    return;
                }
                fillButton(piece);
            });

            this.boardPieces[i] = piece;
            this.gameLayout.addView(piece.getButton());
        }
    }

    public void fillButton(BoardPiece piece) {
        if (isTurn()) {
            piece.getButton().setBackgroundResource(R.drawable.game_x);
        } else {
            piece.getButton().setBackgroundResource(R.drawable.game_o);
        }
        piece.setClicked(true);
        this.turn = !this.turn;
    }

}
