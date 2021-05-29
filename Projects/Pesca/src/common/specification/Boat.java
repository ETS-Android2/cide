package common.specification;

import common.data.Data;
import common.data.Line;
import transformation.Transform;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Boat {

    /**
     * The identifier of the boat.
     */
    private Data name;

    /**
     * The users of the boat.
     */
    private ArrayList<Data> users;

    /**
     *
     * The boat is the container of the users that go to fish.
     *
     * @param name the identifier of the boat.
     * @param users the users that contains the boat.
     */
    public Boat(Data name, ArrayList<Data> users) {
        this.name = name;
        this.users = users;
    }

    public Data getName() {
        return name;
    }

    public ArrayList<Data> getUsers() {
        return users;
    }

    /**
     *
     * Return if the input identifier is in the different users in the boat.
     *
     * @param identifier the identifier of the user.
     * @return if the user is in the boat.
     */
    public boolean getUserByIdentifier(String identifier){
        for(Data d : this.users){
            if(d.getStringValue().equals(identifier)){
                return true;
            }
        }
        return false;
    }

    /**
     *
     * Allow to add a user in the boat.
     *
     * @param user the user to add in the boat.
     * @throws Exception if the user is already registered in the boat.
     */
    public void add(String user) throws Exception {
        if(getUserByIdentifier(user)){
            throw new Exception("User already registered in boat");
        }
        this.users.add(new Data(Transform.toComplex(user.getBytes())));
    }

    /**
     *
     * Allow to remove some user of the boat.
     *
     * @param user the user to remove in the boat.
     * @throws Exception if the user is not registered in the boat.
     */
    public void remove(String user) throws Exception {
        if(!getUserByIdentifier(user)){
            throw new Exception("User does not exist in boat.");
        }
        this.users.removeIf(d -> d.getStringValue().equals(user));
    }

    /**
     *
     * Allow to convert the complex data of the boat
     * to an array of bytes that can be written inside a file.
     *
     * @param delimiter to separate the data.
     * @return array of bytes to write in a file.
     */
    public byte[] exportData(char delimiter){

        ArrayList<Byte> data = new ArrayList<>();

        // The identifier of the boat #example#
        data.add((byte)delimiter);
        Collections.addAll(data,this.name.getRaw());
        data.add((byte)delimiter);

        if (this.users != null){
            for(Data user : this.users){
                Collections.addAll(data,user.getRaw());
                data.add((byte)delimiter);
            }
        }

        // Last char, the new line.
        data.add((byte)'\n');

        Byte[] flow = new Byte[data.size()];
        return Transform.toPrimitive(data.toArray(flow));
    }

    /**
     *
     * With a list of lines, can be used to detect the identifier and the
     * grow number of users.
     *
     * @param lines the lines to convert to boat class.
     * @return an arraylist of Boats.
     */
    public static ArrayList<Boat> parseBoats(ArrayList<Line> lines) {

        ArrayList<Boat> boats = new ArrayList<>();

        for(Line l : lines){

            ArrayList<Data> users = Transform.toComplex(l.getData());
            // Remove boat name
            users.remove(0);

            boats.add(new Boat(
                    l.getData()[0],
                    users.size() > 1
                    ? users
                    : null
            ));
        }

        return boats;
    }

}
