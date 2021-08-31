package common.specification;

import common.data.Data;

public class User {

    /**
     * The identifier of the user.
     */
    private Data identifier;

    /**
     *
     * User containing his identifier.
     *
     * @param identifier the identifier of the user.
     */
    public User(Data identifier) {
        this.identifier = identifier;
    }

    public Data getIdentifier() {
        return identifier;
    }

}
