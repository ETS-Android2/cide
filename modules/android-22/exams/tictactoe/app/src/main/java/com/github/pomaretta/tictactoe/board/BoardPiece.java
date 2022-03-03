package com.github.pomaretta.tictactoe;

import android.widget.Button;

public class BoardPiece {

    private Button button;
    private boolean clicked;

    public BoardPiece(Button button) {
        this.button = button;
    }

    public void setClicked(boolean clicked) {
        this.clicked = clicked;
    }

    public boolean isClicked() {
        return clicked;
    }

    public Button getButton() {
        return button;
    }

}
