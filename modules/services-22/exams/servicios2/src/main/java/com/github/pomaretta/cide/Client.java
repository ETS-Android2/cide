package com.github.pomaretta.cide;

import com.github.pomaretta.cide.console.Console;

/**
 * @author Carlos Pomares <https://www.github.com/pomaretta>
 */
public class Client {

    private final Console console;

    public Client() {
        this.console = new Console();
    }

    public void start(String[] args) {
        this.console.start();
    }

    public static void main(String[] args) throws Exception
    {
        Client client = new Client();
        client.start(args);
    }
}
