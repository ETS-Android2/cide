package Application.Entities;

import java.sql.Timestamp;
import java.util.HashMap;

public class Order {

    /**
     * Identificador del encargo
     */
    private final int id;
    /**
     * Fecha de tipo timestamp del encargo
     */
    private final Timestamp orderDate;
    /**
     * Cliente del encargo
     */
    private final Client client;
    /**
     * Lista de productos del encargo.
     */
    private final HashMap<Product,Integer> products;

    /**
     *
     * Entidad Order, contiene sus parámetros de identificación y fecha,
     * así como la entidad Client y un hashmap de productos, con la entidad
     * Product y su cantidad.
     *
     * @param i el id del encargo.
     * @param c el cliente de tipo Cliente.
     * @param p lista de productos con sus cantidades.
     * @param d fecha de encargo de tipo Timestamp.
     */
    public Order(int i,Client c, HashMap<Product,Integer> p, Timestamp d){
        assert i != 0;
        assert c != null;
        assert p != null;
        assert d != null;
        this.id = i;
        this.client = c;
        this.products = p;
        this.orderDate = d;
    }

    public int getId() {
        return id;
    }

    public Timestamp getOrderDate() {
        return orderDate;
    }

    public Client getClient() {
        return client;
    }

    public HashMap<Product, Integer> getProducts() {
        return products;
    }

}
