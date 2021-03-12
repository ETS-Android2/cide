package Data;

/*

    Project     Programming21
    Package     Application.Entities    
    
    Version     1.0      
    Author      Carlos Pomares
    Date        2021-03-12

    DESCRIPTION
    
*/

import Application.Entities.Client;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Carlos Pomares
 */

public class BDManager {

    private Connection conn;

    /**
     *
     * Creates connection with default url and credentials given by the BDCredentials object.
     *
     * @return a SQL Connection.
     * @throws SQLException if the connection fails.
     */
    public Connection createConnection() throws SQLException {
        return conn = DriverManager.getConnection(BDCredentials.getUrl(),BDCredentials.USERNAME.getData(),BDCredentials.PASSWORD.getData());
    }

    /**
     *
     * Creates connection with url, username, password arguments and tries to establish connection.
     *
     * @param url the url of the jdbc pointing to the database.
     * @param username valid username for access to the database.
     * @param password valid password for the username.
     * @return a SQL connection.
     * @throws SQLException if the connections fails.
     */
    public Connection createConnection(String url, String username, String password) throws SQLException {
        return conn = DriverManager.getConnection(url,username,password);
    }

    /**
     *
     * Close the connection with the database.
     *
     * @throws SQLException if the actions fails.
     */
    public void close() throws SQLException {
        this.conn.close();
    }

}
