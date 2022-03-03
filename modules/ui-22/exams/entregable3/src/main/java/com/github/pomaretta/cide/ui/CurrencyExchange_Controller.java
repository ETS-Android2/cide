package com.github.pomaretta.cide.ui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class CurrencyExchange_Controller implements Initializable {
    
    @FXML
    private TextField userEntry;
    @FXML
    private Button calc;

    /**
    * Coin Fields
    */
    @FXML
    private TextField coin1;
    @FXML
    private TextField coin2;
    @FXML
    private TextField coin3;
    @FXML
    private TextField coin4;
    @FXML
    private TextField coin5;
    @FXML
    private TextField coin6;
    @FXML
    private TextField coin7;
    @FXML
    private TextField coin8;
    @FXML
    private TextField note1;
    @FXML
    private TextField note2;
    @FXML
    private TextField note3;
    @FXML
    private TextField note4;
    @FXML
    private TextField note5;
    @FXML
    private TextField note6;
    @FXML
    private TextField note7;

    private TextField[] exchangeFields;

    private Float[] exchangeCurrencies = {
        0.01f, 0.02f,0.05f,
        0.1f, 0.2f, 0.5f,
        1.0f, 2.0f, 5.0f, 
        10.0f, 20.0f, 50.0f, 
        100.0f, 200.0f, 500.0f
    };

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.calc.setOnAction(ev -> calc());

        this.exchangeFields = new TextField[]{
            coin1, coin2, coin3,
            coin4, coin5, coin6,
            coin7, coin8, note1,
            note2, note3, note4,
            note5, note6, note7
        };

    }

    private void resetFields() {
        for (TextField field : exchangeFields) {
            field.setText("");
        }
    }

    private void calc() {

        // Reiniciamos los campos.
        this.resetFields();

        final String userInput = this.userEntry.getText();
        float userInputFloat = 0f; 
        
        try {
            userInputFloat = Float.parseFloat(userInput);
            // Miramos si el usuario ha introducido un número negativo
            if (userInputFloat <= 0) {                
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Entrada inválida");
            alert.setContentText("Introduce un número positivo.");
            alert.showAndWait();
            return;
        }

        // Calculate exchange
        for (int i = (exchangeFields.length - 1); i >= 0; i--) {
            
            float exchange = exchangeCurrencies[i];

            // Si el valor del usuario es menor que el valor de la moneda seguimos buscando monedas.
            if (userInputFloat < exchange) continue;
            
            int coins = 0;

            while (userInputFloat >= exchange) {
                // Arreglar la pérdida de decimales
                userInputFloat = (float) Math.round(userInputFloat * 100.0f) / 100.0f;
                // EL valor inicial pierde valor
                userInputFloat -= exchange;
                // Sumamos en número de monedas.
                coins++;
            }

            // Aplicamos el resultado al campo.
            this.exchangeFields[i].setText(String.valueOf(coins));
        }


    }

}
