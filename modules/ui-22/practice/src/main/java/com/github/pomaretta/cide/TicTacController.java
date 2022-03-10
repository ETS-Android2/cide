package com.github.pomaretta.cide;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

public class TicTacController implements Initializable {

    @FXML
    private Button socket1;
    @FXML
    private Button socket2;
    @FXML
    private Button socket3;
    @FXML
    private Button socket4;
    @FXML
    private Button socket5;
    @FXML
    private Button socket6;
    @FXML
    private Button socket7;
    @FXML
    private Button socket8;
    @FXML
    private Button socket9;

    private Button[] sockets;

    private boolean isXTurn = false;
    private boolean gameOver = false;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        this.sockets = new Button[] {
            socket1, socket2, socket3,
            socket4, socket5, socket6,
            socket7, socket8, socket9
        };

        // Set up the Handler.
        for (Button b : this.sockets) {
            b.setOnAction(e -> {
                this.handleOnClick(e);
            });
        }

    }

    private void checkGameOver() {
        for (Button b : this.sockets) {
            if (b.getText().equals("")) {
                return;
            }
        }
        this.gameOver = true;
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Game Over");
        alert.setHeaderText("Game Over");
        alert.setContentText("The game is over.  Thanks for playing!");
        alert.showAndWait();
        System.exit(0);
    }

    private void handleOnClick(ActionEvent e) {

        Button b = (Button) e.getSource();

        if (!b.getText().equals("")) return;

        b.setText("X");

        if (!this.isXTurn) {
            b.setText("O");
        }

        
        if (this.isWinner(this.isXTurn ? "X" : "O")) {
            this.gameOver = true;
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Game Over");
            alert.setHeaderText("Game Over");
            alert.setContentText(
                String.format(
                    "Player %s.  Thanks for playing!", 
                    this.isXTurn ? "AI" : "Carlos"
                )
            );
            alert.showAndWait();
            System.exit(0);
        }

        this.isXTurn = !this.isXTurn;

        this.checkGameOver();

        // AI
        int position = 0;
        try {
            position = this.searchSocket();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        this.handleOnClick(this.sockets[position]);
    }

    private void handleOnClick(Button target) {

        if (this.gameOver) return;

        Button b = target;

        if (!b.getText().equals("")) return;

        b.setText("X");

        if (!this.isXTurn) {
            b.setText("O");
        }

        if (this.isWinner(this.isXTurn ? "X" : "O")) {
            this.gameOver = true;
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Game Over");
            alert.setHeaderText("Game Over");
            alert.setContentText(
                String.format(
                    "Player %s.  Thanks for playing!", 
                    this.isXTurn ? "AI" : "Carlos"
                )
            );
            alert.showAndWait();
            System.exit(0);
        }

        this.isXTurn = !this.isXTurn;
    }

    private boolean isGameOver() {
        return this.gameOver;
    }

    private boolean isWinner(String player) {
        if (
            this.socket1.getText().equals(player) && this.socket2.getText().equals(player) && this.socket3.getText().equals(player)
            ||
            this.socket4.getText().equals(player) && this.socket5.getText().equals(player) && this.socket6.getText().equals(player)
            ||
            this.socket7.getText().equals(player) && this.socket8.getText().equals(player) && this.socket9.getText().equals(player)
            ||
            this.socket1.getText().equals(player) && this.socket4.getText().equals(player) && this.socket7.getText().equals(player)
            ||
            this.socket2.getText().equals(player) && this.socket5.getText().equals(player) && this.socket8.getText().equals(player)
            ||
            this.socket3.getText().equals(player) && this.socket6.getText().equals(player) && this.socket9.getText().equals(player)
            ||
            this.socket1.getText().equals(player) && this.socket5.getText().equals(player) && this.socket9.getText().equals(player)
            ||
            this.socket3.getText().equals(player) && this.socket5.getText().equals(player) && this.socket7.getText().equals(player)
        ) return true;
        return false;
    }

    private int searchSocket() throws Exception {
        if (this.isGameOver()) {
            throw new Exception("Game is over.");
        }
        int position = (int) (Math.random() * 9);
        if (this.sockets[position].getText().equals("")) {
            return position;
        } else {
            return this.searchSocket();
        }
    }

}
