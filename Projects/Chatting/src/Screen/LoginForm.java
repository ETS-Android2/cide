package Screen;

/*

    Project     Programming21
    Package     Screen    
    
    Version     1.0      
    Author      Carlos Pomares
    Date        2021-02-25

    DESCRIPTION
    
*/

import Util.ChatForm;

import javax.swing.*;
import java.awt.*;

/**
 * @author Carlos Pomares
 */

public class LoginForm extends ChatForm {
    
    private JTextField usernameField;
    private JPasswordField passwordField;

    @Override
    public void run() {

        root().setBounds(100, 100, 450, 176);
        root().setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        root().getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel("Username:");
        lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        lblNewLabel.setBounds(10, 34, 76, 19);
        root().getContentPane().add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Password:");
        lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 16));
        lblNewLabel_1.setBounds(10, 88, 74, 19);
        root().getContentPane().add(lblNewLabel_1);

        usernameField = new JTextField();
        usernameField.setFont(new Font("Arial", Font.PLAIN, 12));
        usernameField.setBounds(96, 32, 158, 21);
        root().getContentPane().add(usernameField);
        usernameField.setColumns(10);

        JButton loginButton = new JButton("Login");
        loginButton.setFont(new Font("Arial", Font.BOLD, 16));
        loginButton.setBounds(313, 30, 77, 27);
        root().getContentPane().add(loginButton);

        JButton signUpButton = new JButton("Sign Up");
        signUpButton.setFont(new Font("Arial", Font.PLAIN, 14));
        signUpButton.setBounds(313, 86, 81, 25);
        root().getContentPane().add(signUpButton);

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Arial", Font.PLAIN, 12));
        passwordField.setBounds(96, 88, 158, 21);
        root().getContentPane().add(passwordField);

        show();

    }

}
