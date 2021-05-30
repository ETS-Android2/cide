package services;

import common.data.Line;
import common.specification.*;
import transformation.Transform;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;

public class PescaAPI extends FileAPI {

    /**
     *
     * The instance of the API, at first time, tries to create flow container in the user home.
     * Then tries to establish all data flows needed by the API.
     *
     * @throws IOException if something of the Input/Output fails.
     */
    public PescaAPI() throws IOException {

        createFlowContainer();

        boolean flow;
        do {
            flow = establishDataFlows();
        } while (!flow);
    }

    /**
     *
     * Creates all the flows needed by the API, detects if exists, if not,
     * then tries to create it.
     *
     * @return if all flows are successfully created or already exists.
     * @throws IOException if something of the Input/Output fails.
     */
    protected boolean establishDataFlows() throws IOException {

        try {
            File file = new File(parseKey("flow","users.txt"));
            if(!file.exists()){
                throw new FileNotFoundException();
            }
        } catch (FileNotFoundException e){
            createFileEmpty("flow","users.txt");
            return false;
        }

        try {
            File file = new File(parseKey("flow","boats.txt"));
            if(!file.exists()){
                throw new FileNotFoundException();
            }
        } catch (FileNotFoundException e){
            createFileEmpty("flow","boats.txt");
            return false;
        }

        try {
            File file = new File(parseKey("flow","registers.txt"));
            if(!file.exists()){
                throw new FileNotFoundException();
            }
        } catch (FileNotFoundException e){
            createFileEmpty("flow","registers.txt");
            return false;
        }

        return true;
    }

    /* ======================================
        USERS METHODS
     ====================================== */

    /**
     *
     * Obtain all data in the users.txt, then parse all lines and
     * for each line check if the user input is equal to one of them.
     * If there is some coincidence then returns true else returns false.
     *
     * @param user the user to check.
     * @return if the users exists or not.
     * @throws IOException if something of the Input/Output fails.
     */
    public boolean getUserByIdentifier(String user) throws IOException {
        return this.searchDataInFlow(
                read(parseKey("flow","users.txt"))
                ,'#'
                ,1
                ,0
                ,user
        );
    }


    /**
     *
     * Implementation of User methods to retrieve the line number
     * where the user is identified.
     *
     * @param user the user to identify.
     * @return the line in the file.
     * @throws IOException if something of the Input/Output fails.
     */
    private int getUserLine(String user) throws IOException {
        return getLinePositionInFlow(
                read(parseKey("flow","users.txt"))
                ,'#'
                ,1
                ,0
                ,user
        );
    }

    /**
     *
     * Allow to write a new users in the users.txt. At first time
     * checks if the user is already registered in the flow, if not, proceed
     * to register the new users and for that, reads all users and write all of it
     * again, then writes the new user.
     *
     * @param identifier the user to register.
     * @throws Exception if the user is already registered.
     */
    public void registerUser(String identifier) throws Exception {

        if(getUserByIdentifier(identifier)){
            throw new Exception("User already exists.");
        }

        // Prepare data for file
        identifier = '#' + identifier + '#' + '\n';

        Line l = new Line(Transform.toComplex(identifier.getBytes()),'#');

        this.appendData(
                parseKey("flow","users.txt")
                ,'#'
                ,l
        );
    }



    /**
     *
     * Allow to delete an user of the users.txt flow, at first time check if the user is registered into
     * the flow and read all the users including the one who will be deleted, an then write all of it, when
     * the user is reached and is equal to the input identifier doesn't write it, continues to the next one.
     * The method to delete an user is not writing it again.
     *
     * @param identifier the identifier to delete.
     * @throws Exception if the user is not registered.
     */
    public void deleteUser(String identifier) throws Exception {

        if (!getUserByIdentifier(identifier)){
            throw new Exception("User not exists.");
        }

        removeData(
                parseKey("flow","users.txt")
                ,'#'
                ,1
                ,getUserLine(identifier)
        );
    }

    /* ======================================
        FISH ACTION METHODS
     ====================================== */

    /**
     *
     * Allows to register a new fishing action to the registers.txt.
     * At first time check if the user exists and then get the date of now.
     * Then creates the string that follows this pattern #<USER>#<FISH>#<SIZE>#<DATE>#.
     *
     * @param user the user to register the fish action.
     * @param fish the fish to register to the fish action.
     * @throws Exception if the user does not exists at the time of the action.
     */
    public void registerNewAction(String user, Fish fish) throws Exception {

        if(!getUserByIdentifier(user)){
            throw new Exception("The user not exists.");
        }

        // Prepare data for insert
        LocalDate date = LocalDate.now();
        user = '#' + user + '#' + fish.getName() + '#' + fish.getSize() + '#' + date + '#' + '\n';

        Line l = new Line(Transform.toComplex(user.getBytes()),'#');

        this.appendData(
                parseKey("flow","registers.txt")
                ,'#'
                ,l
        );
    }

    /* ======================================
        FISH METHODS
     ====================================== */

    /**
     *
     * Obtain all the fish data inside the data flows, then for each fish generates a
     * random number and tries to get the higher fish between the ranges of the random.
     *
     * @param key the key to search the data of the fish /data/florida.txt
     * @return a fish instance containing all fish data.
     * @throws IOException if something of the Input/Output fails.
     */
    public Fish getFish(String key) throws IOException {

        float random = (float) (Math.random() * 1.2f);

        int line = getLineWhereCondition(
                read(key)
                ,'#'
                ,4
                ,1
                ,random
                ,DataOperation.HIGHER_OR_EQUAL
        );

        Line l = getLineDataInFlow(
                read(key)
                ,'#'
                ,4
                ,line
        );

        return new Fish(
                l.getData()[0],
                l.getData()[1],
                l.getData()[2],
                l.getData()[3]
        );
    }

    /* ======================================
        STATISTICS METHODS
     ====================================== */

    /**
     *
     * Get all statistics of the registers.txt
     *
     * @return the statistics parsed.
     * @throws IOException if something of the Input/Output fails.
     */
    public StatisticResult getStatistics() throws IOException {
        return new StatisticResult(
                read(parseKey("flow","registers.txt"))
                ,'#'
                ,4
                ,1
                ,2
        );
    }

    /**
     *
     * Get all statistics filtered by the user.
     *
     * @param user the user to filter.
     * @return the statistics parsed.
     * @throws IOException if something of the Input/Output fails.
     */
    public StatisticResult getStatistics(String user) throws IOException {

        if(
            !searchDataInFlow(
                read(parseKey("flow","registers.txt"))
                ,'#'
                ,4
                ,0
                ,user
            )
        )
        {
            throw new IOException("User not exists in registers.txt");
        }

        return new StatisticResult(
                read(parseKey("flow","registers.txt"))
                ,'#'
                ,4
                ,1
                ,2
                ,0
                ,user
        );
    }

}