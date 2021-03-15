package Application.Persistent;

/*

    Project     Programming21
    Package     Application.Entities    
    
    Version     1.0      
    Author      Carlos Pomares
    Date        2021-03-12

    DESCRIPTION
    
*/

import Application.Entities.Client;
import Application.Entities.Product;
import Application.Persistent.Connections;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Carlos Pomares
 */

public class DatabaseDriver {

    private Connection conn;

    public DatabaseDriver() throws SQLException {
        this.conn = Connections.createConnection();
    }

    /*
     *
     * CLIENTES
     *
     * */

    public int obtenerNuevoIDCliente() throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(Statements.GET_LAST_CLIENT_ID.getQuery());
        if(rs.next())
            return (1 + rs.getInt(1));
        return 0;
    }

    public List<Client> obtenerTodosLosClientes() throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(Statements.SELECT_ALL_CLIENTS.getQuery());
        ArrayList<Client> clients = new ArrayList<>();
        while(rs.next()){
            clients.add(new Client(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("name_2"),
                    rs.getString("lastname"),
                    rs.getString("lastname_2"),
                    rs.getString("street_address"),
                    rs.getString("mail_address"),
                    rs.getString("phone_number")
            ));
        }
        return clients;
    }

    public List<Client> buscarClientePorNombre(String nom) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(String.format(Statements.GET_CLIENT_BY_NAME.getQuery(),nom));
        ArrayList<Client> clients = new ArrayList<>();
        while(rs.next()){
            clients.add(new Client(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("name_2"),
                    rs.getString("lastname"),
                    rs.getString("lastname_2"),
                    rs.getString("street_address"),
                    rs.getString("mail_address"),
                    rs.getString("phone_number")
            ));
        }
        return clients;
    }

    public boolean agregarCliente(Client c) throws SQLException {
        Statement stmt = conn.createStatement();
        return stmt.execute(String.format(Statements.INSERT_NEW_CLIENT.getQuery(),
                c.getId(),
                c.getFirstName(),
                c.getSecondName(),
                c.getFirstLastname(),
                c.getSecondLastname(),
                c.getStreetAddress(),
                c.getMailAddress(),
                c.getPhoneNumber()
        ));
    }

    /*
     *
     * PRODUCTOS
     *
     * */

    // TODO Buscar producto por título
    public List<Product> buscarProductoPorTitulo(String title) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(String.format(Statements.GET_PRODUCT_BY_TITLE.getQuery(),title));
        ArrayList<Product> products = new ArrayList<>();
        while(rs.next()){
            products.add(new Product(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getString("description"),
                    rs.getFloat("price")
            ));
        }
        return products;
    }

    public boolean agregarProducto(Product p) throws SQLException {
        Statement stmt = conn.createStatement();
        return stmt.execute(String.format(Statements.INSERT_NEW_PRODUCT.getQuery(),
                p.getTitle(),
                p.getDescription(),
                p.getPrice()
        ));
    }

    /*
     *
     * ENCARGOS
     *
     * */

    // TODO Buscar encargos por cliente

    // TODO Crear encargo

    // TODO Eliminar encargo

    public void close() throws SQLException {
        this.conn.close();
    }

}
