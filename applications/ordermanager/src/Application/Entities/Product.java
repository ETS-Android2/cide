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

    /**
     * Identificador del producto
     */
    private int id;
    /**
     * Título del producto
     */
    private final String title;
    /**
     * Descripción del producto
     */
    private final String description;
    /**
     * Precio del producto.
     */
    private final float price;
    /**
     * Stock del producto.
     */
    private final int stock;

    /**
     *
     * Entidad producto, permite crear un producto nuevo sin saber el ID.
     *
     * @param t el título del producto.
     * @param d la descripción del producto.
     * @param p el precio del producto.
     * @param stock la cantidad de stock del producto.
     */
    public Product(String t, String d, float p, int stock) {
        this.title = t;
        this.description = d;
        this.price = p;
        this.stock = stock;
    }

    /**
     *
     * Entidad producto, con conocimiento de su identificador, uso en
     * resultado provinientes de la base de datos.
     *
     * @param i el identificador del producto.
     * @param t el título del producto.
     * @param d la descripción del producto.
     * @param p el precio del producto.
     * @param stock la cantidad de stock del producto.
     */
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

    /**
     *
     * Comprueba si el id del producto es igual al del producto obtenido por parámetros.
     *
     * @param o un objecto de tipo Product.
     * @return si el id es igual al del producto pasado por parámetros.
     */
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
