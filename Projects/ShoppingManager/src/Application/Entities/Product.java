package Application.Entities;

/*

    Project     Programming21
    Package     Application.Entities    
    
    Version     1.0      
    Author      Carlos Pomares
    Date        2021-03-12

    DESCRIPTION
    
*/

/**
 * @author Carlos Pomares
 */

public class Product {

    private int id;
    private String title;
    private String description;
    private float price;

    public Product(String t, String d, float p) {
        this.title = t;
        this.description = d;
        this.price = p;
    }

    public Product(int i, String t, String d, float p) {
        this.id = i;
        this.title = t;
        this.description = d;
        this.price = p;
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

}
