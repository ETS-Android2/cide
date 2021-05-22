import console.PescaConsole;
import services.PescaAPI;

public class Pesca {

    private final PescaConsole pescaConsole;

    public Pesca() throws Exception {
        pescaConsole = new PescaConsole();
    }

    public void run(){
        pescaConsole.start();
    }

    public static void main(String[] args) throws Exception {
        Pesca application = new Pesca();
        application.run();
    }

}
