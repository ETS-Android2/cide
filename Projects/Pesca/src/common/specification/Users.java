package common.specification;

import common.data.Data;

public class Users {

    private Data identifier;

    public Users(Data identifier) {
        this.identifier = identifier;
    }

    public Data getIdentifier() {
        return identifier;
    }

}
