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
    private String date;
    private User user;
    private String content;

    private Message(int id, String date, User user, String content) {
        this.id = id;
        this.date = date;
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

    public String getDate() {
        return date;
    }

    public User getUser() {
        return user;
    }

    public String getContent() {
        return content;
    }

    public String toSQL(){
        return String.format("INSERT INTO message(user,content) VALUES (%d,'%s')",getUser().getId(),getContent());
    }

    public static Message retrieve(int id, String date, User user, String content){
        return new Message(id,date,user,content);
    }

    public static Message generate(User user, String content){
        return new Message(user,content);
    }

}
