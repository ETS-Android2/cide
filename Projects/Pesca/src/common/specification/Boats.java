package common.specification;

import common.data.Data;

import java.util.ArrayList;

public class Boats {

    private Data name;
    private ArrayList<Data> users;

    public Boats(Data name, ArrayList<Data> users) {
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
