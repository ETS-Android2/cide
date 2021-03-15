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

    INSERT_NEW_CLIENT("INSERT INTO client(id,name,name_2,lastname,lastname_2,street_address,mail_address,phone_number) VALUES (%d,'%s','%s','%s','%s','%s','%s','%s')"),
    INSERT_NEW_PRODUCT("INSERT INTO product(title,description,price) VALUES ('%s','%s',%f)"),
    INSERT_NEW_ORDER("INSERT INTO client_order(client) VALUES (%d)"),
    INSERT_NEW_ORDER_PRODUCT("INSERT INTO order_product(product,quantity) VALUES (%d,%f)"),

    // SPECIFIC
    GET_LAST_CLIENT_ID("SELECT MAX(id) FROM client"),
    GET_CLIENT_BY_NAME("SELECT * FROM client WHERE name = '%s'"),
    GET_ALL_CLIENT_ORDERS("SELECT * FROM client_order WHERE client = %d"),

    GET_PRODUCT_BY_TITLE("SELECT * FROM product WHERE UPPER(title) LIKE '%s'"),

    GET_ALL_PRODUCT_ID_AND_QUANTITY_IN_ORDER("SELECT * FROM order_product WHERE order_id = %d")
    ;

    private final String query;

    Statements(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }

}
