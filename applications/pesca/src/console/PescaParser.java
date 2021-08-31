package console;

import termux.Components.Command.CommandParser;
import termux.DefaultConsole;

public abstract class PescaParser extends CommandParser {

    private static PescaConsole console;

    @Override
    protected int parseBlock(String command) throws Exception {
        switch (command.toLowerCase()){
            case "help":
                console.help();
                break;
            case "reset":
                console.reset();
                break;
            case "author":
                console.author();
                break;
            default:
                return callBack(command.toLowerCase());
        }
        return 0;
    }

    protected abstract int callBack(String command) throws Exception;

    public static void setConsole(DefaultConsole c){
        console = (PescaConsole) c;
    }

}
