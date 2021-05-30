package console;

import common.specification.StatisticResult;
import common.specification.StatisticResultSimple;
import services.PescaAPI;
import services.PescaAPISimple;
import termux.Components.Command.CommandParser;
import termux.Components.Menu.DefaultInteractiveMenu;
import termux.Components.Menu.Encapsulate;
import termux.Components.Menu.OptionMenu;
import termux.Components.Menu.SequentialMenu;
import termux.DefaultConsole;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PescaConsole extends DefaultConsole {

    private final PescaAPISimple api;

    public PescaConsole() throws Exception {
        api = new PescaAPISimple();
        PescaParser.setConsole(this);
    }

    /**
     * Main menu, shows the main actions that the user can do.
     */
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
                        registerNewUser();
                        break;
                    case 2:
                        deleteUser();
                        break;
                    case 3:
                        newAction();
                        break;
                    case 4:
                        userStatistics();
                        break;
                    case 5:
                        showStatistics(api.getStatistics());
                        break;
                    case 6:
                        return -1;
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
                System.out.printf("\n\t%s\n"
                        ,"Utiliza el comando 'help', para mostrar la ayuda de comandos.");
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

    /**
     * Obtain the identifier of the user and register it.
     */
    private void registerNewUser() throws Exception {
        ArrayList<String> result = getUser();
        api.registerUser(result.get(0));
    }

    /**
     * Obtain the identifier of the user and delete it.
     */
    private void deleteUser() throws Exception {
        ArrayList<String> result = getUser();
        api.deleteUser(result.get(0));
    }

    /**
     * Sequential menu to register new fish action.
     */
    private void newAction() throws Exception {

        String[] messages = {
                "Introduce el identificador del usuario",
                "Donde quieres pescar (mediterrania | florida | indico | pacifico)"
        };

        String[] validation = {
                "^[a-zA-Z0-9]+$"
                ,"^(florida|mediterrania|indico|pacifico)$"
        };

        SequentialMenu menu = new SequentialMenu(
                messages
                ,validation
                ,reader
                ,"\t"
                ,errorLog
        );

        menu.show();

        ArrayList<String> result = menu.getOutput();


        api.registerNewAction(
                result.get(0)
                ,api.getFish(getClass().getResource(parseBoat(result.get(1))).getFile())
        );
    }

    /**
     * @param input name of the boat to go fish.
     * @return the selected path.
     */
    private String parseBoat(String input){
        switch (input) {
            case "florida":
                return "/data/florida.txt";
            case "pacifico":
                return "/data/pacifico.txt";
            case "indico":
                return "/data/indico.txt";
            default:
                return "/data/mediterrania.txt";
        }
    }

    /**
     * Sequential menu to obtain the statistics of the user.
     */
    private void userStatistics() throws Exception {
        ArrayList<String> result = getUser();
        showStatistics(api.getStatistics(result.get(0)));
    }

    /**
     *
     * Sequential menu to obtain the identifier of the user.
     *
     * @return the result of the menu.
     */
    private ArrayList<String> getUser() {
        String[] messages = {
                "Introduce el identificador del usuario"
        };

        SequentialMenu menu = new SequentialMenu(
                messages
                , reader
                , "\t"
                , errorLog
        );

        menu.show();

        return menu.getOutput();
    }

    /**
     *
     * Show the statistics of the StatisticsResult.
     *
     * @param statistics the statistics to show.
     */
    private void showStatistics(StatisticResult statistics) throws IOException {

        StatisticResultSimple s = (StatisticResultSimple) statistics;

        Encapsulate.encapsulateString("ESTADÍSTICAS GENERALES","\t");

        System.out.printf("\n\tMáximo peso registrado: %.2f",statistics.getMax());
        System.out.printf("\n\tMínimo peso registrado: %.2f",statistics.getMin());
        System.out.printf("\n\tMedia de peso registrado: %.2f",statistics.getAverage());
        System.out.printf("\n\tMediana de peso registrado: %.2f\n",statistics.getMean());

        Encapsulate.encapsulateString("ESTADÍSTICAS ESPECÍFICAS","\t");

        showStatisticsFiles(s,"tmp","sizes.txt","Máximo peso por pescado capturado","\n\tPESCADO: %s -- MÁXIMO PESO: %.2f");
        showStatisticsFiles(s,"tmp","catches.txt","Máximas capturas por pescado","\n\tPESCADO: %s -- CAPTURAS: %.0f");

    }

    /**
     *
     * Show a hashmap in the CLI application.
     *
     * @param hashMap to show.
     * @param header to show with the hashmap, usually specifies what data is being viewed.
     * @param format the format of the data to show.
     */
    private void showStatisticsHashMaps(HashMap<String,Float> hashMap,String header, String format){
        System.out.printf("\n\t%s:",header);
        for(Map.Entry<String,Float> entry : hashMap.entrySet()){
            System.out.printf("\n\t" + format,entry.getKey(),entry.getValue());
        }
        System.out.print("\n");
    }

    private void showStatisticsFiles(StatisticResultSimple statistics,String bucket,String key,String header,String format) throws IOException {
        System.out.printf("\n\t%s:",header);
        statistics.showStatisticsConsole(
                bucket
                ,key
                ,'#'
                ,2
                ,0
                ,1
                ,format
        );
        System.out.print("\n");
    }

    /**
     *
     * Comando help, muestra los diferentes comandos.
     *
     */
    public void help(){

        String[] comandos = {
                "RESET -- Reinicia las alertas y el menu de debug a valores por defecto."
                ,"AUTHOR -- Te muestra un mensaje hecho por el autor del programa."
                ,"HELP -- Te muestra una ayuda sobre comandos."
        };

        OptionMenu optionMenu = new OptionMenu(comandos,"\t","COMANDOS","%s");

        optionMenu.show();

    }

    /**
     *
     * Comando author, muestra un mensaje del autor del programa.
     *
     */
    public void author(){
        System.out.printf("\n\t========= %s =========\n","Créditos del autor");
        System.out.print("\t   ______________________________\n" +
                "\t / \\                             \\.\n" +
                "\t|   |                            |.\n" +
                "\t \\_ |  Gracias por probar        |.\n" +
                "\t    |  esta aplicación,          |.\n" +
                "\t    |  espero que te haya        |.\n" +
                "\t    |  gustado y te haya         |.\n" +
                "\t    |  servido de inspiración,   |.\n" +
                "\t    |  lo he hecho con cariño    |.\n" +
                "\t    |  y he intentado aplicar    |.\n" +
                "\t    |  nuevos conocimientos      |.\n" +
                "\t    |                            |.\n" +
                "\t    |  Gracias, Carlos Pomares   |.\n" +
                "\t    |                            |.\n" +
                "\t    |  github.com/pomaretta      |.\n" +
                "\t    |                            |.\n" +
                "\t    |  https://carlospomares.es  |.\n" +
                "\t    |                            |.\n" +
                "\t    |   _________________________|___\n" +
                "\t    |  /                            /.\n" +
                "\t    \\_/____________________________/.\n\n");
    }

    /**
     *
     * Comando reset, permite vaciar los errores.
     *
     */
    public void reset(){
        errorLog.clear();
    }

    @Override
    protected void main() {
        init();
    }

}
