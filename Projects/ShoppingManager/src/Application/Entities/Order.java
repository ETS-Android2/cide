package Application.Entities;

import java.sql.Timestamp;
import java.util.HashMap;

public class Order {

    private int id;
    private Timestamp orderDate;
    private Client client;
    private HashMap<Product,Integer> products;

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

    public Order(Client c, HashMap<Product,Integer> p){
        assert c != null;
        assert p != null;
        this.client = c;
        this.products = p;
        makeOrder();
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

    private boolean makeOrder(){

        // CREATE ORDER WITH CLIENT


        // FOR EACH PRODUCT CREATE REFERENCE WITH ORDER_ID

        return false;
    }

}
