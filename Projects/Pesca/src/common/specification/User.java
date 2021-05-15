package common.specification;

import common.data.Data;

public class User {

    private Data identifier;

    public User(Data identifier) {
        this.identifier = identifier;
    }

    public Data getIdentifier() {
        return identifier;
    }

}
