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

/**
 * @author Carlos Pomares
 */

public class User {

    private int id;
    private String username;
    private String password;

    private User(int id,String username) {
        this.id = id;
        this.username = username;
    }

    private User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    private User(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public static User generate(String username, String password){
        return new User(username,password);
    }

    public static User retrieve(int id, String username, String password){
        return new User(id,username,password);
    }

    public static User retrieve(int id, String username){
        return new User(id,username);
    }

    public static int lastMessage(User user)
        throws SQLException {
        String SQL = String.format(
                "SELECT B.id AS message_id "
                + "FROM user A "
                + "INNER JOIN "
                + "message B "
                + "ON A.id = B.user "
                + "WHERE A.id = %d "
                + "ORDER BY B.creation_date DESC "
                + "LIMIT 1"
        ,user.getId());
        Connection connection = Database.start(FormApp.getDatabaseManager());
        ResultSet rs = Database.newQuery(connection,SQL);
        rs.next();
        int result = Integer.parseInt(rs.getString("message_id"));
        Database.close(connection);
        return result;
    }

}
