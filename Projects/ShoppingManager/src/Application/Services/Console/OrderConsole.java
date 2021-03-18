package Application.Services.Console;

/*

    Project     Programming21
    Package     Application.Services.Console    
    
    Version     1.0      
    Author      Carlos Pomares
    Date        2021-03-18

    DESCRIPTION
    
*/

import Application.Entities.Client;
import Application.Entities.Order;
import Application.Entities.Product;
import Application.Persistent.DatabaseDriver;
import Application.Services.Console.Components.Command.OrderParser;
import Application.Services.Console.Components.Menu.*;
import Application.Services.Encapsulate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author Carlos Pomares
 */

public class OrderConsole extends DefaultConsole {

    private int MAX_ERRORS = 5;
    private DatabaseDriver driver;

    public OrderConsole() {
        driver = new DatabaseDriver();
        OrderParser.setConsole(this);

        try {
            driver.stablishConnection();
        } catch (Exception e){
            errorLog.add(e.getMessage());
        }

    }

    /*
        MAIN MENU
     */

    private void init(){

        String[] options = {
            "Cliente"
            ,"Producto"
            ,"Encargos"
            ,"Salir"
        };

        OptionMenu optionMenu = new OptionMenu(options,"\t","Main Menu","%s",1,true);

        OrderParser orderParser = new OrderParser() {
            @Override
            protected int callBack(String command) throws Exception {
                switch (Integer.parseInt(command)){
                    case 1:
                        client();
                        break;
                    case 2:
                        product();
                        break;
                    case 3:
                        order();
                        break;
                    case 4:
                        return -1;
                }
                return 0;
            }
        };

        DefaultInteractiveMenu menu = new DefaultInteractiveMenu(
                this.errorLog
                ,optionMenu
                ,orderParser
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

    /*
        CLIENT MENU
     */

    private void client(){

        String[] options = {
                "Agregar cliente",
                "Visualizar clientes",
                "Buscar cliente por nombre",
                "Volver al menu principal"
        };

        OptionMenu optionMenu = new OptionMenu(options,"\t","Client Menu","%s",1,true);

        OrderParser parser = new OrderParser() {
            @Override
            protected int callBack(String command) throws Exception {
                switch (Integer.parseInt(command)){
                    case 1:
                        registerNewClient();
                        break;
                    case 2:
                        clientView((ArrayList<Client>) driver.obtenerTodosLosClientes(),false);
                        break;
                    case 3:
                        clientView(searchClientByName(),false);
                        break;
                    case 4:
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

                optionMenu.show();

            }

        };

        menu.show();

    }

    private void registerNewClient() throws SQLException {

        String[] messages = {
                "Introduce el primer nombre"
                ,"Introduce el segundo nombre"
                ,"Introduce el primer apellido"
                ,"Introduce el segundo apellido"
                ,"Introduce el domicilio"
                ,"Introduce el correo electronico (example@example.com)"
                ,"Introduce el número de teléfono (000-00-00-00)"
        };

        String[] validation = {
                "^[a-zA-Z0-9]+$"
                ,"^[a-zA-Z0-9]*$"
                ,"^[a-zA-Z0-9]+$"
                ,"^[a-zA-Z0-9]+$"
                ,"^[a-zA-Z0-9_ ]*$"
                ,"(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])"
                ,"^[0-9]{3}-[0-9]{2}-[0-9]{2}-[0-9]{2}$"
        };

        SequentialMenu menu = new SequentialMenu(messages,validation,reader,"\t",errorLog);
        menu.show();

        ArrayList<String> result = menu.getOutput();

        int i = result.size() - 1;
        String n = result.get(result.size() - 1);
        result.remove(result.get(result.size() - 1));
        result.add(i,n.replace("-",""));

        // TODO Add driver update.
        driver.agregarCliente(new Client(
                driver.obtenerNuevoIDCliente(),
                result.get(0),
                result.get(1),
                result.get(2),
                result.get(3),
                result.get(4),
                result.get(5),
                result.get(6)
        ));

    }

    private ArrayList<Client> searchClientByName() throws Exception {

        String[] messages = {
                "Introduce el nombre del cliente"
        };

        SequentialMenu menu = new SequentialMenu(messages,reader,"\t",errorLog);
        menu.show();

        ArrayList<String> result = menu.getOutput();

        if(result.get(0).equals(""))
            throw new Exception("No puede estar vacio...");

        return (ArrayList<Client>) driver.buscarClientePorNombre(result.get(0));
    }

    private Client clientView(ArrayList<Client> clients,boolean selection) throws Exception {

        final Client[] selected = new Client[1];

        if(clients.size() == 0)
            throw new Exception("CLIENTS IS EMPTY");

        String header = String.format(
                "\n\t%-5s %-20s %-25s %-25s %-25s %-9s"
                ,"ID"
                ,"NOMBRE"
                ,"APELLIDO"
                ,"CALLE"
                ,"MAIL"
                ,"TELÉFONO"
        );

        String[] options = {
                "SIGUIENTE"
                ,"ANTERIOR"
                ,"SIGUIENTE PÁGINA"
                ,"ANTERIOR PÁGINA"
                ,"SELECCIONAR"
                ,"SALIR"
        };

        InlineMenu inlineMenu = new InlineMenu(options,"\t",1);

        // CASTING LIST
        ArrayList<Object> items = new ArrayList<>(clients);

        SelectionMenu selectionMenu = new SelectionMenu(
                "\t"
                ,items
                ,header
        ) {
            @Override
            protected void showItem(Object o, boolean selected) {
                Client c = (Client) o;
                String format = String.format(
                        "%-5d %-20s %-25s %-25s %-25s %-9s"
                        ,c.getId()
                        ,(c.getFirstName() + " " + ((!c.getSecondName().equalsIgnoreCase("null")) ? c.getSecondName() : ""))
                        ,(c.getFirstLastname() + " " + c.getSecondLastname())
                        ,c.getStreetAddress()
                        ,c.getMailAddress()
                        ,c.getPhoneNumber()
                );

                if(selected){
                    Encapsulate.encapsulateString(format,"\t");
                } else {
                    System.out.printf("\n\t%s",format);
                }
            }
        };

        OrderParser orderParser = new OrderParser() {
            @Override
            protected int callBack(String command) throws Exception {
                switch (Integer.parseInt(command)){
                    case 1:
                        selectionMenu.nextItem();
                        break;
                    case 2:
                        selectionMenu.previousItem();
                        break;
                    case 3:
                        selectionMenu.nextPage();
                        break;
                    case 4:
                        selectionMenu.previousPage();
                        break;
                    case 5:
                        if(!selection){
                            throw new Exception("NOT ENABLED");
                        } else {
                            selected[0] = (Client) selectionMenu.select();
                            return -1;
                        }
                    case 6:
                        return -1;
                }
                return 0;
            }
        };

        DefaultInteractiveMenu menu = new SelectionInteractiveMenu(
                this.errorLog
                ,inlineMenu
                ,orderParser
                ,reader
                ,"\n\t> "
                ,selectionMenu
        ) {
            @Override
            protected void outsideLoop() {

            }
        };

        menu.show();

        return selected[0];
    }

    /*
        PRODUCT MENU
     */

    private void product(){

        String[] options = {
                "Agregar producto",
                "Buscar producto por título",
                "Ver todos los productos",
                "Volver al menu principal"
        };

        OptionMenu optionMenu = new OptionMenu(options,"\t","Product Menu","%s",1,true);

        OrderParser parser = new OrderParser() {
            @Override
            protected int callBack(String command) throws Exception {
                switch (Integer.parseInt(command)){
                    case 1:
                        registerNewProduct();
                        break;
                    case 2:
                        productView(searchProductByTitle(),false);
                        break;
                    case 3:
                        productView((ArrayList<Product>) driver.obtenerTodosLosProductos(),false);
                        break;
                    case 4:
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

                optionMenu.show();

            }

        };

        menu.show();

    }

    private void registerNewProduct() throws Exception {

        String[] messages = {
                "Introduce el título ",
                "Introduce la descripción ",
                "Introduce el precio ",
                "Introduce el stock "
        };

        String[] validation = {
                "."
                ,"."
                ,"^[0-9]*.[0-9]{2}$"
                ,"^[0-9]*$"
        };

        SequentialMenu menu = new SequentialMenu(messages,validation,reader,"\t",errorLog);
        menu.show();

        ArrayList<String> result = menu.getOutput();

        if(result.size() < 4)
            throw new Exception("NOT ENOUGH RESULTS");

        driver.agregarProducto(new Product(
                result.get(0)
                ,result.get(1)
                ,Float.parseFloat(result.get(2))
                ,Integer.parseInt(result.get(3))
        ));

    }

    private ArrayList<Product> searchProductByTitle() throws Exception {

        String[] messages = {
                "Introduce el título del producto"
        };

        SequentialMenu menu = new SequentialMenu(messages,reader,"\t",errorLog);
        menu.show();

        ArrayList<String> result = menu.getOutput();

        if(result.get(0).equals(""))
            throw new Exception("No puede estar vacio...");

        return (ArrayList<Product>) driver.buscarProductoPorTitulo(result.get(0));
    }

    private Product productView(ArrayList<Product> products, boolean selection) throws Exception {

        final Product[] selected = new Product[1];

        String header = String.format(
                "\n\t%-5s %-20s %-50s %-8s %-8s"
                ,"ID"
                ,"TÍTULO"
                ,"DESCRIPCIÓN"
                ,"PRECIO"
                ,"STOCK"
        );

        String[] options = {
                "SIGUIENTE"
                ,"ANTERIOR"
                ,"SIGUIENTE PÁGINA"
                ,"ANTERIOR PÁGINA"
                ,"SELECCIONAR"
                ,"SALIR"
        };

        InlineMenu inlineMenu = new InlineMenu(options,"\t",1);

        // CASTING LIST
        ArrayList<Object> items = new ArrayList<>(products);

        SelectionMenu selectionMenu = new SelectionMenu(
                "\t"
                ,items
                ,header
        ) {
            @Override
            protected void showItem(Object o, boolean selected) {
                Product p = (Product) o;
                String format = String.format(
                        "%-5d %-20s %-50s %-8.2f %-8d"
                        ,p.getId()
                        ,p.getTitle()
                        ,p.getDescription()
                        ,p.getPrice()
                        ,p.getStock()
                );

                if(selected){
                    Encapsulate.encapsulateString(format,"\t");
                } else {
                    System.out.printf("\n\t%s",format);
                }

            }
        };

        OrderParser orderParser = new OrderParser() {
            @Override
            protected int callBack(String command) throws Exception {
                switch (Integer.parseInt(command)){
                    case 1:
                        selectionMenu.nextItem();
                        break;
                    case 2:
                        selectionMenu.previousItem();
                        break;
                    case 3:
                        selectionMenu.nextPage();
                        break;
                    case 4:
                        selectionMenu.previousPage();
                        break;
                    case 5:
                        if(!selection){
                            throw new Exception("NOT ENABLED");
                        } else {
                            selected[0] = (Product) selectionMenu.select();
                            return -1;
                        }
                    case 6:
                        return -1;
                }
                return 0;
            }
        };

        DefaultInteractiveMenu menu = new SelectionInteractiveMenu(
                this.errorLog
                ,inlineMenu
                ,orderParser
                ,reader
                ,"\n\t> "
                ,selectionMenu
        ) {
            @Override
            protected void outsideLoop() {

            }
        };

        menu.show();

        return selected[0];
    }

    /*
        ORDER MENU
     */

    private void order(){

        String[] options = {
                "Realizar un encargo",
                "Buscar un encargo",
                "Eliminar encargo",
                "Volver al menu principal"
        };

        OptionMenu optionMenu = new OptionMenu(options,"\t","Order Menu","%s",1,true);

        OrderParser parser = new OrderParser() {
            @Override
            protected int callBack(String command) throws Exception {
                switch (Integer.parseInt(command)){
                    case 1:
                        registerNewOrder();
                        break;
                    case 2:
                        searchOrderByClient();
                        break;
                    case 3:
                        removeOrder();
                        break;
                    case 4:
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

                optionMenu.show();

            }

        };

        menu.show();

    }

    private void registerNewOrder() throws Exception {
        Client selectedClient = clientView((ArrayList<Client>) driver.obtenerTodosLosClientes(),true);
        HashMap<Product,Integer> products = productCart();
        assert selectedClient != null;
        assert products != null;
        driver.agregarEncargo(selectedClient);
        int order = driver.obtenerIdUltimoEncargoDeUnCliente(selectedClient);
        driver.agregarProductosAEncargo(order,products);
        updateProducts(products);
    }

    private void removeOrder() throws Exception {
        Order order = orderView(driver.obtenerTodosLosEncargos(),true);
        if(driver.eliminarEncargo(order.getId())){
            throw new Exception("ENCARGO NO ENCONTRADO");
        }
    }

    private void searchOrderByClient() throws Exception {
        Client selected = clientView((ArrayList<Client>) driver.obtenerTodosLosClientes(),true);
        assert selected != null;
        orderView(driver.obtenerEncargosDeUnCliente(selected),false);
    }

    private HashMap<Product,Integer> productCart() throws Exception {

        HashMap<Product,Integer> products = new HashMap<>();

        final ArrayList<HashMap<Product,Integer>> result = new ArrayList<>();

        String[] options = {
                "AGREGAR PRODUCTO"
                ,"BORRAR PRODUCTO"
                ,"SIGUIENTE"
                ,"EJECUTAR Y SALIR"
                ,"SALIR SIN GUARDAR"
        };

        InlineMenu inlineMenu = new InlineMenu(options,"\t",1);

        DefaultMenu productsView = new DefaultMenu(
                "\t"
        ) {
            @Override
            protected void update() {

                System.out.printf(
                        "\n" + escapeCharacters + "%-5s %-30s %-10s %-10s"
                        ,"ID"
                        ,"TITULO"
                        ,"PRECIO"
                        ,"CANTIDAD"
                );

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
        };

        OrderParser parser = new OrderParser() {
            @Override
            protected int callBack(String command) throws Exception {
                switch (Integer.parseInt(command)){
                    case 1:
                        Product product = productView((ArrayList<Product>) driver.obtenerTodosLosProductos(),true);
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
                        break;
                    case 4:
                        if(products.size() > 0 && validateProducts(products)) {
                            result.add(products);
                            return -1;
                        } else {
                            throw new Exception("CART CANNOT BE EMPTY.");
                        }
                    case 5:
                        return -1;
                }
                return 0;
            }
        };

        DefaultInteractiveMenu menu = new DefaultInteractiveMenu(
                errorLog
                ,inlineMenu
                ,parser
                ,reader
                ,"\n\t> "
        ) {
            @Override
            protected void outsideLoop() {

            }

            @Override
            protected void loopBlock() {

                System.out.printf("\n\t%s",Encapsulate.inlineEncapsulate("CARRITO",35,2));

                if(products.size() > 0){
                    productsView.show();
                }

                optionMenu.show();

            }
        };

        menu.show();

        return result.get(0);
    }

    private boolean validateProduct(Product p,int quantity) throws Exception {
        return driver.obtenerProductoPorId(p.getId()).getStock() >= quantity;
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
            driver.updateProduct(product.getKey(),product.getValue());
        }
    }

    private Order orderView(ArrayList<Order> orders, boolean selection) throws Exception {

        final Order[] selected = new Order[1];

        String header = String.format(
                "\n\t%-5s %-45s %-25s %-10s %-10s"
                ,"ID"
                ,"CLIENTE"
                ,"FECHA"
                ,"PRODUCTOS"
                ,"TOTAL"
        );

        String[] options = {
                "SIGUIENTE"
                ,"ANTERIOR"
                ,"SIGUIENTE PÁGINA"
                ,"ANTERIOR PÁGINA"
                ,"SELECCIONAR"
                ,"SALIR"
        };

        InlineMenu inlineMenu = new InlineMenu(options,"\t",1);

        // CASTING LIST
        ArrayList<Object> items = new ArrayList<>(orders);

        SelectionMenu selectionMenu = new SelectionMenu(
                "\t"
                ,items
                ,header
        ) {
            @Override
            protected void showItem(Object o, boolean selected) {
                Order order = (Order) o;
                float total = 0f;
                for(Map.Entry<Product,Integer> p : order.getProducts().entrySet()){
                    total += p.getKey().getPrice() * p.getValue();
                }
                String format = String.format(
                        "%-5d %-45s %-25s %-10d %-10.2f"
                        ,order.getId()
                        ,order.getClient().getFirstName()
                                + " " + order.getClient().getFirstLastname()
                                + " " + order.getClient().getMailAddress()
                        ,order.getOrderDate().toString()
                        ,order.getProducts().size()
                        ,total
                );

                if(selected){
                    Encapsulate.encapsulateString(format,"\t");
                } else {
                    System.out.printf("\n\t%s",format);
                }

            }
        };

        OrderParser orderParser = new OrderParser() {
            @Override
            protected int callBack(String command) throws Exception {
                switch (Integer.parseInt(command)){
                    case 1:
                        selectionMenu.nextItem();
                        break;
                    case 2:
                        selectionMenu.previousItem();
                        break;
                    case 3:
                        selectionMenu.nextPage();
                        break;
                    case 4:
                        selectionMenu.previousPage();
                        break;
                    case 5:
                        if(!selection){
                            throw new Exception("NOT ENABLED");
                        } else {
                            selected[0] = (Order) selectionMenu.select();
                            return -1;
                        }
                    case 6:
                        return -1;
                }
                return 0;
            }
        };

        DefaultInteractiveMenu menu = new SelectionInteractiveMenu(
                this.errorLog
                ,inlineMenu
                ,orderParser
                ,reader
                ,"\n\t> "
                ,selectionMenu
        ) {
            @Override
            protected void outsideLoop() {

            }
        };

        menu.show();

        return selected[0];
    }

    // TODO COMMANDS
    public void help(){

        String[] comandos = {
                "CONFIGURE -- Te permite configurar una nueva conexión de base de datos."
                ,"DEBUG -- Activa las opciones de debug."
                ,"RESET -- Reinicia las alertas y el menu de debug a valores por defecto."
                ,"AUTHOR -- Te muestra un mensaje hecho por el autor del programa."
                ,"TEST -- Permite hacer un test de integración con diferentes partes del programa."
                ,"HELP -- Te muestra una ayuda sobre comandos."
        };

        OptionMenu optionMenu = new OptionMenu(comandos,"\t","COMANDOS","%s");

        optionMenu.show();

    }
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
    public void reset(){
        errorLog.clear();
    }

    @Override
    protected void main() {
        init();
    }

}
