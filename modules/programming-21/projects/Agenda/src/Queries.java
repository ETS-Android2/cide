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

    // Permite editar los campos de una entrada en la base de datos por ID.
    private PreparedStatement updatePerson;

    // Permite eliminar una entrada a partir del ID.
    private PreparedStatement removePerson;

    // Permite obtener un resultado de una entrada a partir de su ID.
    private PreparedStatement getIdExists;

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
                    "SELECT * FROM ADDRESSES WHERE UPPER(LastName) LIKE ?");
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
            getIdExists = connection.prepareStatement("SELECT * FROM ADDRESSES WHERE ADDRESSID = ?");
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
            selectPeopleByLastName.setString(1, name + "%"); // specify last name

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

    /*public int generateNewId(){
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
    }*/

    /**
     *
     * Permite generar un nuevo ID de forma aleatoria.
     * De forma recursiva irá probando identificadores hasta encontrar
     * uno que no este ocupado por una entranda en la base de datos.
     *
     * @return el id generado.
     */
    public int generateNewId(){
        int result = generateRandomNewId();
        if(!idExists(result))
            return result;
        else
            return generateNewId();
    }

    /**
     *
     * Genera un número aleatorio entre 0 y 999.999
     *
     * @return un número aleatorio entre 0 y 999.999
     */
    public int generateRandomNewId(){
        return (int)(Math.random() * 999999) + 1;
    }

    /**
     *
     * Permite actualizar los valores de una cierta entrada en la base de datos.
     * Donde se introducen los nuevos valores y actuales. A través de su identificador
     * actualizamos los valores.
     *
     * @param id el identificador de la entrada.
     * @param fname el nombre a determinar.
     * @param lname el apellido a determinar.
     * @param email el email a determinar.
     * @param num el número de teléfono a determinar.
     * @return 1 si se ha completado el cambio en la base de datos, 0 si no se ha completado.
     */
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

    /**
     *
     * Permite eliminar una entrada en la base de datos pasando su identificador.
     *
     * @param id el identificador de la entrada a eliminar.
     * @return 1 si se ha completado satisfactoriamente, 0 si no.
     */
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

    /**
     *
     * Comprueba si el id pasado por parámetros obtiene alguna respuesta de la base de datos.
     *
     * @param id el id a comprobar en la base de datos.
     * @return true si el id existe, false si no existe.
     */
    public boolean idExists(int id){
        boolean result = false;
        try {
            getIdExists.setInt(1,id);
            ResultSet rs = getIdExists.executeQuery();
            if(rs.next())
                result = true;
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