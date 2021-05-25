package services;

import common.data.Line;

import java.io.IOException;
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
        return false;
    }

}
