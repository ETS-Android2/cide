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
    final private String username = "USERNAME";
    final private String password = "PASSWORD";
    final private String url = "jdbc:mysql://" + host + ":" + port + "/" + database;

    private Connection connection;

    private Database() throws SQLException{
        connection = DriverManager.getConnection(this.url,this.username,this.password);
    }

    public static Database init() throws SQLException {
        return new Database();
    }

    public static ResultSet newQuery(Database database, String statement) throws SQLException {
        Statement stmt = database.connection.createStatement();
        return stmt.executeQuery(statement);
    }

    public static boolean newUpdate(Database database, String statement) throws SQLException {
        Statement stmt = database.connection.createStatement();
        return stmt.execute(statement);
    }

}
