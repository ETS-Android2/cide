package services;

import common.data.Line;
import common.specification.Fish;
import common.specification.StatisticResult;
import common.specification.Statistics;
import common.specification.User;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class PescaAPISimple extends PescaAPI {

    public PescaAPISimple() throws IOException {

        createFlowContainer();

        boolean flow;
        do {
            flow = establishDataFlows();
        } while (!flow);
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
    @Override
    public boolean getUserByIdentifier(String user) throws IOException {
        return this.searchDataInFlow(
                read(parseKey("flow","users.txt"))
                ,'#'
                ,1
                ,1
                ,user
        );
    }


    public int getUserLine(String user) throws Exception {

        if(!getUserByIdentifier(user))
            throw new Exception("User not exists.");

        return getLinePositionInFlow(
                read(parseKey("flow","users.txt"))
                ,'#'
                ,1
                ,1
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
    @Override
    public void registerUser(String identifier) throws Exception {

        if(getUserByIdentifier(identifier)){
            throw new Exception("User already exists.");
        }

        // Prepare data for file
        identifier = '#' + identifier + '#' + '\n';

        Line l = new Line(toComplex(identifier.getBytes()),'#');

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
    @Override
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

    /**
     *
     * Allow to obtain an user by the class specificaction for usage in another methods.
     *
     * @param identifier to search.
     * @return an User class containing the identifier.
     * @throws Exception if the user does not exists.
     */
    @Override
    public User getUser(String identifier) throws Exception {

        if(!getUserByIdentifier(identifier)){
            throw new Exception("User does not exist.");
        }

        Line l = getLineDataInFlow(
                read(parseKey("flow","users.txt"))
                ,'#'
                ,1
                ,getUserLine(identifier)
        );

        return new User(l.getData()[0]);
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
    @Override
    public void registerNewAction(String user, Fish fish) throws Exception {

        if(!getUserByIdentifier(user)){
            throw new Exception("The user not exists.");
        }

        // Prepare data for insert
        LocalDate date = LocalDate.now();
        user = '#' + user + '#' + fish.getName() + '#' + fish.getSize() + '#' + date + '#' + '\n';

        Line l = new Line(toComplex(user.getBytes()),'#');

        appendData(
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
    @Override
    public Fish getFish(String key) throws IOException {

        float random = (float) (Math.random() * 1.5f);

        int line = getLineWhereCondition(
                read(key)
                ,'#'
                ,4
                ,2
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
    @Override
    public StatisticResult getStatistics() throws IOException {

        ArrayList<Line> lines = parseLines(
                read(parseKey("flow","registers.txt"))
                ,'#'
                ,4
        );
        ArrayList<Statistics> statistics = new ArrayList<>();

        for(Line l : lines){
            statistics.add(new Statistics(l.getData()[1],l.getData()[2].getFloatValue()));
        }

        return new StatisticResult(statistics);
    }

    /**
     *
     * Get all statistics filtered by the user.
     *
     * @param user the user to filter.
     * @return the statistics parsed.
     * @throws IOException if something of the Input/Output fails.
     */
    @Override
    public StatisticResult getStatistics(String user) throws IOException {

        ArrayList<Line> lines = parseLines(
                read(parseKey("flow","registers.txt"))
                ,'#'
                ,4
        );
        ArrayList<Statistics> statistics = new ArrayList<>();

        for(Line l : lines){
            if(l.getData()[0].getStringValue().equals(user)){
                statistics.add(new Statistics(l.getData()[1],l.getData()[2].getFloatValue()));
            }
        }

        return new StatisticResult(statistics);
    }

}