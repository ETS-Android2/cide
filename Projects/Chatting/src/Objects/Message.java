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

public class Message {

    private int id;
    private User user;
    private String content;

    private Message(int id, User user, String content) {
        this.id = id;
        this.user = user;
        this.content = content;
    }

    private Message(User user, String content) {
        this.user = user;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public String getContent() {
        return content;
    }

    // TODO
    public String toSQL(){
        return "";
    }

    public static Message retrieve(int id, User user, String content){
        return new Message(id,user,content);
    }

    public static Message generate(User user, String content){
        return new Message(user,content);
    }

}
