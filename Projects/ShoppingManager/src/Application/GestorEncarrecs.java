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
import Application.Entities.Order;
import Application.Entities.Product;
import Application.Persistent.DatabaseDriver;
import Application.Services.Encapsulate;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Carlos Pomares
 */

public class GestorEncarrecs {

    final private List<String> ALERTS;
    final private int MAX_ERRORS = 5;
    private DatabaseDriver DATA_SOURCE;
    final private BufferedReader USER_INPUT;

    private boolean debug = false;
    private String[] debugOptions = new String[]{"Debug 1", "Debug 2"};

    public GestorEncarrecs() {
        USER_INPUT = new BufferedReader(new InputStreamReader(System.in));
        ALERTS = new ArrayList<>();
        DATA_SOURCE = new DatabaseDriver();
    }

    public void start() throws Exception {
        System.out.printf("\t%s\n","Utiliza el comando 'help', para mostrar la ayuda de comandos.");
        try {
            DATA_SOURCE.stablishConnection();
        } catch (SQLException e){
            System.out.printf("\n\t%s\n","Establece la conexión con la base de datos");
            configureNewConnection();
        }
        menuPrincipal();
        DATA_SOURCE.close();
    }

    private void menuPrincipal(){

        boolean exit = false;

        String[] options = {
                "Cliente",
                "Producto",
                "Encargos",
                "Salir"
        };

        while(!exit){

            showInternalMenus();

            showMenuStyled("Options",options,"\n\t");

            System.out.print("\n\n\t> ");

            try {

                boolean command = false;
                String order = USER_INPUT.readLine();

                switch (parseCommand(order.toLowerCase())) {
                    case 1:
                        menuCliente();
                        break;
                    case 2:
                        menuProducto();
                        break;
                    case 3:
                        menuEncargo();
                        break;
                    case 4:
                        exit = true;
                        break;
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

            try {
                out.add(ask(sequence[step],"\t"));
            } catch (Exception e){
                ALERTS.add(e.getMessage());
            }

            step++;

        }

        return out;
    }

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
                    case 1:
                        registerNewClient();
                        break;
                    case 2:
                        showAllClients((ArrayList<Client>) this.DATA_SOURCE.obtenerTodosLosClientes());
                        break;
                    case 3:
                        showAllClients(searchClientByName());
                        break;
                    case 4:
                        exit = true;
                        break;
                }


            } catch (Exception e){
                ALERTS.add(e.getMessage());
            }

        }

    }

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

    private Client selectClient() throws Exception {

        int selected = 0;

        ArrayList<Client> clients = (ArrayList<Client>) DATA_SOURCE.obtenerTodosLosClientes();

        if(clients.size() == 0)
            throw new Exception("EMPTY CLIENTS");

        boolean exit = false;

        while(!exit){

            try {

                System.out.printf("\n\n\t%-5s %-35s %-30s"
                        ,"ID"
                        ,"NOMBRE"
                        ,"MAIL"
                );

                for (int i = 0; i < clients.size(); i++) {
                    Client c = clients.get(i);

                    String name = c.getFirstName()
                            + ((!c.getSecondName().equalsIgnoreCase("null")) ? " " + c.getSecondName() : " ")
                            + " " + c.getFirstLastname()
                            + " " + c.getSecondLastname();

                    String format = String.format("%-5d %-35s %-30s"
                            ,c.getId()
                            ,name
                            ,c.getMailAddress()
                    );

                    if(i == selected){
                        Encapsulate.encapsulateString(format,"\t","=");
                    } else {
                        System.out.print("\n\t" + format);
                    }

                }

                System.out.printf("\n\n\t%-15s %-15s %-15s",
                        "1. SIGUIENTE",
                        "2. SELECCIONAR",
                        "3. SALIR"
                );

                System.out.print("\n\n\t> ");

                switch (parseCommand(USER_INPUT.readLine())){
                    case 1:
                        if(selected == (clients.size() - 1)){
                            selected = 0;
                        } else {
                            selected++;
                        }
                        break;
                    case 2:
                        return clients.get(selected);
                    case 3:
                        exit = true;
                        break;
                }

            } catch (Exception e){
                ALERTS.add(e.getMessage());
            }

        }

        return null;
    }

    private void menuProducto(){

        boolean exit = false;

        String[] options = {
                "Agregar producto",
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
                    case 1:
                        registerNewProduct();
                        break;
                    case 2:
                        showAllProducts(searchProductByTitle());
                        break;
                    case 3:
                        showAllProducts((ArrayList<Product>) DATA_SOURCE.obtenerTodosLosProductos());
                        break;
                    case 4:
                        exit = true;
                        break;
                }

            } catch (Exception e){
                ALERTS.add(e.getMessage());
            }

        }

    }

    private void registerNewProduct() throws Exception {

        String[] messages = {
                "Introduce el título ",
                "Introduce la descripción ",
                "Introduce el precio ",
                "Introduce el stock "
        };

        ArrayList<String> result = menuSecuencial(messages);

        DATA_SOURCE.agregarProducto(new Product(
                result.get(0),
                result.get(1),
                Float.parseFloat(result.get(2)),
                Integer.parseInt(result.get(3))
        ));

    }

    private ArrayList<Product> searchProductByTitle() throws Exception {
        String[] messages = {
                "Introduce el titulo del producto: "
        };

        ArrayList<String> result = menuSecuencial(messages);

        if(result.get(0).equalsIgnoreCase("")){
            throw new Exception("No puede estar vacio...");
        }

        return (ArrayList<Product>) DATA_SOURCE.buscarProductoPorTitulo(result.get(0));
    }

    private void showAllProducts(ArrayList<Product> products){
        if(products.size() != 0){
            System.out.printf("\n\n\t%-5s %-20s %-50s %-8s %-8s"
                    ,"ID"
                    ,"TÍTULO"
                    ,"DESCRIPCIÓN"
                    ,"PRECIO"
                    ,"STOCK"
            );
            for(Product product : products){
                System.out.printf("\n\t%-5d %-20s %-50s %-8.2f %-8d"
                        ,product.getId()
                        ,product.getTitle()
                        ,product.getDescription()
                        ,product.getPrice()
                        ,product.getStock()
                );
                System.out.print("\n\t---------------------------------------------");
            }
        }
    }

    private Product selectProduct() throws Exception {

        int selected = 0;

        ArrayList<Product> products = (ArrayList<Product>) DATA_SOURCE.obtenerTodosLosProductos();

        if(products.size() == 0)
            throw new Exception("EMPTY PRODUCTS");

        boolean exit = false;

        while(!exit){

            try {

                System.out.printf("\n\n\t%-5s %-30s %-10s %-8s"
                        ,"ID"
                        ,"TITULO"
                        ,"PRECIO"
                        ,"STOCK"
                );

                for (int i = 0; i < products.size(); i++) {
                    Product p = products.get(i);

                    String format = String.format("%-5d %-30s %-10.2f %-8d"
                            ,p.getId()
                            ,p.getTitle()
                            ,p.getPrice()
                            ,p.getStock()
                    );

                    if(i == selected){
                        Encapsulate.encapsulateString(format,"\t","=");
                    } else {
                        System.out.print("\n\t" + format);
                    }

                }

                System.out.printf("\n\n\t%-15s %-15s %-15s",
                        "1. SIGUIENTE",
                        "2. SELECCIONAR",
                        "3. SALIR"
                );

                System.out.print("\n\n\t> ");

                switch (parseCommand(USER_INPUT.readLine())){
                    case 1:
                        if(selected == (products.size() - 1)){
                            selected = 0;
                        } else {
                            selected++;
                        }
                        break;
                    case 2:
                        return products.get(selected);
                    case 3:
                        exit = true;
                        break;
                }

            } catch (Exception e){
                ALERTS.add(e.getMessage());
            }

        }

        return null;
    }

    private HashMap<Product,Integer> productCart() throws Exception {

        boolean exit = false;

        HashMap<Product,Integer> products = new HashMap<>();
        ArrayList<Product> database = (ArrayList<Product>) DATA_SOURCE.obtenerTodosLosProductos();

        while(!exit){

            showInternalMenus();

            System.out.println("\n\t---------------- CARRITO ----------");

            try {

                if(products.size() > 0){

                    System.out.printf("\n\t%-5s %-30s %-10s %-10s"
                            ,"ID"
                            ,"TITULO"
                            ,"PRECIO"
                            ,"CANTIDAD"
                    );

                    // TODO Visual separation between each product
                    for(Map.Entry<Product,Integer> p : products.entrySet()){
                        Product product = p.getKey();
                        int quantity = p.getValue();
                        System.out.printf("\n\t%-5d %-30s %-10.2f %-10d",
                                product.getId()
                                ,product.getTitle()
                                ,product.getPrice()
                                ,quantity
                        );
                        System.out.print("\n\t-------------------------------");
                    }
                }

                System.out.printf("\n\n\t%-20s %-20s %-20s %-20s %-20s\n"
                        ,"1. AGREGAR PRODUCTO"
                        ,"2. BORRAR PRODUCTO"
                        ,"3. SIGUIENTE"
                        ,"4. GUARDAR Y SALIR"
                        ,"5. SALIR SIN GUARDAR"
                );

                System.out.print("\n\t> ");

                switch (parseCommand(USER_INPUT.readLine())){
                    case 1:
                        Product product = selectProduct();
                        boolean changed = false;
                        for(Product p : products.keySet()){
                            if(p.equals(product)){
                                products.replace(p,products.get(p),(products.get(p) + 1));
                                changed = true;
                                break;
                            }
                        }
                        if(!changed){
                            products.put(product,1);
                        }
                        break;
                    case 2: case 3:
                        throw new Exception("NOT IMPLEMENTED");
                    case 4:
                        if(products.size() > 0 && validateProducts(products)) {
                            updateProducts(products);
                            return products;
                        } else {
                            throw new Exception("CART CANNOT BE EMPTY.");
                        }
                    case 5:
                        exit = true;
                        break;
                }

            } catch (Exception e){
                ALERTS.add(e.getMessage());
            }

        }

        return null;
    }

    private boolean validateProduct(Product p,int quantity) throws Exception {
        return DATA_SOURCE.obtenerProductoPorId(p.getId()).getStock() >= quantity;
    }

    private boolean validateProducts(HashMap<Product,Integer> p) throws Exception {
        int count = 0;
        for(Map.Entry<Product,Integer> product : p.entrySet()){
            if(validateProduct(product.getKey(),product.getValue())) {
                count++;
            } else {
                throw new Exception("PRODUCT: "
                        + product.getKey().getId()
                        + " " + product.getKey().getTitle()
                        + " NO ES VÁLIDO..."
                );
            }
        }
        return count == p.size();
    }

    private void updateProducts(HashMap<Product,Integer> p) throws Exception {
        for(Map.Entry<Product,Integer> product : p.entrySet()){
            DATA_SOURCE.updateProduct(product.getKey(),product.getValue());
        }
    }

    // TODO Menu Encargo
    private void menuEncargo(){

        boolean exit = false;

        String[] options = {
                "Realizar un encargo",
                "Buscar un encargo",
                "Eliminar encargo",
                "Volver al menu principal"
        };

        while(!exit){

            showMenus("Encargos",options);

            // ORDER
            System.out.print("\n\n\t> ");

            try {

                boolean command = false;
                String order = USER_INPUT.readLine();

                switch (parseCommand(order.toLowerCase())){
                    case 1:
                        makeOrder();
                        break;
                    case 2:
                        searchOrderByClientId();
                        break;
                    case 3:
                        deleteOrder();
                        break;
                    case 4:
                        exit = true;
                        break;
                }

            } catch (Exception e){
                ALERTS.add(e.getMessage());
            }

        }

    }

    private void makeOrder() throws Exception {
        Client selectedUser = selectClient();
        HashMap<Product,Integer> products = productCart();
        assert products != null;
        DATA_SOURCE.agregarEncargo(selectedUser);
        int encargo = DATA_SOURCE.obtenerIdUltimoEncargoDeUnCliente(selectedUser);
        DATA_SOURCE.agregarProductosAEncargo(encargo,products);
    }

    private void deleteOrder() throws Exception {
        String[] messages = {
                "Introduce el id del encargo: "
        };
        ArrayList<String> result = menuSecuencial(messages);
        if(DATA_SOURCE.eliminarEncargo(Integer.parseInt(result.get(0)))){
            throw new Exception("ENCARGO NO ENCONTRADO");
        }
    }

    // TODO Buscar encargo por usuario
    private void searchOrderByClientId() throws Exception {
        Client selected = selectClient();
        assert selected != null;
        showOrders(DATA_SOURCE.obtenerEncargosDeUnCliente(selected));
    }

    // TODO Mostrar encargo
    private void showOrder(Order o, String escape){
        System.out.printf(escape + "%-5s %-45s %-20s"
                ,o.getId()
                ,o.getClient().getFirstName()
                        + " " + o.getClient().getFirstLastname()
                        + " " + o.getClient().getMailAddress()
                ,o.getOrderDate()
        );
    }

    // TODO Mostrar encargos
    private void showOrders(ArrayList<Order> orders){
        if(orders.size() > 0){
            System.out.printf("\n\n\t%-5s %-45s %-20s"
                    ,"ID"
                    ,"CLIENTE"
                    ,"FECHA"
            );
            for(Order o : orders){
                showOrder(o,"\n\t");
                System.out.print("\n\t---------------------------------------------");
            }
        }
    }

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
        System.out.print(escape + "-----------------------------------\n");
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
        showInternalMenus();
        // OPTIONS
        showMenuStyled(title,options,"\n\t");
    }
    private void showInternalMenus(){
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
    }

    // TODO Ask con validation
    private String ask(String message, String escape) throws Exception {
        System.out.println(escape + message);
        System.out.print(escape + "--> ");
        return USER_INPUT.readLine();
    }

    // TODO Comandos
    private int parseCommand(String command) throws Exception {
        switch (command){
            case "help":
                showHelp();
                break;
            case "debug":
                debug = true;
                break;
            case "test":
                throw new Exception("NOT IMPLEMENTED");
            case "author":
                author();
                break;
            case "configure":
                configureNewConnection();
                break;
            case "reset":
                ALERTS.clear();
                debug = false;
                break;
            default:
                try {
                    return Integer.parseInt(command);
                } catch (Exception exception){
                    //
                }
                break;
        }
        return 0;
    }

    private void configureNewConnection() throws Exception {

        String[] messages = {
                "Introduce usuario"
                ,"Introduce la contraseña"
                ,"Introduce el hostname (ip)"
                ,"Introduce la base de datos"
                ,"Introduce el puerto (vacio=3306)"
        };

        ArrayList<String> result = menuSecuencial(messages);

        if(result.get(result.size() - 1).equals("")){
            int i = result.size() - 1;
            result.remove(result.size() - 1);
            result.add(i,"3306");
        }

        for(String r : result){
            if(r.equals("")){
                throw new Exception("DATO VACIO, ABORTANDO...");
            }
        }

        DATA_SOURCE.configureNewConnection(
                String.format("jdbc:mysql://%s:%s/%s"
                        ,result.get(2)
                        ,result.get(4)
                        ,result.get(3))
                ,result.get(0)
                ,result.get(1)
        );

        Encapsulate.encapsulateString("CONEXIÓN ESTABLECIDA","\n\t","%");

    }
    private void author(){

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
    private void showHelp(){

        String[] comandos = {
                "CONFIGURE -- Te permite configurar una nueva conexión de base de datos."
                ,"DEBUG -- Activa las opciones de debug."
                ,"RESET -- Reinicia las alertas y el menu de debug a valores por defecto."
                ,"AUTHOR -- Te muestra un mensaje hecho por el autor del programa."
                ,"TEST -- Permite hacer un test de integración con diferentes partes del programa."
                ,"HELP -- Te muestra una ayuda sobre comandos."
        };

        System.out.printf("\n\t====== %s ======","COMANDOS");

        showOptions(comandos, "\n\t");

    }

    public static void main(String[] args) throws Exception {
        GestorEncarrecs ge = new GestorEncarrecs();
        ge.start();
    }

}
