package console;

import termux.Components.Command.CommandParser;
import termux.DefaultConsole;

public abstract class PescaParser extends CommandParser {

    private static PescaConsole console;

    @Override
    protected int parseBlock(String command) throws Exception {
        switch (command.toLowerCase()){
            case "debug": case "test":
                throw new Exception("NOT IMPLEMENTED");
            default:
                return callBack(command.toLowerCase());
        }
    }

    protected abstract int callBack(String command) throws Exception;

    public static void setConsole(DefaultConsole c){
        console = (PescaConsole) c;
    }

}
