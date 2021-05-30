package application;

import console.PescaConsole;

public class Pesca {

    /**
     * Run type of the application,
     * cli to run in Console Line Interface mode,
     * other to run in graphical mode.
     */
    private String type;

    /**
     *
     * Pesca Console for CLI mode.
     *
     */
    private PescaConsole pescaConsole;

    /**
     *
     * PescaUI containing the graphical interface.
     *
     */
    private PescaUI pescaUI;

    /**
     *
     * Main class, enter mode to run in CLI or Graphical mode.
     *
     * @param type run type.
     * @throws Exception if some flow produces an error.
     */
    public Pesca(String type) throws Exception {
        this.type = type;
        if(this.type.equals("cli")){
            pescaConsole = new PescaConsole();
        }
    }

    /**
     *
     * Run the application with the run type selected.
     *
     * @throws Exception if some flow produces an error.
     */
    public void run() throws Exception {
        if(this.type.equals("cli")){
            pescaConsole.start();
        } else {
            pescaUI = PescaUI.init();
            pescaUI.getFormManager().changeForm(pescaUI.getForms().get("main"));
        }
    }

    public static void main(String[] args) throws Exception {
        // Graphical
        // Pesca application = new Pesca("graphics");

        // CLI
        Pesca application = new Pesca("cli");

        application.run();
    }

}
