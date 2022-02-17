package com.github.pomaretta.cide.console;

import com.github.pomaretta.termux.Command.DefaultCommandParser;

public abstract class Parser extends DefaultCommandParser {

        @Override
        protected int parseBlock(String command) throws Exception {
            // Esto sirve para meter comandos custom aparte de números
            switch (command) {
                case "clear":
                    System.out.print("\033[H\033[2J");
                    break;
                default:
                    // Si no es ninguno de los anteriores lo paso con número
                    return this.callBack(command);
            }   
            return 0;
        }

}
