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

    /**
     * La conexión con la base de datos.
     */
    private Connection conn;

    /**
     *
     * Establece una conexión directa al sistema de base de datos obteniendo las credenciales,
     * directamente del enum Credentials.
     *
     * @throws SQLException si no consigue conectarse.
     */
    public void stablishConnection() throws SQLException {
        this.conn = Connections.createConnection();
    }

    /**
     *
     * Establece una conexión al sistema de BBDD, dados los siguientes parámetros.
     *
     * @param url la url de conexión a la base de datos.
     * @param username el usuario de conexión.
     * @param password la contraseña para el usuario.
     * @throws SQLException si no se consigue hacer la conexión.
     */
    public void configureNewConnection(String url, String username, String password) throws SQLException {
        this.conn = Connections.createConnection(url,username,password);
    }

    /*
     *
     * CLIENTES
     *
     * */

    /**
     *
     * Buscara el último cliente, obtendra su ID y le sumará 1.
     *
     * @return el incremento del nuevo ID.
     * @throws SQLException si ocurre algún error a la hora de realizar la consulta.
     */
    public int obtenerNuevoIDCliente() throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(Statements.GET_LAST_CLIENT_ID.getQuery());
        if(rs.next())
            return (1 + rs.getInt(1));
        return 0;
    }

    /**
     *
     * Devuelve una lista de objetos de tipo Client, con la consulta de que obtendra
     * todos los clientes que se encuentren registrados en la base de datos.
     *
     * @return una lista con todos los clientes de la base de datos.
     * @throws SQLException si ocurre algún error a la hora de realizar la consulta.
     */
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

    /**
     *
     * Devuelve una lista de objetos de tipo Client, con la consulta de que obtendrá
     * todos los clientes que su primer nombre coincida con el parámetro obtenido, utilizando
     * LIKE permite utilizar sus funciones de filtrado.
     *
     * @param name el nombre del cliente a buscar.
     * @return una lista de clientes que coincidan con el nombre pasado por parámetros.
     * @throws SQLException si ocurre algún error a la hora de realizar la consulta.
     */
    public List<Client> buscarClientePorNombre(String name) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(String.format(Statements.GET_CLIENT_BY_NAME.getQuery(),name));
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

    /**
     *
     * Devuelve un objeto de tipo cliente con los datos obtenidos de la consulta individual de un cliente,
     * donde se busca obtener una salida que el id pasado por parámetros coincida con el de algún cliente.
     *
     * @param id el identificador del cliente a devolver.
     * @return un objeto de tipo cliente con todos sus datos.
     * @throws Exception si ocurre algún error a la hora de realizar la consulta.
     */
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

    /**
     *
     * Permite agregar un nuevo cliente a la base datos obteniendo sus datos a traves de un nuevo objeto cliente.
     * Obtendra todos los datos necesarios, menos su identificador que lo obtendra a través del método obtenerNuevoIDCliente().
     *
     * @param c el cliente a registrar.
     * @return si ha sido concluyente la actualización.
     * @throws SQLException si ocurre algún error a la hora de realizar la consulta.
     */
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

    /**
     *
     * Devuelve una lista de objetos de tipo Product, con la consulta de que obtendra
     * todos los productos que se encuentren registrados en la base de datos.
     *
     * @return una lista con todos los productos.
     * @throws SQLException si ocurre algún error a la hora de realizar la consulta.
     */
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

    /**
     *
     * Devuelve una lista de objetos de tipo Product, con la consulta de que obtendrá
     * todos los productos que su título coincida con el parámetro obtenido, utilizando
     * LIKE permite utilizar sus funciones de filtrado.
     *
     * @param title el título del producto a encontrar.
     * @return una lista con los productos que coincidan con el título.
     * @throws SQLException si ocurre algún error a la hora de realizar la consulta.
     */
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

    /**
     *
     * Devuelve un objeto de tipo product, con la consulta de obtener un único resultado
     * obteniendo un dato individual de un producto y devolver esos datos en un objeto de tipo
     * Product.
     *
     * @param id el identificador del producto a devolver.
     * @return un objeto de tipo Product con todos los datos obtenidos.
     * @throws SQLException si ocurre algún error a la hora de realizar la consulta.
     */
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

    /**
     *
     * Permite agregar un nuevo producto a la base datos obteniendo sus datos a traves de un nuevo objeto Product.
     * Obtendra todos los datos necesarios.
     *
     * @param p el producto a registrar en la base de datos.
     * @return si ha concluido de forma satisfactoria la operación.
     * @throws SQLException si ocurre algún error a la hora de realizar la consulta.
     */
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

    /**
     *
     * Permite actualizar el stock de un producto, dado el producto y su cantidad a restar.
     *
     * @param p el producto a cambiar.
     * @param update la cantidad a restar.
     * @return si la operación a concluido satisfactoriamente.
     * @throws SQLException si ocurre algún error a la hora de realizar la consulta.
     */
    public boolean updateProductStockDecrement(Product p, int update) throws SQLException {
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

    /**
     *
     * Permite agregar un nuevo encargo a la base datos obteniendo su cliente de un objeto Client.
     *
     * @param c el cliente al cual coger su identificador.
     * @return si ha concluido de forma satisfactoria la operación.
     * @throws SQLException si ocurre algún error a la hora de realizar la consulta.
     */
    public boolean agregarEncargo(Client c) throws SQLException {
        Statement stmt = conn.createStatement();
        return stmt.execute(String.format(Statements.INSERT_NEW_ORDER.getQuery(),c.getId()));
    }

    /**
     *
     * Permite agregar un nuevo registro en la tabla order_product, donde se registrará un producto de forma individual
     * a su encargo, con el identificador del encargo, el producto y su cantidad.
     *
     * @param e el identificador del encargo.
     * @param p el producto a incluir (obteniendo su identificador).
     * @param i la cantidad del producto.
     * @return si la operación a concluido de forma satisfactoria.
     * @throws SQLException si ocurre algún error a la hora de realizar la consulta.
     */
    public boolean agregarProductoAEncargo(Integer e,Product p, Integer i) throws SQLException {
        Statement stmt = conn.createStatement();
        return stmt.execute(String.format(
                Statements.INSERT_NEW_ORDER_PRODUCT.getQuery()
                ,e
                ,p.getId()
                ,i
        ));
    }

    /**
     *
     * Permite agregar de forma general un listado completo de productos a cierto encargo.
     *
     * @param encargo el identificador del encargo.
     * @param productos la lista completa de productos.
     * @throws Exception si ocurre algún error a la hora de realizar la consulta.
     */
    public void agregarProductosAEncargo(int encargo, HashMap<Product,Integer> productos) throws Exception {
        for(Map.Entry<Product,Integer> p : productos.entrySet()){
            agregarProductoAEncargo(encargo,p.getKey(),p.getValue());
        }
    }

    /**
     *
     * Obtiene el identificador del último encargo de un cliente. Utilizado a la hora de crear
     * los registros de los productos.
     *
     * @param c el cliente a obtener su último encargo.
     * @return el identificador del encargo.
     * @throws Exception si ocurre algún error a la hora de realizar la consulta.
     */
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

    /**
     *
     * Obtiene todos los encargos que haya registrados en la base de datos.
     *
     * @return una lista de objetos de tipo Order con todos los encargos.
     * @throws Exception si ocurre algún error a la hora de realizar la consulta.
     */
    public ArrayList<Order> obtenerTodosLosEncargos() throws Exception {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(Statements.SELECT_ALL_ORDERS.getQuery());

        ArrayList<Order> orders = new ArrayList<>();

        while(rs.next()){
            orders.add(obtenerEncargo(rs.getInt("id")));
        }

        return orders;
    }

    /**
     *
     * Obtiene una lista con los encargos de cierto cliente.
     *
     * @param c el cliente a obtener los encargos.
     * @return una lista con todos sus encargos.
     * @throws Exception si ocurre algún error a la hora de realizar la consulta.
     */
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

    /**
     *
     * Obtener de forma individual un encargo, con todas sus dependencias.
     * Objetos de tipo cliente, un listado de productos con sus cantidades, fecha y identificación.
     *
     * @param id el identificador del encargo a obtener todos sus datos.
     * @return un objeto de tipo Order.
     * @throws Exception si ocurre algún error a la hora de realizar la consulta.
     */
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

    /**
     *
     * Permite obtener el listado completo de los productos que incluye un cierto encargo.
     *
     * @param encargo el identificador del encargo a encontrar.
     * @return un listado con productos y cantidades.
     * @throws Exception si ocurre algún error a la hora de realizar la consulta.
     */
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

    /**
     *
     * Permite eliminar un encargo.
     *
     * @param id el identificador del encargo a eliminar.
     * @return si la operación concluye de forma satisfactoria.
     * @throws SQLException si ocurre algún error a la hora de realizar la consulta.
     */
    public boolean eliminarEncargo(int id) throws SQLException {
        Statement stmt = conn.createStatement();
        return stmt.execute(String.format(
                Statements.DELETE_ORDER_BY_ID.getQuery()
                ,id
        ));
    }

    /**
     *
     * Permite cerrar la conexión con la base de datos.
     *
     * @throws SQLException si ocurre algún error a la hora de realizar la consulta.
     */
    public void close() throws SQLException {
        this.conn.close();
    }

}
