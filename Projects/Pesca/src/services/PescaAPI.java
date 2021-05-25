package services;

import common.data.Line;
import common.specification.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class PescaAPI extends FileAPI implements PescaService {

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
    @Override
    public boolean getUserByIdentifier(String user) throws IOException {
        byte[] raw = getDataFromFlow(read(parseKey("flow","users.txt")));
        ArrayList<Line> lines = parseLines(raw,'#',1);
        for (Line l : lines){
            if(l.getData()[0].getStringValue().equals(user)){
                return true;
            }
        }
        return false;
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

        byte[] raw = getDataFromFlow(read(parseKey("flow","users.txt")));
        FileOutputStream outputStream = execute(parseKey("flow","users.txt"));
        ArrayList<Line> lines = parseLines(raw,'#',1);

        for (Line l : lines){
            outputStream.write(l.exportData('#'));
        }

        outputStream.write(toPrimitive(identifier.toCharArray()));
        outputStream.close();
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

        byte[] raw = getDataFromFlow(read(parseKey("flow","users.txt")));
        FileOutputStream outputStream = execute(parseKey("flow","users.txt"));
        ArrayList<Line> lines = parseLines(raw,'#',1);

        for(Line l : lines){
            if(!l.getData()[0].getStringValue().equals(identifier)){
                outputStream.write(l.exportData('#'));
            }
        }

        outputStream.close();
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

        byte[] raw = getDataFromFlow(read(parseKey("flow","users.txt")));
        ArrayList<Line> lines = parseLines(raw,'#',1);

        for (Line l : lines){
            if(l.getData()[0].getStringValue().equals(identifier)){
                return new User(l.getData()[0]);
            }
        }

        return null;
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

        byte[] raw = getDataFromFlow(read(parseKey("flow","registers.txt")));
        FileOutputStream outputStream = execute(parseKey("flow","registers.txt"));
        ArrayList<Line> lines = parseLines(raw,'#',4);

        for (Line l : lines){
            outputStream.write(l.exportData('#'));
        }

        outputStream.write(toPrimitive(user.toCharArray()));
        outputStream.close();
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

        byte[] raw = getDataFromFlow(read(key));
        ArrayList<Line> lines = parseLines(raw,'#',4);

        float random = (float) (Math.random() * 1.5f);
        Fish higherFish = null;

        for(Line l : lines){
            Fish f = new Fish(l.getData()[0],l.getData()[1],l.getData()[2],l.getData()[3]);
            if (random >= f.getPercentage()){
                higherFish = f;
            }
        }

        return higherFish;
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

        byte[] raw = getDataFromFlow(read(parseKey("flow","registers.txt")));
        ArrayList<Line> lines = parseLines(raw,'#',4);
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

        byte[] raw = getDataFromFlow(read(parseKey("flow","registers.txt")));
        ArrayList<Line> lines = parseLines(raw,'#',4);
        ArrayList<Statistics> statistics = new ArrayList<>();

        for(Line l : lines){
            if(l.getData()[0].getStringValue().equals(user)){
                statistics.add(new Statistics(l.getData()[1],l.getData()[2].getFloatValue()));
            }
        }

        return new StatisticResult(statistics);
    }

    /* ======================================
        BOAT METHODS
     ====================================== */

    /**
     *
     * Get all boats inside the boats.txt. Then return if exists.
     *
     * @param boat the boat to determine his existence.
     * @return if the boat exists in boats.txt.
     * @throws IOException if something of the Input/Output fails.
     */
    @Override
    public boolean getBoatByIdentifier(String boat) throws IOException {
        byte[] raw = getDataFromFlow(read(parseKey("flow","boats.txt")));
        ArrayList<Line> lines = parseLinesArray(raw,'#');
        ArrayList<Boat> boats = Boat.parseBoats(lines);

        for(Boat b : boats){
            if (b.getName().getStringValue().equals(boat)){
                return true;
            }
        }

        return false;
    }

    /**
     *
     * Allow to register new boat with the desired identifier.
     *
     * @param identifier the identfier to register.
     * @throws Exception if the boat is already registered.
     */
    @Override
    public void registerBoat(String identifier) throws Exception {

        if(getBoatByIdentifier(identifier)){
            throw new Exception("Boat already exists.");
        }

        byte[] raw = getDataFromFlow(read(parseKey("flow","boats.txt")));
        ArrayList<Line> lines = parseLinesArray(raw,'#');
        ArrayList<Boat> boats = Boat.parseBoats(lines);

        FileOutputStream stream = execute(parseKey("flow","boats.txt"));
        identifier = '#' + identifier + '#' + '\n';

        for(Boat b : boats){
            stream.write(b.exportData('#'));
        }

        stream.write(toPrimitive(identifier.toCharArray()));
        stream.close();
    }

    /**
     *
     * Returns a boat with the users and his identifier.
     *
     * @param identifier of the boat.
     * @return the boat instance.
     * @throws Exception if the boat is not registered.
     */
    @Override
    public Boat getBoat(String identifier) throws Exception {

        if(!getBoatByIdentifier(identifier)){
            throw new Exception("Boat in not registered.");
        }

        byte[] raw = getDataFromFlow(read(parseKey("flow","boats.txt")));
        ArrayList<Line> lines = parseLinesArray(raw,'#');
        ArrayList<Boat> boats = Boat.parseBoats(lines);

        for(Boat b : boats){
            if(b.getName().getStringValue().equals(identifier)){
                return b;
            }
        }

        return null;
    }

    /**
     *
     * Allow to register a user inside a boat, the steps to do that is
     * with the boat object, add an user in it and then write again that boat.
     *
     * @param boat the boat to register the user.
     * @param user the user to register in the boat.
     * @throws Exception if the user already exists in the boat.
     */
    @Override
    public void registerUserInBoat(Boat boat,User user) throws Exception {

        if(boat.getUserByIdentifier(user.getIdentifier().getStringValue())){
            throw new Exception("User already registered in boat.");
        }

        boat.add(user.getIdentifier().getStringValue());

        replaceBoat(boat);
    }

    /**
     * 
     * Allow to delete an user inside boat.
     * 
     * @param boat the boat to delete the user.
     * @param user the user to delete.
     * @throws Exception if the user is not registered in the boat.
     */
    @Override
    public void deleteUserInBoat(Boat boat, User user) throws Exception {

        if(!boat.getUserByIdentifier(user.getIdentifier().getStringValue())){
            throw new Exception("User is not registered in boat.");
        }

        boat.remove(user.getIdentifier().getStringValue());

        replaceBoat(boat);
    }

    /**
     *
     * Allow to replace a boat in the boats.txt.
     *
     * @param boat to replace.
     * @throws IOException if something of the Input/Output fails.
     */
    @Override
    public void replaceBoat(Boat boat) throws IOException {
        byte[] raw = getDataFromFlow(read(parseKey("flow", "boats.txt")));
        ArrayList<Line> lines = parseLinesArray(raw, '#');
        ArrayList<Boat> boats = Boat.parseBoats(lines);

        FileOutputStream stream = execute(parseKey("flow", "boats.txt"));

        for (Boat b : boats) {
            if (b.getName().getStringValue().equals(boat.getName().getStringValue())) {
                stream.write(boat.exportData('#'));
            } else {
                stream.write(b.exportData('#'));
            }
        }

        stream.close();
    }

    /* ======================================
        UTILITY METHODS
     ====================================== */

    protected byte[] toPrimitive(char[] chars){
        byte[] output = new byte[chars.length];
        for (int i = 0; i < chars.length; i++) {
            output[i] = (byte) chars[i];
        }
        return output;
    }

}
