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
import java.util.List;

/**
 * @author Carlos Pomares
 */

public class GestorEncarrecs {

    final private List<String> ALERTS;
    final private int MAX_ERRORS = 5;
    final private DatabaseDriver DATA_SOURCE;
    final private BufferedReader USER_INPUT;

    private boolean debug = false;
    private String[] debugOptions = new String[]{"Debug 1", "Debug 2"};

    public GestorEncarrecs() throws Exception {
        DATA_SOURCE = new DatabaseDriver();
        USER_INPUT = new BufferedReader(new InputStreamReader(System.in));
        ALERTS = new ArrayList<>();
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
            if(ALERTS.size() > 0){
                showMenuStyled("ALERTS", (ArrayList<String>) ALERTS,"\n\t");
                if(ALERTS.size() >= 5)
                    ALERTS.clear();
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
                    case 1 -> menuCliente();
                    case 2 -> menuProducto();
                    case 3 -> exit = true;
                }

            } catch (Exception e){
                ALERTS.add(e.getMessage());
            }

        }

    }

    private ArrayList<String> menuSecuencial(String[] sequence){

        int step = 0;
        ArrayList<String> out = new ArrayList<>();

        while(out.size() <= sequence.length){

            // ALERTS
            if(ALERTS.size() > 0){
                showMenuStyled("ALERTS", (ArrayList<String>) ALERTS,"\n\t");
                if(ALERTS.size() >= 5)
                    ALERTS.clear();
            }

            try {
                out.add(ask(sequence[step],"\t"));
            } catch (Exception e){
                ALERTS.add(e.getMessage());
            }

            step++;

        }

        return out;
    }

    // TODO Menu Cliente
    private void menuCliente(){

        boolean exit = false;

        String[] options = {
                "Agregar cliente",
                "Seleccionar cliente",
                "Volver al menu principal"
        };

        while(!exit){

            showMenus("Cliente",options);

            // ORDER
            System.out.print("\n\n\t> ");

            try {

                boolean command = false;
                String order = USER_INPUT.readLine();

                switch (parseCommand(order.toLowerCase())){
                    case 1,2 -> System.out.println("NOT IMPLEMENTED");
                    case 3 -> exit = true;
                }

            } catch (Exception e){
                ALERTS.add(e.getMessage());
            }

        }

    }

    // TODO Método buscar cliente, secuencia de preguntas...

    // TODO Método agregar cliente, secuencia de preguntas...

    // TODO Menu Producto
    private void menuProducto(){

        boolean exit = false;

        String[] options = {
                "Agregar producto",
                "Seleccionar producto",
                "Volver al menu principal"
        };

        while(!exit){

            showMenus("Producto",options);

            // ORDER
            System.out.print("\n\n\t> ");

            try {

                boolean command = false;
                String order = USER_INPUT.readLine();

                switch (parseCommand(order.toLowerCase())){
                    case 1,2 -> System.out.println("Option 1");
                    case 3 -> exit = true;
                }

            } catch (Exception e){
                ALERTS.add(e.getMessage());
            }

        }

    }

    // TODO Agregar producto

    // TODO Listar productos

    // TODO Menu Encargo
    private void menuEncargo(){}

    // TODO Realizar un encargo

    // TODO Eliminar un encargo

    // TODO Consultar encargos

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
    private void showMenus(String title, String[] options){

        // ALERTS
        if(ALERTS.size() > 0){
            showMenuStyled("ALERTS", (ArrayList<String>) ALERTS,"\n\t");
            if(ALERTS.size() >= 5)
                ALERTS.clear();
        }

        // DEBUG OPTIONS
        if(debug){
            showMenuStyled("DEBUG",debugOptions,"\n\t");
        }

        // OPTIONS
        showMenuStyled(title,options,"\n\t");

    }
    private String ask(String message, String escape) throws Exception {
        System.out.println(escape + message);
        System.out.print(escape + "--> ");
        return USER_INPUT.readLine();
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
