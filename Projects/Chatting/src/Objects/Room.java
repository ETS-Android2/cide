package Objects;

/*

    Project     Programming21
    Package     Objects    
    
    Version     1.0      
    Author      Carlos Pomares
    Date        2021-02-25

    DESCRIPTION
    
*/

import Services.Database;
import Util.FormApp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/**
 * @author Carlos Pomares
 */

public class Room {

    private int id;
    final private User user;
    final private String title;
    final private String description;
    final private boolean visibility;

    private HashMap<User,Message> messages;

    private Room(User user, String title, String description, boolean visibility) {
        this.user = user;
        this.title = title;
        this.description = description;
        this.visibility = visibility;
    }

    private Room(int id, User user, String title, String description, boolean visibility) {
        this.id = id;
        this.user = user;
        this.title = title;
        this.description = description;
        this.visibility = visibility;
        messages = new HashMap<>();
    }

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public boolean getVisibility() {
        return visibility;
    }

    // TODO
    public String toSQL(){
        return String.format("INSERT INTO room(user,title,description,visibility) VALUES (%d,'%s','%s',%b)",getUser().getId(),getTitle(),getDescription(),getVisibility());
    }

    // TODO
    public String retrieveSQL(){
        return "";
    }

    public static boolean create(User user, String title, String description, boolean visibility) throws SQLException {
        return Database.newUpdate(FormApp.getDatabaseManager(),new Room(user,title,description,visibility).toSQL());
    }

    public static boolean sendMessage(Message message, Room room){
        return true;
    }

    public static HashMap<User,Message> getMessages(Room room){
        return new HashMap<>();
    }

}
