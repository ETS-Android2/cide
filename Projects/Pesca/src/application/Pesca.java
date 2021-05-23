package application;

import console.PescaConsole;

public class Pesca {

    private String type;
    private PescaConsole pescaConsole;
    private PescaUI pescaUI;

    public Pesca(String type) throws Exception {
        this.type = type;
        if(this.type.equals("cli")){
            pescaConsole = new PescaConsole();
        }
    }

    public void run() throws Exception {
        if(this.type.equals("cli")){
            pescaConsole.start();
        } else {
            pescaUI = PescaUI.init();
        }
    }

    public static void main(String[] args) throws Exception {
        Pesca application = new Pesca("cli");
        application.run();
    }

}
