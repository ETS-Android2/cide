package Objects;

/*

    Project     Programming21
    Package     Objects    
    
    Version     1.0      
    Author      Carlos Pomares
    Date        2021-02-25

    DESCRIPTION
    
*/

/**
 * @author Carlos Pomares
 */

public class User {

    private int id;
    private String username;
    private String password;

    private User(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    private User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public static User generate(String username, String password){
        return new User(username,password);
    }

    public static User retrieve(int id, String username, String password){
        return new User(id,username,password);
    }

}
