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

    /**
     *
     * Enumeración de las diferentes credenciales utilizadas
     * para la conexión con el sistem de BBDD.
     *
     * Establecer aquí las credenciales permite hacer una conexión
     * directa con la base de datos y no tener que configurarla dentro
     * del programa.
     *
     */

    USERNAME("USERNAME"),
    PASSWORD("PASSWORD"),
    DATABASE("DATABASE"),
    HOST("HOST"),
    PORT("PORT");

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
