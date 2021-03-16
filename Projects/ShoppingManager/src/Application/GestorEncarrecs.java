package Application;

/*

    Project     Programming21
    Package     Application.Entities    
    
    Version     1.0      
    Author      Carlos Pomares
    Date        2021-03-12

    DESCRIPTION
    
*/

import Application.Entities.Client;
import Application.Entities.Product;
import Application.Persistent.DatabaseDriver;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.text.DecimalFormat;
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
        // Si MySQL no esta activo, peta, solución, hacer una comprobación de servidor antes de instanciar.
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

        while(out.size() < sequence.length){

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
                "Visualizar clientes",
                "Buscar cliente por nombre",
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
                    case 1 -> registerNewClient();
                    case 2 -> showAllClients((ArrayList<Client>) this.DATA_SOURCE.obtenerTodosLosClientes());
                    case 3 -> showAllClients(searchClientByName());
                    case 4 -> exit = true;
                }

            } catch (Exception e){
                ALERTS.add(e.getMessage());
            }

        }

    }

    // TODO Método buscar cliente, secuencia de preguntas...
    private ArrayList<Client> searchClientByName() throws Exception {
        String[] messages = {
                "Introduce el nombre del cliente: "
        };

        ArrayList<String> result = menuSecuencial(messages);

        if(result.get(0).equalsIgnoreCase("")){
            throw new Exception("No puede estar vacio...");
        }


        return (ArrayList<Client>) DATA_SOURCE.buscarClientePorNombre(result.get(0));
    }

    // TODO Método agregar cliente, secuencia de preguntas...
    private void registerNewClient() throws SQLException {
        String[] messages = {
                "Introduce el primer nombre: "
                ,"Introduce el segundo nombre: "
                ,"Introduce el primer apellido: "
                ,"Introduce el segundo apellido: "
                ,"Introduce el domicilio: "
                ,"Introduce el correo electronico: "
                ,"Introduce el número de teléfono: "
        };

        ArrayList<String> result = menuSecuencial(messages);

        DATA_SOURCE.agregarCliente(new Client(
                DATA_SOURCE.obtenerNuevoIDCliente(),
                result.get(0),
                result.get(1),
                result.get(2),
                result.get(3),
                result.get(4),
                result.get(5),
                result.get(6)
        ));

    }

    // TODO Obtener clientes
    private void showAllClients(ArrayList<Client> clients){
        if(clients.size() != 0){
            System.out.printf("\n\n\t%-5s %-20s %-25s %-25s %-25s %-9s"
                    ,"ID"
                    ,"NOMBRE"
                    ,"APELLIDO"
                    ,"CALLE"
                    ,"MAIL"
                    ,"TELÉFONO"
            );
            for(Client client : clients){
                System.out.printf("\n\t%-5d %-20s %-25s %-25s %-25s %-9s"
                        ,client.getId()
                        ,(client.getFirstName() + " " + ((!client.getSecondName().equalsIgnoreCase("null")) ? client.getSecondName() : ""))
                        ,(client.getFirstLastname() + " " + client.getSecondLastname())
                        ,client.getStreetAddress()
                        ,client.getMailAddress()
                        ,client.getPhoneNumber()
                );
                System.out.print("\n\t---------------------------------------------");
            }
        }
    }

    // TODO Menu Producto
    private void menuProducto(){

        boolean exit = false;

        String[] options = {
                "Agregar producto",
                "Seleccionar producto",
                "Buscar producto por título",
                "Ver todos los productos",
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
                    case 1 -> registerNewProduct();
                    case 2,3 -> {
                        throw new Exception("NOT IMPLEMENTED");
                    }
                    case 4 -> showAllProducts((ArrayList<Product>) DATA_SOURCE.obtenerTodosLosProductos());
                    case 5 -> exit = true;
                }

            } catch (Exception e){
                ALERTS.add(e.getMessage());
            }

        }

    }

    // TODO Agregar producto
    private void registerNewProduct() throws Exception {

        String[] messages = {
                "Introduce el título ",
                "Introduce la descripción ",
                "Introduce el precio: "
        };

        ArrayList<String> result = menuSecuencial(messages);

        DATA_SOURCE.agregarProducto(new Product(
                result.get(0),
                result.get(1),
                Float.parseFloat(result.get(2))
        ));

    }

    // TODO Listar productos
    private void showAllProducts(ArrayList<Product> products){
        if(products.size() != 0){
            System.out.printf("\n\n\t%-5s %-20s %-50s %-8s"
                    ,"ID"
                    ,"TÍTULO"
                    ,"DESCRIPCIÓN"
                    ,"PRECIO"
            );
            for(Product product : products){
                System.out.printf("\n\t%-5d %-20s %-50s %-8.2f"
                        ,product.getId()
                        ,product.getTitle()
                        ,product.getDescription()
                        ,product.getPrice()
                );
                System.out.print("\n\t---------------------------------------------");
            }
        }
    }

    // TODO Menu Encargo
    private void menuEncargo(){}

    // TODO Realizar un encargo

    // TODO Eliminar un encargo

    // TODO Consultar encargos

    // TODO MENUS
    private void showMenuStyled(String title, String[] options,String escape){
        System.out.printf("\n" + escape + "-------------- %s -------------",title);
        showOptionsWithNumbers(options,escape);
        System.out.print(escape + "-----------------------------------");
    }
    private void showMenuStyled(String title, ArrayList<String> options,String escape){
        System.out.printf("\n" + escape + "-------------- %s -------------",title);
        String[] tmp = options.toArray(new String[0]);
        showOptionsWithNumbers(tmp,escape);
        System.out.print(escape + "-----------------------------------");
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
