package Application.Entities;

import java.util.HashMap;

public class Order {

    private Client client;
    private HashMap<Product,Float> products;

    public Order(Client c, HashMap<Product,Float> p){
        this.client = c;
        this.products = p;
    }

}
