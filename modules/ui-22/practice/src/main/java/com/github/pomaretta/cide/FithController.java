package com.github.pomaretta.cide;

import java.util.logging.Logger;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class FithController implements Initializable {

    private Logger LOGGER = Logger.getLogger(FithController.class.getName());

    @FXML
    private Button btnAgregar;

    @FXML
    private TableView<Persona> tabla;

    public void initialize(java.net.URL location, java.util.ResourceBundle resources) {

        tabla.getItems().clear();
        tabla.getColumns().clear();

        TableColumn<Persona, String> column = new TableColumn<Persona, String>("Nombre");
        column.setCellValueFactory(cell -> new ReadOnlyStringWrapper(cell.getValue().getNombre()));

        TableColumn<Persona, String> column2 = new TableColumn<Persona, String>("Apellidos");
        column2.setCellValueFactory(cell -> new ReadOnlyStringWrapper(cell.getValue().getApellido()));

        TableColumn<Persona, String> column3 = new TableColumn<Persona, String>("Edad");
        column3.setCellValueFactory(cell -> new ReadOnlyStringWrapper(String.valueOf(cell.getValue().getEdad())));

        tabla.getColumns().add(column);
        tabla.getColumns().add(column2);
        tabla.getColumns().add(column3);

    };

    public void agregarPersona(Persona persona) {
        tabla.getItems().add(persona);
    };

    @FXML
    public void agregar() {

        Stage s = new Stage();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("exercice05-02.fxml"));
            // FithSecondController c = loader.getController();
            Parent r = loader.load();
            FithSecondController c = loader.getController();            
            c.setController(this);
            s.setScene(new Scene(
                r
            ));
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        s.show();

        // Persona persona = new Persona();
        // persona.setNombre(nombre.getText());
        // persona.setApellido(apellidos.getText());
        // persona.setEdad(Integer.parseInt(edad.getText()));

        // if (editando) {
        //     LOGGER.info("Editando persona: " + persona);
        //     tabla.getItems().set(editandoIndex, persona);
        //     btnAgregar.setText("Agregar");
        //     editando = false;
        // } else {
        //     LOGGER.info("Agregando persona: " + persona);
        //     tabla.getItems().add(
        //             persona);
        // }

    }

}
