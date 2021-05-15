package common.specification;

import common.data.Data;

import java.util.ArrayList;

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

}
