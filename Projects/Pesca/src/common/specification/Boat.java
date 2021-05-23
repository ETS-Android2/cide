package common.specification;

import common.data.Data;
import common.data.Line;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Boat {

    private Data name;
    private ArrayList<Data> users;

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

    public boolean getUserByIdentifier(String identifier){
        for(Data d : this.users){
            if(d.getStringValue().equals(identifier)){
                return true;
            }
        }
        return false;
    }

    public void add(String user) throws Exception {
        if(getUserByIdentifier(user)){
            throw new Exception("User already registered in boat");
        }
        this.users.add(new Data(toComplex(user.getBytes())));
    }

    public void remove(String user) throws Exception {
        if(!getUserByIdentifier(user)){
            throw new Exception("User does not exist in boat.");
        }
        this.users.removeIf(d -> d.getStringValue().equals(user));
    }

    public byte[] exportData(char delimiter){

        ArrayList<Byte> data = new ArrayList<>();
        data.add((byte)delimiter);
        Collections.addAll(data,this.name.getRaw());
        data.add((byte)delimiter);

        if (this.users != null){
            for(Data user : this.users){
                Collections.addAll(data,user.getRaw());
                data.add((byte)delimiter);
            }
        }

        data.add((byte)'\n');
        Byte[] flow = new Byte[data.size()];
        return toPrimitive(data.toArray(flow));
    }

    public byte[] toPrimitive(Byte[] bytes){
        byte[] output = new byte[bytes.length];
        for (int i = 0; i < bytes.length; i++) {
            output[i] = bytes[i];
        }
        return output;
    }

    public static ArrayList<Data> toComplex(Data[] bytes){
        return new ArrayList<>(Arrays.asList(bytes));
    }

    public static ArrayList<Boat> parseBoats(ArrayList<Line> lines) {

        ArrayList<Boat> boats = new ArrayList<>();

        for(Line l : lines){
            ArrayList<Data> users = Boat.toComplex(l.getData());
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

    public Byte[] toComplex(byte[] bytes){
        Byte[] output = new Byte[bytes.length];
        for (int i = 0; i < bytes.length; i++) {
            output[i] = bytes[i];
        }
        return output;
    }

}
