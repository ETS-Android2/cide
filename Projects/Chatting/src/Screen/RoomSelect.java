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

public class RoomSelect extends ChatForm {

    private JTextField roomIdField;

    @Override
    public void run() {
        
        root().setBounds(100, 100, 536, 173);
        root().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        root().getContentPane().setLayout(null);

        JList roomsList = new JList();
        roomsList.setModel(new AbstractListModel() {
            String[] values = new String[] {};
            public int getSize() {
                return values.length;
            }
            public Object getElementAt(int index) {
                return values[index];
            }
        });
        roomsList.setBounds(10, 42, 211, 81);

        JLabel lblNewLabel = new JLabel("Room ID:");
        lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        lblNewLabel.setBounds(231, 14, 82, 22);
        root().getContentPane().add(lblNewLabel);

        roomIdField = new JTextField();
        roomIdField.setBounds(323, 12, 187, 24);
        root().getContentPane().add(roomIdField);
        roomIdField.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("Public Rooms");
        lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 18));
        lblNewLabel_1.setBounds(10, 14, 121, 22);
        root().getContentPane().add(lblNewLabel_1);

        JButton connectButton = new JButton("Connect");
        connectButton.setFont(new Font("Arial", Font.BOLD, 15));
        connectButton.setBounds(257, 73, 105, 31);
        root().getContentPane().add(connectButton);

        JButton createRoomButton = new JButton("Create");
        createRoomButton.setFont(new Font("Arial", Font.PLAIN, 12));
        createRoomButton.setBounds(372, 79, 124, 23);
        root().getContentPane().add(createRoomButton);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 42, 211, 81);
        root().getContentPane().add(scrollPane);

        scrollPane.setViewportView(roomsList);

        show();

    }

}
