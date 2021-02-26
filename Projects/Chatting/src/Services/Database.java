package Services;

/*

    Project     Programming21
    Package     Services    
    
    Version     1.0      
    Author      Carlos Pomares
    Date        2021-02-25

    DESCRIPTION
    
*/

import javax.xml.crypto.Data;
import java.sql.*;

/**
 * @author Carlos Pomares
 */


public class Database {

    final private String host = "localhost";
    final private String port = "3306";
    final private String database = "chatting";
    final private String username = "javaTest";
    final private String password = "dream19";
    final private String url = "jdbc:mysql://" + host + ":" + port + "/" + database;

    private Connection connection;

    private Database() {}

    public static Database init()
            throws SQLException {
        return new Database();
    }

    private String getUsername() {
        return username;
    }
    private String getPassword() {
        return password;
    }
    private String getUrl() {
        return url;
    }

    public static ResultSet newQuery(Connection connection, String query) throws SQLException {
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        return rs;
    }
    public static boolean newUpdate(Connection connection, String update) throws SQLException {
        Statement stmt = connection.createStatement();
        return stmt.execute(update);
    }

    public static Connection start(Database database)
            throws SQLException {
        return DriverManager.getConnection(database.getUrl(), database.getUsername(), database.getPassword());
    }

    public static void close(Connection connection)
            throws SQLException {
        assert connection != null;
        connection.close();
    }

}
