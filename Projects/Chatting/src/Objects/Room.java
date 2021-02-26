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
import Util.Form;
import Util.FormApp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

    private String toSQL(){
        return String.format("INSERT INTO room(user,title,description,visibility) VALUES (%d,'%s','%s',%b)",getUser().getId(),getTitle(),getDescription(),getVisibility());
    }

    private String retrieveSQL(){
        return String.format("SELECT A.room,A.creation_date,user_id,user_name,message_id,message_content "
                + "FROM message_transaction A "
                + "INNER JOIN "
                + "(SELECT A.id AS user_id,A.username AS user_name,B.id AS message_id,content AS message_content "
                + "FROM user A "
                + "INNER JOIN "
                + "message B "
                + "ON A.id = B.user "
                + ") B "
                + "ON A.message = B.message_id "
                + "WHERE A.room = %s",getId());
    }

    private String putSQL(User user)
        throws SQLException {
        return String.format("INSERT INTO message_transaction(room,message,user) VALUES (%d,%d,%d)",getId(),User.lastMessage(user),user.getId());
    }

    public static Room retrieve(int id, User user, String title, String description, boolean visibility){
        return new Room(id,user,title,description,visibility);
    }

    public static boolean create(User user, String title, String description, boolean visibility)
            throws SQLException {
        Connection connection = Database.start(FormApp.getDatabaseManager());
        boolean result = Database.newUpdate(connection,new Room(user,title,description,visibility).toSQL());
        Database.close(connection);
        return result;
    }

    public static boolean sendMessage(User user,Message message, Room room)
            throws SQLException {
        Connection connection = Database.start(FormApp.getDatabaseManager());
        // INSERT MESSAGE
        Database.newUpdate(connection,message.toSQL());
        // PULL MESSAGE
        Database.newUpdate(connection, room.putSQL(user));
        Database.close(connection);
        return true;
    }

    public static HashMap<User,Message> getMessages(Room room){
        return new HashMap<>();
    }

    public static int lastMessage(User user, Room room)
            throws SQLException {
        String SQL = String.format(
                "SELECT message_id "
                        + "FROM message_transaction A "
                        + "INNER JOIN "
                        + "( SELECT A.id AS user_id,A.username AS user_name,B.id AS message_id,content AS message_content "
                        + "FROM user A "
                        + "INNER JOIN "
                        + "message B "
                        + "ON A.id = B.user "
                        + "WHERE A.id = %d ) B "
                        + "ON A.message = B.message_id "
                        + "WHERE A.room = %d "
                        + "ORDER BY A.creation_date "
                        + "DESC LIMIT 1"
                ,user.getId(),room.getId());
        Connection connection = Database.start(FormApp.getDatabaseManager());
        ResultSet rs = Database.newQuery(connection,SQL);
        rs.next();
        int result = Integer.parseInt(rs.getString("message_id"));
        Database.close(connection);
        return result;
    }

}
