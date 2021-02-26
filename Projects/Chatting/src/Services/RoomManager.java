package Services;

/*

    Project     Programming21
    Package     Services    
    
    Version     1.0      
    Author      Carlos Pomares
    Date        2021-02-25

    DESCRIPTION
    
*/

import Objects.Message;
import Objects.Room;
import Objects.User;
import java.util.HashMap;

/**
 * @author Carlos Pomares
 */

public class RoomManager {

    private Room connected;
    private User user;
    private HashMap<User,Message> messages;

    public RoomManager(){
        messages = new HashMap<>();
    }

    public Room getConnected() {
        return connected;
    }
    public User getUser() {
        return user;
    }
    public HashMap<User, Message> getMessages() {
        return messages;
    }

    public static boolean setUser(RoomManager roomManager,User user){
        roomManager.user = user;
        return true;
    }
    public static boolean checkMessages(RoomManager manager){
        return Room.getMessages(manager.connected).size() > manager.messages.size();
    }
    public static boolean updateMessages(RoomManager manager){
        if(!checkMessages(manager)){
            return false;
        }
        manager.messages = Room.getMessages(manager.connected);
        return true;
    }
    public static boolean changeRoom(RoomManager roomManager, Room room){
        roomManager.connected = room;
        return true;
    }

}
