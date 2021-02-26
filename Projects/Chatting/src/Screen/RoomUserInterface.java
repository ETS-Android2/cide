package Screen;

/*

    Project     Programming21
    Package     Screen    
    
    Version     1.0      
    Author      Carlos Pomares
    Date        2021-02-25

    DESCRIPTION
    
*/

import Objects.Message;
import Objects.Room;
import Services.ErrorWindow;
import Services.RoomManager;
import Util.ChatForm;
import Util.Application;
import Util.FormManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author Carlos Pomares
 */

public class RoomUserInterface extends ChatForm {

    private ArrayList<Message> messages;

    private JTextArea messageArea;
    private JList chatList;
    private DefaultListModel listModel;
    ScheduledExecutorService executorService;

    public RoomUserInterface(){
        messages = new ArrayList<>();
    }

    @Override
    public void run() {
        
        getRoot().setResizable(false);
        getRoot().setBounds(100, 100, 450, 300);
        getRoot().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getRoot().getContentPane().setLayout(null);

        listModel = new DefaultListModel();

        chatList = new JList();
        chatList.setValueIsAdjusting(true);
        chatList.setModel(listModel);
        chatList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        chatList.setBounds(10, 11, 177, 217);

        JLabel lblNewLabel = new JLabel("Title:");
        lblNewLabel.setFont(new Font("Arial", Font.BOLD, 16));
        lblNewLabel.setBounds(208, 12, 38, 19);
        getRoot().getContentPane().add(lblNewLabel);

        JLabel roomTitle = new JLabel("[RoomTitle]");
        roomTitle.setBounds(208, 35, 150, 14);
        roomTitle.setFont(new Font("Arial", Font.BOLD, 14));
        getRoot().getContentPane().add(roomTitle);

        JPopupMenu popupMenu = new JPopupMenu();
        popupMenu.setLocation(new Point(50, 50));
        popupMenu.setLabel("Hello World!");
        popupMenu.setPopupSize(new Dimension(200, 100));
        popupMenu.setBounds(50, 0, 100, 28);
        getRoot().getContentPane().add(popupMenu);

        JTextArea roomDescription = new JTextArea();
        roomDescription.setEditable(false);
        roomDescription.setLineWrap(true);
        popupMenu.add(roomDescription);

        messageArea = new JTextArea();
        messageArea.setBounds(208, 188, 144, 40);
        getRoot().getContentPane().add(messageArea);

        JButton sendButton = new JButton("Send");
        sendButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        sendButton.setFont(new Font("Arial", Font.PLAIN, 12));
        sendButton.setBounds(362, 189, 62, 39);
        getRoot().getContentPane().add(sendButton);

        JButton disconnectButton = new JButton("Disconnect");
        disconnectButton.setBounds(320, 12, 120, 23);
        getRoot().getContentPane().add(disconnectButton);


        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 11, 190, 217);
        getRoot().getContentPane().add(scrollPane);

        scrollPane.setViewportView(chatList);

        JMenuBar menuBar = new JMenuBar();
        getRoot().setJMenuBar(menuBar);

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

        // SET ROOM PROPERTIES

        roomTitle.setText(Application.getRoomManager().getConnected().getTitle());
        roomDescription.setText(Application.getRoomManager().getConnected().getDescription());

        sendButton.addActionListener(e -> sendMessage());
        disconnectButton.addActionListener(e -> disconnect());

        update();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                update();
            }
        };

        executorService = Executors.newScheduledThreadPool(1);
        executorService.scheduleAtFixedRate(runnable,0,5, TimeUnit.SECONDS);

        show();
        
    }

    private void update(){
        // OBTAIN ALL MESSAGES
        try {
            if(RoomManager.checkMessages(Application.getRoomManager())){
                RoomManager.updateMessages(Application.getRoomManager());
                this.messages.clear();
                this.messages = Application.getRoomManager().getMessages();
            }
        } catch (SQLException ex){
            ErrorWindow.run(ex.getMessage());
        }
        updateList();
    }

    private void updateList(){
        listModel.clear();
        for(Message msg : this.messages){

            String[] messageDate = msg.getDate().split(" ")[1].split(":");
            String shortDate = String.format("%s:%s",messageDate[0],messageDate[1]);

            // USER - Message content
            String message = String.format(
                    "%s:%s -> %s"
                    ,msg.getUser().getUsername()
                    ,shortDate
                    ,msg.getContent()
            );

            listModel.addElement(message);

        }
    }

    private void sendMessage(){
        try {
            boolean isValid = messageArea.getText().toCharArray().length > 0;
            if(isValid){
                Room.sendMessage(Application.getRoomManager().getUser()
                        ,Message.generate(Application.getRoomManager().getUser(),messageArea.getText())
                        , Application.getRoomManager().getConnected());
            } else {
                ErrorWindow.run("Message not valid!");
            }
        } catch (SQLException ex){
            ErrorWindow.run(ex.getMessage());
        }
        update();
    }

    private void disconnect(){
        executorService.shutdown();
        FormManager.changeForm(Application.getFormManager(), Application.roomSelect);
    }

}
