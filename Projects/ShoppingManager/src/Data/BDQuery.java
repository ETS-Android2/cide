package Data;

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

public enum BDQuery {

    SELECT_ALL_CLIENTS(""),
    SELECT_ALL_PRODUCTS(""),
    SELECT_ALL_ORDERS(""),
    SELECT_ALL_PRODUCTS_IN_ORDER("");

    private final String query;

    BDQuery(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }

}
