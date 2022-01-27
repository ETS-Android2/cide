package com.github.pomaretta.cide.ui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author Carlos Pomares <https://www.github.com/pomaretta>
 */
public class App extends Application {

    private TextField firstNameTextField;
    private TextField lastNameTextField;
    private TextField emailTextField;

    private Button submitButton;
    private Button clearButton;

    /**
     * El método que se ejecutará cuando se presione el botón de "Submit".
     * Obtendrá los campos del usuario siempre que estén todos rellenados, y los guardará
     * en un archivo CSV, con un nombre generado de forma única.
     */
    private void onSubmit() {

        String firstName = firstNameTextField.getText();
        String lastName = lastNameTextField.getText();
        String email = emailTextField.getText();

        // Check if the fields are empty
        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText("Please fill in all the fields");
            alert.showAndWait();
            return;
        }

        File csvFile = new File(
            String.format(
                "./user_%s.csv",
                System.currentTimeMillis()
            )
        );
        FileOutputStream fos = null;

        try {
            fos = new FileOutputStream(csvFile);
            fos.write(("firstname,lastname,email\n" + firstName + "," + lastName + "," + email).getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Show dialog box with the file name
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText("CSV file created");
        alert.setContentText("File: " + csvFile.getName());
        alert.showAndWait();

    }

    /**
     * El método que se ejecutará cuando se presione el botón de "Clear".
     * Limpiará los campos del formulario.
     */
    private void onClear() {
        firstNameTextField.clear();
        lastNameTextField.clear();
        emailTextField.clear();
    }

    @Override
    public void start(Stage stage) throws IOException {

        Text firstNameLabel = new Text("Firstname");
        Text lastNameLabel = new Text("Lastname");
        Text emailLabel = new Text("Email");

        this.firstNameTextField = new TextField();
        this.lastNameTextField = new TextField();
        this.emailTextField = new TextField();

        this.submitButton = new Button("Submit");
        this.submitButton.setOnAction(event -> onSubmit());
        
        this.clearButton = new Button("Clear");
        this.clearButton.setOnAction(event -> onClear());

        // Creamos el GridPane
        GridPane gridPane = new GridPane();

        // Configuramos el GridPane
        gridPane.setMinSize(400, 200);
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(5);
        gridPane.setHgap(5);
        gridPane.setAlignment(Pos.CENTER);

        // Firstname
        gridPane.add(firstNameLabel, 0, 0);
        gridPane.add(firstNameTextField, 1, 0);

        // Lastname
        gridPane.add(lastNameLabel, 0, 1);
        gridPane.add(lastNameTextField, 1, 1);

        // Email
        gridPane.add(emailLabel, 0, 2);
        gridPane.add(emailTextField, 1, 2);

        // Submit
        gridPane.add(submitButton, 0, 3);
        
        // Clear
        gridPane.add(clearButton, 1, 3);

        Scene scene = new Scene(gridPane);

        stage.setTitle("JavaFX - Carlos Pomares");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}