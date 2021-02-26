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
import Services.RoomManager;
import Util.*;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Arrays;

/**
 * @author Carlos Pomares
 */

public class LoginForm extends ChatForm {
    
    private JTextField usernameField;
    private JPasswordField passwordField;

    @Override
    public void run() {

        getRoot().setTitle("Login | Chatting");
        getRoot().setBounds(100, 100, 450, 176);
        getRoot().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getRoot().getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel("Username:");
        lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        lblNewLabel.setBounds(10, 34, 76, 19);
        getRoot().getContentPane().add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Password:");
        lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 16));
        lblNewLabel_1.setBounds(10, 88, 74, 19);
        getRoot().getContentPane().add(lblNewLabel_1);

        usernameField = new JTextField();
        usernameField.setFont(new Font("Arial", Font.PLAIN, 12));
        usernameField.setBounds(96, 32, 158, 21);
        getRoot().getContentPane().add(usernameField);
        usernameField.setColumns(10);

        JButton loginButton = new JButton("Login");
        loginButton.setFont(new Font("Arial", Font.BOLD, 16));
        loginButton.setBounds(313, 30, 77, 27);
        getRoot().getContentPane().add(loginButton);

        JButton signUpButton = new JButton("Sign Up");
        signUpButton.setFont(new Font("Arial", Font.PLAIN, 14));
        signUpButton.setBounds(313, 86, 81, 25);
        getRoot().getContentPane().add(signUpButton);

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Arial", Font.PLAIN, 12));
        passwordField.setBounds(96, 88, 158, 21);
        getRoot().getContentPane().add(passwordField);

        loginButton.addActionListener(e -> {
            login();
        });

        signUpButton.addActionListener(e -> {
            signUp();
        });

        show();

    }

    private boolean login(){

        boolean success = false;
        String SQL = "SELECT * FROM user";

        Connection con = null;
        ResultSet rs = null;

        try {
            // INIT CONNECTION
            con = Database.start(FormApp.getDatabaseManager());
            // OBTAIN ALL USERS
            rs = Database.newQuery(con,SQL);
            // ITERATE
            while (rs.next()){

                int id = Integer.parseInt(rs.getString("id"));
                String username = rs.getString("username");
                String password = rs.getString("password");

                // CHECK USERNAMES AND PASSWORDS
                if(usernameField.getText().equals(username) && password.equals(MD5.getMD5(Stringify.charsToString(passwordField.getPassword())))){
                    RoomManager.setUser(FormApp.getRoomManager(), User.retrieve(id,username,password));
                    success = true;
                }
            }
            Database.close(con);
        } catch (Exception e){
            ErrorWindow.run(e.getMessage());
        }

        if(success){
            FormManager.changeForm(FormApp.getFormManager(),FormApp.roomSelect);
            return success;
        }

        ErrorWindow.run("Incorrect user or password.");
        return success;
    }

    private void signUp(){
        FormManager.changeForm(FormApp.getFormManager(),FormApp.signupForm);
    }

}
