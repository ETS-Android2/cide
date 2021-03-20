/*
 *       Filename:  PR19203AU05E02Queries.java
 *
 *    Description:
 *
 *        Created:  13 d’abr. 2020
 *       Revision:  none
 *
 *        @Author:  xavier - xavier.sastre@cide.es
 *       @Version:  1.0
 *
 * =====================================================================================
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

public class Queries {
    private static final String URL = "jdbc:mysql://194.224.79.42:43306/addressbook?useUnicode=true&useTimezone=true&serverTimezone=UTC&useSSL=false";
    private static final String USERNAME = "alumne";
    private static final String PASSWORD = "tofol";
    private Connection connection; // manages connection
    private PreparedStatement selectAllPeople;
    private PreparedStatement selectPeopleByLastName;
    private PreparedStatement getLastId;
    private PreparedStatement insertNewPerson;
    private PreparedStatement updatePerson;
    private PreparedStatement removePerson;
    // constructor
    public Queries()
    {
        try
        {
            connection =
                    DriverManager.getConnection(URL, USERNAME, PASSWORD);
            // create query that selects all entries in the AddressBook
            selectAllPeople =
                    connection.prepareStatement("SELECT * FROM ADDRESSES");
            // create query that selects entries with a specific last name
            selectPeopleByLastName = connection.prepareStatement(
                    "SELECT * FROM ADDRESSES WHERE LastName = ?");
            // get max id from the database and increment in 1
            getLastId = connection.prepareStatement("SELECT MAX(ADDRESSID) as ID FROM ADDRESSES");
            // create insert that adds a new entry into the database
            insertNewPerson = connection.prepareStatement(
                    "INSERT INTO ADDRESSES " +
                            "(ADDRESSID,FirstName, LastName, Email, PhoneNumber) " +
                            "VALUES (?, ?, ?, ?, ?)");
            // update that set new values to current entry into the database
            updatePerson = connection.prepareStatement("UPDATE ADDRESSES " +
                    "SET FIRSTNAME = ?, LASTNAME = ?, EMAIL = ?, PHONENUMBER = ? "
                    + " WHERE ADDRESSID = ?");
            // remove address from the database
            removePerson = connection.prepareStatement("DELETE FROM ADDRESSES WHERE ADDRESSID = ?");
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
            System.exit(1);
        }
    }
    // select all of the addresses in the database
    public List< Persona > getAllPeople()
    {
        List< Persona > results = null;
        ResultSet resultSet = null;
        try
        {
            // executeQuery returns ResultSet containing matching entries
            resultSet = selectAllPeople.executeQuery();
            results = new ArrayList< Persona >();
            while (resultSet.next())
            {
                results.add(new Persona(
                        resultSet.getInt("addressID"),
                        resultSet.getString("FirstName"),
                        resultSet.getString("LastName"),
                        resultSet.getString("Email"),
                        resultSet.getString("PhoneNumber")));
            }
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        finally
        {
            try
            {
                resultSet.close();
            }
            catch (SQLException sqlException)
            {
                sqlException.printStackTrace();
                close();
            }
        }
        return results;
    }
    // select person by last name
    public List<Persona> getPeopleByLastName(String name)
    {
        List< Persona > results = null;
        ResultSet resultSet = null;
        try
        {
            selectPeopleByLastName.setString(1, name); // specify last name

            // executeQuery returns ResultSet containing matching entries
            resultSet = selectPeopleByLastName.executeQuery();
            results = new ArrayList< Persona >();
            while (resultSet.next())
            {
                results.add(new Persona(resultSet.getInt("addressID"),
                        resultSet.getString("FirstName"),
                        resultSet.getString("LastName"),
                        resultSet.getString("Email"),
                        resultSet.getString("PhoneNumber")));
            }
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        finally
        {
            try
            {
                resultSet.close();
            }
            catch (SQLException sqlException)
            {
                sqlException.printStackTrace();
                close();
            }
        }
        return results;
    }
    // add an entry
    public int addPerson(
            String fname, String lname, String email, String num)
    {
        int result = 0;
        // set parameters, then execute
        try
        {
            insertNewPerson.setInt(1,generateNewId());
            insertNewPerson.setString(2,fname);
            insertNewPerson.setString(3,lname);
            insertNewPerson.setString(4,email);
            insertNewPerson.setString(5,num);

            // insert the new entry; returns # of rows updated
            result = insertNewPerson.executeUpdate();
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
            close();
        }
        return result;
    }

    public int generateNewId(){
        int result = 0;
        try {
            ResultSet rs = getLastId.executeQuery();
            rs.next();
            result = rs.getInt("ID");
        } catch (SQLException sqlException){
            sqlException.printStackTrace();
            close();
        }
        return result + 1;
    }

    public int updatePerson(int id,String fname, String lname, String email, String num){
        int result = 0;
        try {
            updatePerson.setString(1,fname);
            updatePerson.setString(2,lname);
            updatePerson.setString(3,email);
            updatePerson.setString(4,num);
            updatePerson.setInt(5,id);
            result = updatePerson.executeUpdate();
        } catch (SQLException sqlException){
            sqlException.printStackTrace();
            close();
        }
        return result;
    }

    public int removePerson(int id){
        int result = 0;
        try {
            removePerson.setInt(1,id);
            result = removePerson.executeUpdate();
        } catch (SQLException sqlException){
            sqlException.printStackTrace();
            close();
        }
        return result;
    }

    // close the database connection
    public void close()
    {
        try
        {
            connection.close();
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }
} // end class PersonQueries