package com.github.pomaretta.cide.ui;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class SimonetaController implements Initializable {
    
    @FXML
    private GridPane gridPane;
    @FXML
    private Button startButton;
    @FXML
    private Button guideButton;

    private ArrayList<String> colors = new ArrayList<String>();
    
    private ArrayList<SimonetaButton> buttons = new ArrayList<SimonetaButton>();
    private ArrayList<SimonetaButton> pressedButtons = new ArrayList<SimonetaButton>();

    class SimonetaButton {

        private int id;
        private Button button;

        public SimonetaButton(int id, Button button) {
            this.id = id;
            this.button = button;
        }

        public int getId() {
            return id;
        }

        public Button getButton() {
            return button;
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        colors.add("rgba(0,255,0,1)");
        colors.add("rgba(255,255,0,1)");
        colors.add("rgba(255,0,0,1)");
        colors.add("rgba(0,0,255,1)");
        
        startButton.setOnAction(event -> {
            try {
                startGameNumbers();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        guideButton.setOnAction(event -> {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Simoneta");
            alert.setHeaderText("Simoneta Guide");
            alert.setContentText(
                "Instrucciones para jugar\n\nEdad mínima: 6 años\n\nÚnico jugador\n\nPresiona el botón de start para empezar la partida y se te mostrará una secuencia de colores. Acierta la secuencia y conseguirás convertirte en el ganador de la partida."
            );

            alert.showAndWait();
        });

    }

    private void startGame() throws InterruptedException {

        // colors.sort((o1, o2) -> (int) (Math.random() * 3) - 1);

        // Clear the grid
        gridPane.getChildren().clear();
        buttons.clear();
        pressedButtons.clear();

        for (int i = 0; i < 4; i++) {
            Button button = new Button();
            final int index = i;
            SimonetaButton simonetaButton = new SimonetaButton(i, button);

            simonetaButton.getButton().setPrefSize(200, 200);

            simonetaButton.getButton().setStyle(
                String.format(
                    "-fx-background-color: %s;",
                    colors.get(i)
                )
            );

            simonetaButton.getButton().setOnAction(e -> {

                simonetaButton.getButton().setStyle(
                    String.format(
                        "-fx-background-color: %s;",
                        colors.get(index).replace("1", "0.5")
                    )
                );

                // button.setText(
                //     String.valueOf(index)
                // );

                pressedButtons.add(
                    simonetaButton
                );

                if (pressedButtons.size() == buttons.size()) {
                    checkGame();
                }

            });

            gridPane.add(simonetaButton.getButton(), i / 2, i % 2);
            buttons.add(simonetaButton);
        }

        for (int i = 0; i < 4; i++) {
            SimonetaButton b = buttons.get(i);
            buttons.remove(b);
            buttons.add((int) (Math.random() * buttons.size()), b);
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (SimonetaButton b : buttons) {
                        String style = b.getButton().getStyle();
                        b.getButton().setStyle(style.replace("1", "0.5"));
                        Thread.sleep(1500);
                        b.getButton().setStyle(style);
                    } 
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }).start();

    }

    private void startGameNumbers() throws InterruptedException {

        // colors.sort((o1, o2) -> (int) (Math.random() * 3) - 1);

        // Clear the grid
        gridPane.getChildren().clear();
        buttons.clear();
        pressedButtons.clear();

        for (int i = 0; i < 4; i++) {
            Button button = new Button();
            final int index = i;
            SimonetaButton simonetaButton = new SimonetaButton(i, button);

            simonetaButton.getButton().setPrefSize(200, 200);

            simonetaButton.getButton().setStyle(
                String.format(
                    "-fx-background-color: %s;",
                    colors.get(i)
                )
            );

            simonetaButton.getButton().setOnAction(e -> {

                simonetaButton.getButton().setStyle(
                    String.format(
                        "-fx-background-color: %s;",
                        colors.get(index).replace("1", "0.5")
                    )
                );

                simonetaButton.getButton().setText(
                    String.valueOf(simonetaButton.getId())
                );

                pressedButtons.add(
                    simonetaButton
                );

                if (pressedButtons.size() == buttons.size()) {
                    checkGame();
                }

            });

            gridPane.add(simonetaButton.getButton(), i / 2, i % 2);
            buttons.add(simonetaButton);
        }

        for (int i = 0; i < 4; i++) {
            SimonetaButton b = buttons.get(i);
            buttons.remove(b);
            buttons.add((int) (Math.random() * buttons.size()), b);
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (SimonetaButton b : buttons) {
                        String style = b.getButton().getStyle();
                        b.getButton().setStyle(style.replace("1", "0.5"));
                        b.getButton().setText(String.valueOf(b.getId()));
                        Thread.sleep(1500);
                        b.getButton().setStyle(style);
                        b.getButton().setText("");
                    } 
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }).start();

    }

    private void checkGame() {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Simoneta");
        alert.setHeaderText("Game Over");

        boolean win = true;
        for (int i = 0; i < pressedButtons.size(); i++) {
            if (pressedButtons.get(i).getId() != buttons.get(i).getId()) {
                win = false;
                break;
            }
        }

        alert.setContentText(win ? "Has ganado" : "Has perdido");
        alert.showAndWait();
    }

}
