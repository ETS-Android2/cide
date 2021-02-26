package Screen;

/*

    Project     Programming21
    Package     Screen    
    
    Version     1.0      
    Author      Carlos Pomares
    Date        2021-02-25

    DESCRIPTION
    
*/

import Objects.User;
import Services.Database;
import Services.ErrorWindow;
import Util.*;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;

/**
 * @author Carlos Pomares
 */

public class SignUpForm extends ChatForm {

    private JTextField usernameField;
    private JPasswordField passwordField;

    @Override
    public void run() {
        
        getRoot().setBounds(100, 100, 301, 244);
        getRoot().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getRoot().getContentPane().setLayout(null);
        getRoot().setTitle("Sign Up - Chatting");

        JLabel lblNewLabel = new JLabel("Username:");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblNewLabel.setBounds(10, 11, 84, 22);
        getRoot().getContentPane().add(lblNewLabel);

        usernameField = new JTextField();
        usernameField.setFont(new Font("Arial", Font.PLAIN, 12));
        usernameField.setBounds(8, 40, 267, 20);
        getRoot().getContentPane().add(usernameField);
        usernameField.setColumns(10);

        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblPassword.setBounds(10, 71, 80, 22);
        getRoot().getContentPane().add(lblPassword);

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Arial", Font.PLAIN, 12));
        passwordField.setBounds(10, 104, 265, 20);
        getRoot().getContentPane().add(passwordField);

        JButton signUpButton = new JButton("Sign up");
        signUpButton.setFont(new Font("Arial", Font.PLAIN, 14));
        signUpButton.setBounds(98, 152, 81, 25);
        getRoot().getContentPane().add(signUpButton);

        signUpButton.addActionListener(e -> {
            signUp();
        });

        show();

    }

    private void signUp(){

        boolean requirements = true;
        // CHECK REQUERIMENTS

        if(usernameField.getText().length() > 50){
            ErrorWindow.run("Max length of username!");
            requirements = false;
        }

        if(passwordField.getPassword().length == 0){
            ErrorWindow.run("Password not valid!");
            requirements = false;
        }

        if(requirements){
            try {
                User user = User.generate(usernameField.getText(),MD5.getMD5(Stringify.charsToString(passwordField.getPassword())));
                String stmt = String.format(
                        "INSERT INTO user(username,password) VALUES ('%s','%s')"
                        ,user.getUsername(),user.getPassword()
                );

                // CONNECTION
                Connection connection = Database.start(Application.getDatabaseManager());
                Database.newUpdate(connection,stmt);
                Database.close(connection);
                FormManager.changeForm(Application.getFormManager(), Application.loginForm);
            } catch (Exception e){
                ErrorWindow.run(e.getMessage());
            }
        }

    }

}
