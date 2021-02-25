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

public class RoomCreator extends ChatForm {

    private JTextField titleField;
    private final ButtonGroup visibilityGroup = new ButtonGroup();
    private JTextField passwordField;

    @Override
    public void run() {

        root().setBounds(100, 100, 330, 419);
        root().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        root().getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel("Title:");
        lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        lblNewLabel.setBounds(10, 11, 40, 22);
        root().getContentPane().add(lblNewLabel);

        JLabel lblDescription = new JLabel("Description:");
        lblDescription.setFont(new Font("Arial", Font.PLAIN, 18));
        lblDescription.setBounds(10, 81, 95, 22);
        root().getContentPane().add(lblDescription);

        JLabel lblVisibility = new JLabel("Visibility:");
        lblVisibility.setFont(new Font("Arial", Font.PLAIN, 18));
        lblVisibility.setBounds(10, 183, 69, 22);
        root().getContentPane().add(lblVisibility);

        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setFont(new Font("Arial", Font.PLAIN, 18));
        lblPassword.setBounds(10, 242, 84, 22);
        root().getContentPane().add(lblPassword);

        titleField = new JTextField();
        titleField.setFont(new Font("Arial", Font.PLAIN, 12));
        titleField.setBounds(10, 41, 280, 29);
        root().getContentPane().add(titleField);
        titleField.setColumns(10);

        JTextArea textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setBounds(10, 114, 280, 58);
        root().getContentPane().add(textArea);

        JRadioButton publicRadioButton = new JRadioButton("Public");
        visibilityGroup.add(publicRadioButton);
        publicRadioButton.setBounds(10, 212, 53, 23);
        root().getContentPane().add(publicRadioButton);

        JRadioButton privateRadioButton = new JRadioButton("Private");
        visibilityGroup.add(privateRadioButton);
        privateRadioButton.setBounds(65, 212, 59, 23);
        root().getContentPane().add(privateRadioButton);

        passwordField = new JTextField();
        passwordField.setBounds(10, 275, 280, 29);
        root().getContentPane().add(passwordField);
        passwordField.setColumns(10);

        JButton btnNewButton = new JButton("Create Room!");
        btnNewButton.setFont(new Font("Arial", Font.PLAIN, 18));
        btnNewButton.setBounds(10, 319, 280, 50);
        root().getContentPane().add(btnNewButton);

        show();

    }

}
