package com.github.pomaretta.cide;


import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ThirdController {

    @FXML
    private TextField nombre;
    @FXML
    private TextField apellidos;
    @FXML
    private TextField edad;

    @FXML
    private TableView<Persona> tabla;

    @FXML
    public void agregar() {

        tabla.getItems().clear();
        tabla.getColumns().clear();

        TableColumn<Persona,String> column = new TableColumn<Persona, String>("Nombre");
        column.setCellValueFactory(cell -> new ReadOnlyStringWrapper(cell.getValue().getNombre()));

        TableColumn<Persona,String> column2 = new TableColumn<Persona, String>("Apellidos");
        column2.setCellValueFactory(cell -> new ReadOnlyStringWrapper(cell.getValue().getApellido()));

        TableColumn<Persona,String> column3 = new TableColumn<Persona, String>("Edad");
        column3.setCellValueFactory(cell -> new ReadOnlyStringWrapper(String.valueOf(cell.getValue().getEdad())));

        tabla.getColumns().add(column);
        tabla.getColumns().add(column2);
        tabla.getColumns().add(column3);

        Persona persona = new Persona();
        persona.setNombre(nombre.getText());
        persona.setApellido(apellidos.getText());
        persona.setEdad(Integer.parseInt(edad.getText()));

        tabla.getItems().add(
            persona
        );
    }

}
