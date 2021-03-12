package Application.Entities;

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

public class Client {

    private int id;
    private String firstName;
    private String secondName;
    private String firstLastname;
    private String secondLastname;
    private String streetAddress;
    private String mailAddress;
    private String phoneNumber;

    public Client(int id, String firstName, String secondName, String firstLastname, String secondLastname, String streetAddress, String mailAddress, String phoneNumber) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.firstLastname = firstLastname;
        this.secondLastname = secondLastname;
        this.streetAddress = streetAddress;
        this.mailAddress = mailAddress;
        this.phoneNumber = phoneNumber;
    }

    public int getId() {
        return id;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getSecondName() {
        return secondName;
    }
    public String getFirstLastname() {
        return firstLastname;
    }
    public String getSecondLastname() {
        return secondLastname;
    }
    public String getStreetAddress() {
        return streetAddress;
    }
    public String getMailAddress() {
        return mailAddress;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }

}
