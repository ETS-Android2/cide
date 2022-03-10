package com.github.pomaretta.tictactoe.board;

import android.widget.Button;

public class BoardPiece {

    private Button button;
    private boolean clicked;

    /**
     * Describe quien ha pulsado la casilla,
     * si es el jugador el valor es 1, si es 0
     * es el sistema, si es -1 significa que no ha sido pulsado
     * Para representar quien lo ha pulsado necesitamos 3 modos.
     */
    public int clickedBy;

    public BoardPiece(Button button) {
        this.button = button;
        this.clickedBy = -1;
    }

    public void click(boolean clickedBy) {
        setClickedBy(clickedBy);
        this.clicked = true;
    }

    public void setClickedBy(boolean clickedBy) {
        this.clickedBy = clickedBy ? 1 : 0;
    }

    public int isClickedBy() {
        return this.clickedBy;
    }

    public boolean isClicked() {
        return clicked;
    }

    public Button getButton() {
        return button;
    }

}
