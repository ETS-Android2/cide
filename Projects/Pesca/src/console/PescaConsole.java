package console;

import common.specification.StatisticResult;
import services.PescaAPI;
import termux.Components.Command.CommandParser;
import termux.Components.Menu.DefaultInteractiveMenu;
import termux.Components.Menu.Encapsulate;
import termux.Components.Menu.OptionMenu;
import termux.Components.Menu.SequentialMenu;
import termux.DefaultConsole;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PescaConsole extends DefaultConsole {

    private final PescaAPI api;

    public PescaConsole() throws Exception {
        api = new PescaAPI();
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
    private void registerNewUser(){

        ArrayList<String> result = getUser();

        try {
            api.registerUser(result.get(0));
        } catch (Exception e){
            errorLog.add(e.getMessage());
        }

    }

    /**
     * Obtain the identifier of the user and delete it.
     */
    private void deleteUser(){

        ArrayList<String> result = getUser();

        try {
            api.deleteUser(result.get(0));
        } catch (Exception e){
            errorLog.add(e.getMessage());
        }

    }

    /**
     * Sequential menu to register new fish action.
     */
    private void newAction(){

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

        try {
            api.registerNewAction(
                    result.get(0)
                    ,api.getFish(getClass().getResource(parseBoat(result.get(1))).getFile())
            );
        } catch (Exception e){
            errorLog.add(e.getMessage());
        }

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
    private void userStatistics(){

        ArrayList<String> result = getUser();

        try {
            showStatistics(api.getStatistics(result.get(0)));
        } catch (Exception e){
            errorLog.add(e.getMessage());
        }

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
    private void showStatistics(StatisticResult statistics){

        Encapsulate.encapsulateString("ESTADÍSTICAS GENERALES","\t");

        System.out.printf("\n\tMáximo peso registrado: %.2f",statistics.getMax());
        System.out.printf("\n\tMínimo peso registrado: %.2f",statistics.getMin());
        System.out.printf("\n\tMedia de peso registrado: %.2f",statistics.getAverage());
        System.out.printf("\n\tMediana de peso registrado: %.2f\n",statistics.getMean());

        Encapsulate.encapsulateString("ESTADÍSTICAS ESPECÍFICAS","\t");

        showStatisticsHashMaps(statistics.getFishSizes(),"Máximo peso por pescado capturado","PESCADO: %s -- MÁXIMO PESO: %.2f");
        showStatisticsHashMaps(statistics.getFishCatches(),"Máximas capturas por pescado","PESCADO: %s -- CAPTURAS: %.0f");

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

    @Override
    protected void main() {
        init();
    }

}
