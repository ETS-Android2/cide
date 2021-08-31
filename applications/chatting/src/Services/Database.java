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

    final private String host = "HOSTNAME";
    final private String port = "PORT";
    final private String database = "DATABASE";
    final private String username = "USER";
    final private String password = "PASSWORD";
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
