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
import Application.Persistent.DatabaseDriver;
import Application.Services.Console.Components.Command.OrderParser;
import Application.Services.Console.Components.Menu.DefaultInteractiveMenu;
import Application.Services.Console.Components.Menu.InlineMenu;
import Application.Services.Console.Components.Menu.OptionMenu;
import Application.Services.Console.Components.Menu.SequentialMenu;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Carlos Pomares
 */

public class OrderConsole extends DefaultConsole {

    private int MAX_ERRORS = 5;
    private DatabaseDriver driver;

    public OrderConsole() {
        driver = new DatabaseDriver();
        OrderParser.setConsole(this);
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
                        test();
                        break;
                    case 3:
                        throw new Exception("NOT IMPLEMENTED");
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

    // TODO EXAMPLE INLINE MENU
    private void test(){

        String[] options = {
                "Agregar cliente",
                "Visualizar clientes",
                "Buscar cliente por nombre",
                "Volver al menu principal",
                "Volver al menu principal",
                "Volver al menu principal",
                "Volver al menu principal"
        };

        InlineMenu optionMenu = new InlineMenu(options,"\t",1);

        OrderParser parser = new OrderParser() {
            @Override
            protected int callBack(String command) throws Exception {
                switch (Integer.parseInt(command)){
                    case 1:
                        registerNewClient();
                        break;
                    case 2:
                        test();
                        break;
                    case 3:
                        throw new Exception("NOT IMPLEMENTED");
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
                ,"\n\t> "
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
                    case 1: case 2: case 3:
                        throw new Exception("NOT IMPLEMENTED");
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
                    case 1: case 2: case 3:
                        throw new Exception("NOT IMPLEMENTED");
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

    @Override
    protected void main() {
        init();
    }

}
