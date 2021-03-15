package Application;

/*

    Project     Programming21
    Package     Application.Entities    
    
    Version     1.0      
    Author      Carlos Pomares
    Date        2021-03-12

    DESCRIPTION
    
*/

import Application.Persistent.DatabaseDriver;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

/**
 * @author Carlos Pomares
 */

public class GestorEncarrecs {

    final private List<String> ERRORS;
    final private int MAX_ERRORS = 5;
    final private DatabaseDriver DATA_SOURCE;
    final private BufferedReader USER_INPUT;

    private boolean debug = false;
    private String[] debugOptions = new String[]{"Debug 1", "Debug 2"};

    public GestorEncarrecs() throws Exception {
        DATA_SOURCE = new DatabaseDriver();
        USER_INPUT = new BufferedReader(new InputStreamReader(System.in));
        ERRORS = new ArrayList<>();
    }

    public void start(){
        menuPrincipal();
    }

    // TODO Menu principal
    private void menuPrincipal(){

        boolean exit = false;

        String[] options = {
                "Cliente",
                "Producto",
                "Salir"
        };

        while(!exit){

            // ALERTS
            if(ERRORS.size() > 0){
                showMenuStyled("ALERTS", (ArrayList<String>) ERRORS,"\n\t");
                if(ERRORS.size() >= 5)
                    ERRORS.clear();
            }

            // DEBUG OPTIONS
            if(debug){
                showMenuStyled("DEBUG",debugOptions,"\n\t");
            }

            // OPTIONS
            showMenuStyled("Options",options,"\n\t");

            // ORDER
            System.out.print("\n\n\t> ");

            try {

                boolean command = false;
                String order = USER_INPUT.readLine();

                switch (parseCommand(order.toLowerCase())){
                    case 0 -> System.out.println("UNKNOW COMMAND");
                    case 1,2,3,4,5,6,7 -> System.out.println("Option 1");
                    case 8 -> exit = true;
                }

            } catch (Exception e){
                ERRORS.add(e.getMessage());
            }

        }

    }

    // TODO Método genérico de entrada de pregunta

    // TODO Método genérico de muestra de pregunta

    // TODO Método buscar cliente, secuencia de preguntas...

    // TODO Método agregar cliente, secuencia de preguntas...

    // TODO MENUS
    private void showMenuStyled(String title, String[] options,String escape){
        System.out.printf("\n-------------- %s -------------",title);
        showOptionsWithNumbers(options,escape);
        System.out.print("\n-----------------------------------");
    }
    private void showMenuStyled(String title, ArrayList<String> options,String escape){
        System.out.printf("\n-------------- %s -------------",title);
        String[] tmp = options.toArray(new String[0]);
        showOptionsWithNumbers(tmp,escape);
        System.out.print("\n-----------------------------------");
    }
    private void showOptions(String[] options,String escape){
        for(String option : options){
            System.out.printf("%s %s",escape,option);
        }
    }
    private void showOptionsWithNumbers(String[] options,String escape){
        for (int i = 0; i < options.length; i++) {
            System.out.printf("%s %-5d %-29s",escape,(i + 1),options[i]);
        }
    }

    // TODO Comandos
    private int parseCommand(String command) {
        switch (command){
            case "debug" -> debug = true;
            case "author" -> System.out.println("NOT IMPLEMENTED");
            case "test" -> System.out.println("NOT IMPLEMENTED");
            case "configure" -> System.out.println("NOT IMPLEMENTED");
            case "help" -> System.out.println("NOT IMPLEMENTED");
            default -> {
                try {
                    return Integer.parseInt(command);
                } catch (Exception exception){
                    //
                }
            }
        }
        return 0;
    }

    public static void main(String[] args) throws Exception {
        GestorEncarrecs ge = new GestorEncarrecs();
        ge.start();
    }

}
