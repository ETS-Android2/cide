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
import Application.Entities.Order;
import Application.Entities.Product;
import Application.Persistent.Connections;

import javax.xml.transform.Result;
import java.sql.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Carlos Pomares
 */

public class DatabaseDriver {

    private Connection conn;

    public void stablishConnection() throws SQLException {
        this.conn = Connections.createConnection();
    }

    public void configureNewConnection(String url, String username, String password) throws SQLException {
        this.conn = Connections.createConnection(url,username,password);
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

    public Client obtenerClientePorId(int id) throws Exception {

        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(String.format(Statements.GET_CLIENT_BY_ID.getQuery()
                ,id)
        );

        if(rs.next()){
            return new Client(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("name_2"),
                    rs.getString("lastname"),
                    rs.getString("lastname_2"),
                    rs.getString("street_address"),
                    rs.getString("mail_address"),
                    rs.getString("phone_number")
            );
        } else {
            throw new Exception("CLIENT NULL");
        }
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

    public List<Product> obtenerTodosLosProductos() throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(Statements.SELECT_ALL_PRODUCTS.getQuery());
        ArrayList<Product> products = new ArrayList<>();
        while(rs.next()){
            products.add(new Product(
                    rs.getInt("id")
                    ,rs.getString("title")
                    ,rs.getString("description")
                    ,rs.getFloat("price")
                    ,rs.getInt("stock")
            ));
        }
        return products;
    }

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
                    rs.getFloat("price"),
                    rs.getInt("stock")
            ));
        }
        return products;
    }

    public Product obtenerProductoPorId(int id) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(String.format(Statements.GET_PRODUCT_BY_ID.getQuery()
                ,id)
        );
        if(rs.next()){
            return new Product(
                    rs.getInt("id")
                    ,rs.getString("title")
                    ,rs.getString("description")
                    ,rs.getFloat("price")
                    ,rs.getInt("stock")
            );
        }
        return null;
    }

    public boolean agregarProducto(Product p) throws SQLException {
        Statement stmt = conn.createStatement();
        NumberFormat nf = new DecimalFormat("#.##");
        return stmt.execute(String.format(Statements.INSERT_NEW_PRODUCT.getQuery(),
                p.getTitle()
                ,p.getDescription()
                ,nf.format(p.getPrice()).replace(",",".")
                ,p.getStock()
        ));
    }

    public boolean updateProduct(Product p,int update) throws SQLException {
        Statement stmt = conn.createStatement();
        return stmt.execute(String.format(
                Statements.UPDATE_PRODUCT_DECREMENT_STOCK_BY_NUMBER.getQuery()
                ,update
                ,p.getId()
        ));
    }

    /*
     *
     * ENCARGOS
     *
     * */

    // TODO Buscar encargos por cliente

    // TODO Crear encargo
    public boolean agregarEncargo(Client c) throws SQLException {
        Statement stmt = conn.createStatement();
        return stmt.execute(String.format(Statements.INSERT_NEW_ORDER.getQuery(),c.getId()));
    }

    public boolean agregarProductoAEncargo(Integer e,Product p, Integer i) throws SQLException {
        Statement stmt = conn.createStatement();
        return stmt.execute(String.format(
                Statements.INSERT_NEW_ORDER_PRODUCT.getQuery()
                ,e
                ,p.getId()
                ,i
        ));
    }

    public void agregarProductosAEncargo(int encargo, HashMap<Product,Integer> productos) throws Exception {
        for(Map.Entry<Product,Integer> p : productos.entrySet()){
            agregarProductoAEncargo(encargo,p.getKey(),p.getValue());
        }
    }

    // TODO Obtener el último encargo de un cliente
    public int obtenerIdUltimoEncargoDeUnCliente(Client c) throws Exception {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(String.format(Statements.GET_LAST_CLIENT_ORDER.getQuery()
                ,c.getId())
        );
        if(rs.next()){
            return rs.getInt("id");
        } else {
            throw new Exception("ID INCORRECTO");
        }
    }

    // TODO Obtener encargos de un cliente
    public ArrayList<Order> obtenerEncargosDeUnCliente(Client c) throws Exception {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(String.format(
                Statements.GET_ALL_CLIENT_ORDERS.getQuery()
                ,c.getId()
        ));

        ArrayList<Order> orders = new ArrayList<>();

        while(rs.next()){
            orders.add(obtenerEncargo(rs.getInt("id")));
        }

        return orders;
    }

    // TODO Obtener encargo completo
    public Order obtenerEncargo(int id) throws Exception {

        Statement stmt = conn.createStatement();

        ResultSet rs = stmt.executeQuery(String.format(
                Statements.GET_ORDER_BY_ID.getQuery(),
                id
        ));

        if(rs.next()){
            return new Order(
                    rs.getInt("id")
                    ,obtenerClientePorId(rs.getInt("client"))
                    ,obtenerProductosDeEncargo(rs.getInt("id"))
                    , rs.getTimestamp("date")
            );
        } else {
            throw new Exception("ID INCORRECTO");
        }
    }

    // TODO Obtener productos de encargo
    public HashMap<Product,Integer> obtenerProductosDeEncargo(int encargo) throws Exception {

        HashMap<Product,Integer> products = new HashMap<>();

        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(String.format(Statements.GET_ALL_PRODUCT_ID_AND_QUANTITY_IN_ORDER.getQuery()
                ,encargo)
        );

        while(rs.next()){
            products.put(
                    obtenerProductoPorId(rs.getInt("product"))
                    ,rs.getInt("quantity")
            );
        }

        if(products.size() == 0){
            throw new Exception("EMPTY PRODUCTS");
        }

        return products;
    }

    // TODO Eliminar encargo
    public boolean eliminarEncargo(int id) throws SQLException {
        Statement stmt = conn.createStatement();
        return stmt.execute(String.format(
                Statements.DELETE_ORDER_BY_ID.getQuery()
                ,id
        ));
    }

    public void close() throws SQLException {
        this.conn.close();
    }

}
