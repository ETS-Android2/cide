package com.github.pomaretta.cide;

import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class FourthController {

    private Logger LOGGER = Logger.getLogger(FourthController.class.getName());

    @FXML
    private TextField nombre;
    @FXML
    private TextField apellidos;
    @FXML
    private TextField edad;
    @FXML
    private Button btnAgregar;

    @FXML
    private TableView<Persona> tabla;

    private boolean creado = false;
    private boolean editando = false;
    private int editandoIndex = -1;

    @FXML
    public void agregar() {

        if (!creado) {
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

        }

        creado = true;

        Persona persona = new Persona();
        persona.setNombre(nombre.getText());
        persona.setApellido(apellidos.getText());
        persona.setEdad(Integer.parseInt(edad.getText()));

        if (editando) {
            LOGGER.info("Editando persona: " + persona);
            tabla.getItems().set(editandoIndex, persona);
            btnAgregar.setText("Agregar");
            editando = false;
        } else {
            LOGGER.info("Agregando persona: " + persona);
            tabla.getItems().add(
                    persona);
        }

    }

    @FXML
    public void modificar() {

        Persona persona = tabla.getItems().get(tabla.getSelectionModel().getSelectedIndex());

        nombre.setText(persona.getNombre());
        apellidos.setText(persona.getApellido());
        edad.setText(String.valueOf(persona.getEdad()));

        editando = true;
        editandoIndex = tabla.getSelectionModel().getSelectedIndex();

        LOGGER.info("Modificando persona: " + persona + " en la posicion: " + editandoIndex);
        btnAgregar.setText("Modificar");

    }

    @FXML
    public void eliminar() {

        tabla.getItems().remove(tabla.getSelectionModel().getSelectedIndex());

    }

}
