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

public class SignUpForm extends ChatForm {

    private JTextField usernameField;
    private JPasswordField passwordField;

    @Override
    public void run() {
        
        root().setBounds(100, 100, 301, 244);
        root().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        root().getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel("Username:");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblNewLabel.setBounds(10, 11, 84, 22);
        root().getContentPane().add(lblNewLabel);

        usernameField = new JTextField();
        usernameField.setFont(new Font("Arial", Font.PLAIN, 12));
        usernameField.setBounds(8, 40, 267, 20);
        root().getContentPane().add(usernameField);
        usernameField.setColumns(10);

        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblPassword.setBounds(10, 71, 80, 22);
        root().getContentPane().add(lblPassword);

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Arial", Font.PLAIN, 12));
        passwordField.setBounds(10, 104, 265, 20);
        root().getContentPane().add(passwordField);

        JButton signUpButton = new JButton("Sign up");
        signUpButton.setFont(new Font("Arial", Font.PLAIN, 14));
        signUpButton.setBounds(98, 152, 81, 25);
        root().getContentPane().add(signUpButton);

        show();

    }
}
