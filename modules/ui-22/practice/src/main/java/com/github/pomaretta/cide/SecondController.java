package com.github.pomaretta.cide;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;

public class SecondController {
    
    @FXML
    private TextField operador1;
    @FXML
    private TextField operador2;
    @FXML
    private TextField resultado;

    @FXML
    private ToggleButton suma;
    @FXML
    private ToggleButton restar;
    @FXML
    private ToggleButton multiplicar;
    @FXML
    private ToggleButton dividir;

    @FXML
    private ToggleGroup Operands;

    @FXML
    public void operar() {
        
        double res = 0;
        double op1 = 0; 
        double op2 = 0;

        try {
            op1 = Double.parseDouble(operador1.getText());
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            op2 = Double.parseDouble(operador2.getText());
        } catch (Exception e) {
            e.printStackTrace();
        }

        Toggle selected = Operands.getSelectedToggle();
        if (selected == suma) {
            res = op1 + op2;
        } else if (selected == restar) {
            res = op1 - op2;
        } else if (selected == multiplicar) {
            res = op1 * op2;
        } else if (selected == dividir) {
            res = op1 / op2;
        }

        resultado.setText(String.valueOf(res));
        
    }

}
