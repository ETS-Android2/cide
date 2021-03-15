package Application.Persistent;

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

public enum Credentials {

    USERNAME("dam"),
    PASSWORD("Cide2050"),
    DATABASE("order_manager"),
    HOST("localhost"),
    PORT("3306");

    private final String data;

    Credentials(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public static String getUrl(){
        return String.format("jdbc:mysql://%s:%s/%s",HOST.data,PORT.data,DATABASE.data);
    }

}
