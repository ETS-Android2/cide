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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Carlos Pomares
 */

public class RoomUserInterface extends ChatForm {

    

    @Override
    public void run() {
        
        root().setResizable(false);
        root().setBounds(100, 100, 450, 300);
        root().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        root().getContentPane().setLayout(null);

        JList chatList = new JList();
        chatList.setValueIsAdjusting(true);
        chatList.setModel(new AbstractListModel() {
            String[] values = new String[] {};
            public int getSize() {
                return values.length;
            }
            public Object getElementAt(int index) {
                return values[index];
            }
        });
        chatList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        chatList.setBounds(10, 11, 177, 217);

        JLabel lblNewLabel = new JLabel("Title:");
        lblNewLabel.setFont(new Font("Arial", Font.BOLD, 16));
        lblNewLabel.setBounds(208, 12, 38, 19);
        root().getContentPane().add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("[RoomTitle]");
        lblNewLabel_1.setBounds(256, 16, 73, 14);
        root().getContentPane().add(lblNewLabel_1);

        JPopupMenu popupMenu = new JPopupMenu();
        popupMenu.setLocation(new Point(50, 50));
        popupMenu.setLabel("Hello World!");
        popupMenu.setPopupSize(new Dimension(200, 100));
        popupMenu.setBounds(50, 0, 100, 28);
        root().getContentPane().add(popupMenu);

        JTextArea roomDescription = new JTextArea();
        roomDescription.setEditable(false);
        popupMenu.add(roomDescription);

        JTextArea messageArea = new JTextArea();
        messageArea.setBounds(208, 188, 144, 40);
        root().getContentPane().add(messageArea);

        JButton sendButton = new JButton("Send");
        sendButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        sendButton.setFont(new Font("Arial", Font.PLAIN, 12));
        sendButton.setBounds(362, 189, 62, 39);
        root().getContentPane().add(sendButton);

        JButton disconnectButton = new JButton("Disconnect");
        disconnectButton.setBounds(339, 12, 85, 23);
        root().getContentPane().add(disconnectButton);


        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 11, 190, 217);
        root().getContentPane().add(scrollPane);

        scrollPane.setViewportView(chatList);

        JMenuBar menuBar = new JMenuBar();
        root().setJMenuBar(menuBar);

        JMenu mnNewMenu = new JMenu("Info");
        menuBar.add(mnNewMenu);

        JMenuItem menuItemDescription = new JMenuItem("Description");
        menuItemDescription.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(popupMenu.isVisible()) {
                    popupMenu.setVisible(false);
                } else {
                    popupMenu.setVisible(true);
                }
            }
        });
        mnNewMenu.add(menuItemDescription);

        show();
        
    }

}
