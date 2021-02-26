package Screen;

/*

    Project     Programming21
    Package     Screen    
    
    Version     1.0      
    Author      Carlos Pomares
    Date        2021-02-25

    DESCRIPTION
    
*/

import Objects.Room;
import Services.ErrorWindow;
import Util.ChatForm;
import Util.Application;
import Util.FormManager;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

/**
 * @author Carlos Pomares
 */

public class RoomCreator extends ChatForm {

    private JTextField titleField;
    private JTextArea description;
    private final ButtonGroup visibilityGroup = new ButtonGroup();
    private JRadioButton publicRadioButton, privateRadioButton;
    private JTextField passwordField;

    @Override
    public void run() {

        getRoot().setBounds(100, 100, 330, 419);
        getRoot().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getRoot().getContentPane().setLayout(null);
        getRoot().setTitle("Room Creator - Chatting");

        JLabel lblNewLabel = new JLabel("Title:");
        lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        lblNewLabel.setBounds(10, 11, 40, 22);
        getRoot().getContentPane().add(lblNewLabel);

        JLabel lblDescription = new JLabel("Description:");
        lblDescription.setFont(new Font("Arial", Font.PLAIN, 18));
        lblDescription.setBounds(10, 81, 95, 22);
        getRoot().getContentPane().add(lblDescription);

        JLabel lblVisibility = new JLabel("Visibility:");
        lblVisibility.setFont(new Font("Arial", Font.PLAIN, 18));
        lblVisibility.setBounds(10, 183, 69, 22);
        getRoot().getContentPane().add(lblVisibility);

        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setFont(new Font("Arial", Font.PLAIN, 18));
        lblPassword.setBounds(10, 242, 84, 22);
        getRoot().getContentPane().add(lblPassword);

        titleField = new JTextField();
        titleField.setFont(new Font("Arial", Font.PLAIN, 12));
        titleField.setBounds(10, 41, 280, 29);
        getRoot().getContentPane().add(titleField);
        titleField.setColumns(10);

        description = new JTextArea();
        description.setLineWrap(true);
        description.setBounds(10, 114, 280, 58);
        getRoot().getContentPane().add(description);

        publicRadioButton = new JRadioButton("Public");
        visibilityGroup.add(publicRadioButton);
        publicRadioButton.setBounds(10, 212, 100, 23);
        getRoot().getContentPane().add(publicRadioButton);
        publicRadioButton.setSelected(true);

        privateRadioButton = new JRadioButton("Private");
        visibilityGroup.add(privateRadioButton);
        privateRadioButton.setBounds(130, 212, 100, 23);
        getRoot().getContentPane().add(privateRadioButton);

        passwordField = new JTextField();
        passwordField.setBounds(10, 275, 280, 29);
        getRoot().getContentPane().add(passwordField);
        passwordField.setColumns(10);

        // Future update
        passwordField.setEnabled(false);

        JButton createButton = new JButton("Create Room!");
        createButton.setFont(new Font("Arial", Font.PLAIN, 18));
        createButton.setBounds(10, 319, 280, 50);
        getRoot().getContentPane().add(createButton);

        createButton.addActionListener(e -> create());

        show();

    }

    private void create(){

        boolean isValid =
                titleField.getText().toCharArray().length > 0
                &&
                this.description.getText().toCharArray().length > 0;

        String title, description;
        boolean visibility;

        try {
            if(isValid){
                title = titleField.getText();
                description = this.description.getText();
                visibility = publicRadioButton.isSelected();
                Room.create(Application.getRoomManager().getUser(),title,description,visibility);
            } else {
                ErrorWindow.run("Requirements not satisfied!");
            }
        } catch (SQLException ex){
            ErrorWindow.run(ex.getMessage());
        } finally {
            FormManager.changeForm(Application.getFormManager(), Application.roomSelect);
        }
    }

}
