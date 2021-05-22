package console;

import services.PescaAPI;
import termux.Components.Command.CommandParser;
import termux.Components.Menu.DefaultInteractiveMenu;
import termux.Components.Menu.OptionMenu;
import termux.DefaultConsole;

public class PescaConsole extends DefaultConsole {

    private final PescaAPI api;

    public PescaConsole() throws Exception {
        api = new PescaAPI();
    }

    private void init(){

        String[] options = {
                "Alta de usuario"
                ,"Baja de usuario"
                ,"Pescar en una pesquera"
                ,"Estadísticas por usuario"
                ,"Estadísticas globales"
                ,"Salir"
        };

        OptionMenu optionMenu = new OptionMenu(options,"\t","Menu principal","%s",1,true);

        CommandParser parser = new PescaParser() {
            @Override
            protected int callBack(String command) throws Exception {
                switch (Integer.parseInt(command)){
                    case 1:
                        System.out.println("1");
                        break;
                    case 2:
                        System.out.println("2");
                        break;
                    case 3:
                        System.out.println("3");
                        break;
                    case 4:
                        System.out.println("4");
                        break;
                    case 5:
                        System.out.println("5");
                        break;
                    case 6:
                        System.out.println("6");
                        break;
                }
                return 0;
            }
        };

        DefaultInteractiveMenu menu = new DefaultInteractiveMenu(
                this.errorLog
                ,optionMenu
                ,parser
                ,this.reader
                ,"\t> "
        ) {
            @Override
            protected void outsideLoop() {
            }

            @Override
            protected void loopBlock() {
                if(errorLog.size() > 0)
                    errorLog.show("\t","ALERTS");

                optionMenu.show();
            }
        };

        menu.show();

    }

    @Override
    protected void main() {
        init();
    }

}
