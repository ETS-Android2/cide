package Objects;

/*

    Project     Programming21
    Package     Objects    
    
    Version     1.0      
    Author      Carlos Pomares
    Date        2021-02-25

    DESCRIPTION
    
*/

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/**
 * @author Carlos Pomares
 */

public class Room {

    final private int id;
    final private int user;
    final private String title;
    final private String description;
    final private boolean visibility;

    private Room(int id, int user, String title, String description, boolean visibility) {
        this.id = id;
        this.user = user;
        this.title = title;
        this.description = description;
        this.visibility = visibility;
    }

    public int getId() {
        return id;
    }

    public int getUser() {
        return user;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public boolean isVisibility() {
        return visibility;
    }

    public static boolean create(){
        return true;
    }

    public static boolean connect(Room room){
        return true;
    }

    public static boolean sendMessage(Message message, Room room){
        return true;
    }

    public static HashMap<User,Message> getMessages(Room room){
        return new HashMap<>();
    }

}
