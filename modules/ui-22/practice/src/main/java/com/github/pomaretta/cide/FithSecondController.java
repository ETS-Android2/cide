package com.github.pomaretta.cide;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FithSecondController {
 
    private FithController controller;

    @FXML
    private TextField nombre;
    @FXML
    private TextField apellidos;
    @FXML
    private TextField edad;
    @FXML
    private Button btnAgregar;

    public void setController(FithController controller) {
        this.controller = controller;
    }

    @FXML
    public void agregar() {

        Persona persona = new Persona();
        persona.setNombre(nombre.getText());
        persona.setApellido(apellidos.getText());
        persona.setEdad(Integer.parseInt(edad.getText()));

        this.controller.agregarPersona(persona);

        // Get stage
        Stage stage = (Stage) btnAgregar.getScene().getWindow();
        stage.close();
    }

}
