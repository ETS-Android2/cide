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
import Objects.User;
import Services.Database;
import Services.ErrorWindow;
import Services.RoomManager;
import Util.ChatForm;
import Util.Application;
import Util.FormManager;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author Carlos Pomares
 */

public class RoomSelect extends ChatForm {

    private JTextField roomIdField;
    private JList roomsList;
    private DefaultListModel roomsListModel;
    private ScheduledExecutorService executorService;
    private JScrollPane scrollPane;

    private HashMap<Integer,String> rooms;

    public RoomSelect() {
        rooms = new HashMap<>();
    }

    @Override
    public void run() {
        
        getRoot().setBounds(100, 100, 536, 173);
        getRoot().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getRoot().getContentPane().setLayout(null);

        roomsListModel = new DefaultListModel();
        roomsList = new JList();
        roomsList.setModel(roomsListModel);
        roomsList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(!e.getValueIsAdjusting()){
                    listSelection();
                }
            }
        });

        scrollPane = new JScrollPane();
        scrollPane.setViewportView(roomsList);

        roomsList.setBounds(10, 42, 211, 81);

        update();

        JLabel lblNewLabel = new JLabel("Room ID:");
        lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        lblNewLabel.setBounds(231, 14, 82, 22);
        getRoot().getContentPane().add(lblNewLabel);

        roomIdField = new JTextField();
        roomIdField.setBounds(323, 12, 187, 24);
        getRoot().getContentPane().add(roomIdField);
        roomIdField.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("Public Rooms");
        lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 18));
        lblNewLabel_1.setBounds(10, 14, 121, 22);
        getRoot().getContentPane().add(lblNewLabel_1);

        JButton connectButton = new JButton("Connect");
        connectButton.setFont(new Font("Arial", Font.BOLD, 15));
        connectButton.setBounds(257, 73, 105, 31);
        getRoot().getContentPane().add(connectButton);

        connectButton.addActionListener(e -> connect());

        JButton createRoomButton = new JButton("Create");
        createRoomButton.setFont(new Font("Arial", Font.PLAIN, 12));
        createRoomButton.setBounds(372, 79, 124, 23);
        getRoot().getContentPane().add(createRoomButton);

        createRoomButton.addActionListener(e -> create());

        scrollPane.setBounds(10, 42, 211, 81);
        getRoot().getContentPane().add(scrollPane);

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                update();
            }
        };

        executorService = Executors.newScheduledThreadPool(1);
        executorService.scheduleAtFixedRate(runnable,0,1, TimeUnit.MINUTES);

        show();

    }

    private void update(){
        this.rooms.clear();
        String SQL = "SELECT id,title FROM room WHERE visibility = 1";
        Connection connection;
        ResultSet resultSet;
        try {
            connection = Database.start(Application.getDatabaseManager());
            resultSet = Database.newQuery(connection,SQL);
            while(resultSet.next()){
                int id = Integer.parseInt(resultSet.getString("id"));
                String title = resultSet.getString("title");
                this.rooms.put(id,title);
            }
            Database.close(connection);
        } catch (Exception e){
            ErrorWindow.run(e.getMessage());
        }
        updateList();
    }

    private void updateList(){
        roomsListModel.clear();
        for(Map.Entry entry : this.rooms.entrySet()){
            roomsListModel.addElement(String.format("%d - %s",entry.getKey(),entry.getValue()));
        }
    }

    private void listSelection(){
        int selection = roomsList.getSelectedIndex();
        if(selection >= 0){
            String[] selectedItem = roomsListModel.get(selection).toString().split("-");
            roomIdField.setText(selectedItem[0].trim());
        }
    }

    private void connect(){

        Connection connection;
        ResultSet result;

        // ROOM DATA
        int roomID;
        String roomDate;
        String roomTitle;
        String roomDescription;
        boolean roomVisibility;
        String roomPassword;

        // USER DATA
        int userID;
        String userName;

        try {

            connection = Database.start(Application.getDatabaseManager());

            // GET ID FROM FIELD
            int id = Integer.parseInt(roomIdField.getText());

            // CREATE SQL STMT
            String SQL = String.format(
                    "SELECT "
                    + "A.id AS room_id "
                    + ",A.creation_date AS room_date "
                    + ",A.title AS room_title "
                    + ",A.description AS room_description "
                    + ",A.visibility AS room_visibility "
                    + ",A.password AS room_password "
                    + ",B.id AS user_id "
                    + ",B.username AS user_name "
                    + "FROM room A "
                    + "INNER JOIN "
                    + "user B "
                    + "ON A.user = B.id "
                    + "WHERE A.id = %d"
                    ,id
            );

            // OBTAIN ROOM DATA
            result = Database.newQuery(connection,SQL);

            // FIRST RESULT
            result.next();

            // ROOM AND USER
            roomID = Integer.parseInt(result.getString("room_id"));
            roomDate = result.getString("room_date");
            roomTitle = result.getString("room_title");
            roomDescription = result.getString("room_description");
            roomVisibility = result.getString("room_visibility").equals("1");
            roomPassword = result.getString("room_password");

            userID = Integer.parseInt(result.getString("user_id"));
            userName = result.getString("user_name");

            // CHANGE ROOM IN ROOM MANAGER
            User user = User.retrieve(userID,userName);
            Room room = Room.retrieve(roomID,user,roomTitle,roomDescription,roomVisibility);
            RoomManager.changeRoom(Application.getRoomManager(),room);

            // CHANGE FORM
            FormManager.changeForm(Application.getFormManager(), Application.roomInterface);

        } catch (Exception e){
            ErrorWindow.run(e.getMessage());
        }

        getRoot().getContentPane().removeAll();

    }

    private void create(){
        FormManager.changeForm(Application.getFormManager(), Application.roomCreator);
    }

}
