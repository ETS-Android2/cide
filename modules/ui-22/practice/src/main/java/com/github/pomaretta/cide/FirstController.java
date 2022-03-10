package com.github.pomaretta.cide;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class FirstController {
    
    @FXML
    private TextField operador1;
    @FXML
    private TextField operador2;
    @FXML
    private TextField resultado;

    @FXML
    public void sumNumbers() {
        
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

        resultado.setText(String.valueOf(op1 + op2));

    }

}
