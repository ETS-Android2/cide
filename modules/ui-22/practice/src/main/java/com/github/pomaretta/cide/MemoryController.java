package com.github.pomaretta.cide;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.GridPane;

public class MemoryController implements Initializable {

    class MemoryCard {

        private ToggleButton button;
        private String color;

        private boolean destroyed;
        private boolean opened;

        public MemoryCard(ToggleButton button, String color) {
            this.button = button;
            this.color = color;
        }

        public void reset() {
            this.button.setStyle(
                "-fx-background-color: rgba(0,0,0,1);"
            );
            this.opened = false;
        }

        public void destroy() {
            this.button.setStyle(
                "-fx-background-color: rgba(0,0,0,0);"
            );
            this.destroyed = true;
        }

        public ToggleButton getButton() {
            return button;
        }

        public String getColor() {
            return color;
        }

        public boolean isDestroyed() {
            return destroyed;
        }

        public void open() {
            this.button.setStyle(
                "-fx-background-color: " + this.color + ";"
            );
            this.opened = true;
        }

        public boolean isOpened() {
            return opened;
        }

    }

    @FXML
    private GridPane gameZone;

    private ArrayList<MemoryCard> pair;
    private MemoryCard[] cards;
    private HashMap<Integer,Integer> assigned;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gameZone.setHgap(25);
        start();
    }

    private void start() {

        // Pairs of colors
        pair = new ArrayList<>();
        cards = new MemoryCard[6];
        assigned = new HashMap<>();

        // Colors
        String[] colors = new String[] {
            "red", "green", "blue"
        };

        // Fill assigned pairs
        for (int i = 0; i < colors.length; i++) {
            assigned.put(i, 0);
        }
        
        for (int i = 0; i < cards.length; i++) {
            
            // Create button
            ToggleButton button = new ToggleButton();
            button.setPrefSize(100, 100);
            // Set button style
            String color =  colors[getColorIndex()];
            // Get card
            MemoryCard card = new MemoryCard(button, color);

            // Set style to button
            button.setStyle(
                "-fx-background-color: rgba(0,0,0,1);"
            );

            card.button.setOnAction(event -> {

                // If pair is full and colors are not equal reset cards
                if (pair.size() == 2 && !pair.get(0).getColor().equals(pair.get(1).getColor())) {
                    pair.get(0).reset();
                    pair.get(1).reset();
                    pair.clear();
                    checkGame();
                    return;
                }

                // If card is not destroyed
                if (card.isDestroyed()) return;

                // If card is opened
                if (card.isOpened()) return;

                if (pair.size() == 1 && pair.get(0).isOpened() && pair.get(0).getColor().equals(color)) { 
                    // Destroy cards
                    pair.get(0).destroy();
                    card.destroy();
                    pair.clear();
                    checkGame();
                    return;
                }

                card.open();
                pair.add(card);
            });

            // Add card to deck
            cards[i] = card;
            // Add button to grid
            gameZone.add(card.button, i, 0);
        }

    }

    private int getColorIndex() {
        int random = (int) (Math.random() * 3);
        if (assigned.get(random) == 0 || assigned.get(random) == 1) {
            assigned.put(random, assigned.get(random) + 1);
            return random;
        } else {
            return getColorIndex();
        }
    }

    private void checkGame() {
        for (MemoryCard card : cards) {
            if (!card.isDestroyed()) return;
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Congratulations!");
        alert.setHeaderText("You won!");
        alert.setContentText("You have won the game!");
        alert.showAndWait();
        start();
    }

}