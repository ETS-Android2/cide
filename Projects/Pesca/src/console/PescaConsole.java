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
import java.util.Map;

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

    private void registerNewUser(){

        String[] messages = {
                "Introduce el identificador del usuario"
        };

        SequentialMenu menu = new SequentialMenu(
                messages
                ,reader
                ,"\t"
                ,errorLog
        );

        menu.show();

        ArrayList<String> result = menu.getOutput();

        try {
            api.registerUser(result.get(0));
        } catch (Exception e){
            errorLog.add(e.getMessage());
        }

    }

    private void deleteUser(){

        String[] messages = {
                "Introduce el identificador del usuario"
        };

        SequentialMenu menu = new SequentialMenu(
                messages
                ,reader
                ,"\t"
                ,errorLog
        );

        menu.show();

        ArrayList<String> result = menu.getOutput();

        try {
            api.deleteUser(result.get(0));
        } catch (Exception e){
            errorLog.add(e.getMessage());
        }

    }

    private void newAction(){

        String[] messages = {
                "Introduce el identificador del usuario",
                "Donde quieres pescar (mediterrania | florida)"
        };

        String[] validation = {
                "^[a-zA-Z0-9]+$"
                ,"^(florida|mediterrania)$"
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
                    ,api.getFish(
                            result.get(1).equals("mediterrania")
                            ? getClass().getResource("/data/mediterrania.txt").getFile()
                            : getClass().getResource("/data/florida.txt").getFile()
                    )
            );
        } catch (Exception e){
            errorLog.add(e.getMessage());
        }

    }

    private void userStatistics(){

        String[] messages = {
                "Introduce el identificador del usuario"
        };

        SequentialMenu menu = new SequentialMenu(
                messages
                ,reader
                ,"\t"
                ,errorLog
        );

        menu.show();

        ArrayList<String> result = menu.getOutput();

        try {
            showStatistics(api.getStatistics(result.get(0)));
        } catch (Exception e){
            errorLog.add(e.getMessage());
        }

    }

    private void showStatistics(StatisticResult statistics){

        Encapsulate.encapsulateString("STADÍSTICAS GENERALES","\t");

        System.out.printf("\n\tMáximo peso registrado: %.2f",statistics.getMax());
        System.out.printf("\n\tMínimo peso registrado: %.2f",statistics.getMin());
        System.out.printf("\n\tMedia de peso registrado: %.2f",statistics.getAverage());
        System.out.printf("\n\tMediana de peso registrado: %.2f",statistics.getMean());

        Encapsulate.encapsulateString("STADÍSTICAS ESPECÍFICAS","\t");

        System.out.print("\n\tMáximo peso por pescado capturado\n");

        for(Map.Entry<String,Float> entry : statistics.getFishSizes().entrySet()){
            System.out.printf("\n\tPESCADO: %s -- MÁXIMO PESO: %.2f",entry.getKey(),entry.getValue());
        }

    }

    @Override
    protected void main() {
        init();
    }

}
