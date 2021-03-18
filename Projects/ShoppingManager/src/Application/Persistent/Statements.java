package Application.Persistent;

/*

    Project     Programming21
    Package     Data    
    
    Version     1.0      
    Author      Carlos Pomares
    Date        2021-03-12

    DESCRIPTION
    
*/

/**
 * @author Carlos Pomares
 */

public enum Statements {

    // GENERAL
    SELECT_ALL_CLIENTS("SELECT * FROM client"),
    SELECT_ALL_PRODUCTS("SELECT * FROM product"),
    SELECT_ALL_ORDERS("SELECT * FROM order"),
    SELECT_ALL_PRODUCTS_IN_ORDERS("SELECT * FROM order_product"),

    INSERT_NEW_CLIENT("INSERT INTO client (id,name,name_2,lastname,lastname_2,street_address,mail_address,phone_number) VALUES (%d,'%s','%s','%s','%s','%s','%s','%s')"),
    INSERT_NEW_PRODUCT("INSERT INTO product (title,description,price,stock) VALUES ('%s','%s',%s,%d)"),
    INSERT_NEW_ORDER("INSERT INTO client_order (client) VALUES (%d)"),
    INSERT_NEW_ORDER_PRODUCT("INSERT INTO order_product (order_id,product, quantity) VALUES (%d,%d,%d);"),

    // SPECIFIC
    GET_LAST_CLIENT_ID("SELECT MAX(id) FROM client"),
    GET_CLIENT_BY_NAME("SELECT * FROM client WHERE UPPER(name) LIKE '%s'"),
    GET_CLIENT_BY_ID("SELECT * FROM client WHERE id = %d"),
    GET_ALL_CLIENT_ORDERS("SELECT * FROM client_order WHERE client = %d"),
    GET_LAST_CLIENT_ORDER("SELECT * FROM client_order WHERE client = %d ORDER BY date DESC LIMIT 1"),

    GET_PRODUCT_BY_TITLE("SELECT * FROM product WHERE UPPER(title) LIKE '%s'"),
    GET_PRODUCT_BY_ID("SELECT * FROM product WHERE id = %d"),
    UPDATE_PRODUCT_DECREMENT_STOCK_BY_NUMBER("UPDATE product SET stock = stock - %d WHERE id = %d"),

    GET_ORDER_BY_ID("SELECT * FROM client_order WHERE id = %d"),
    GET_ALL_PRODUCT_ID_AND_QUANTITY_IN_ORDER("SELECT * FROM order_product WHERE order_id = %d"),
    DELETE_ORDER_BY_ID("DELETE FROM client_order WHERE id = %d")
    ;

    private final String query;

    Statements(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }

}
