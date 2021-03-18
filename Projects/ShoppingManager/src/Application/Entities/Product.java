package Application.Entities;

/*

    Project     Programming21
    Package     Application.Entities    
    
    Version     1.0      
    Author      Carlos Pomares
    Date        2021-03-12

    DESCRIPTION
    
*/

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Objects;

/**
 * @author Carlos Pomares
 */

public class Product {

    private int id;
    private String title;
    private String description;
    private float price;
    private int stock;

    public Product(String t, String d, float p, int stock) {
        this.title = t;
        this.description = d;
        this.price = p;
        this.stock = stock;
    }

    public Product(int i, String t, String d, float p, int stock) {
        this.id = i;
        this.title = t;
        this.description = d;
        this.price = p;
        this.stock = stock;
    }

    public int getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }
    public float getPrice() {
        return price;
    }
    public int getStock() {
        return stock;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
