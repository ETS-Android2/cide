package com.github.pomaretta.cide.console;

import java.util.ArrayList;

import com.github.pomaretta.cide.entity.Department;
import com.github.pomaretta.cide.entity.Person;
import com.github.pomaretta.cide.entity.Teacher;
import com.github.pomaretta.cide.service.CideService;
import com.github.pomaretta.termux.Console.DefaultConsole;
import com.github.pomaretta.termux.Menu.DefaultInteractiveMenu;
import com.github.pomaretta.termux.Menu.InlineMenu;
import com.github.pomaretta.termux.Menu.OptionMenu;
import com.github.pomaretta.termux.Menu.OptionsMenu;
import com.github.pomaretta.termux.Menu.SelectionInteractiveMenu;
import com.github.pomaretta.termux.Menu.SelectionMenu;
import com.github.pomaretta.termux.Menu.SequentialMenu;
import com.github.pomaretta.termux.Util.Encapsulate;

import org.hibernate.Session;

public class Console extends DefaultConsole {

    private CideService service;
    private boolean connected;

    private void init() {

        String[] options = new String[]{
            "Conectar con la base de datos",
            "Salir"
        };

        final OptionsMenu menu = new OptionMenu(
            options,
            "",
            "ORM - CIDE",
            "%s",
            1,
            true
        );

        Parser parser = new Parser() {
            @Override
            protected int callBack(String arg0) throws Exception {
                switch (Integer.parseInt(arg0)) {
                    case 1:
                        // Obtener un valor | Conectarse a un server
                        if (!connected) {
                            connectToDatabase();
                            break;
                        }
                        searchMenu();
                        break;
                    case 2:
                        if (!connected) return -1;
                        previewMenu();
                        break;
                    case 3:
                        if (!connected) break;
                        return -1;
                }
                
                // Wait for user input
                System.out.print("Press enter to continue...");
                System.in.read();
                // Clear console
                // Make cls if windows else clear
                System.out.print("\033[H\033[2J");
                System.out.flush();

                return 0;
            }
        };

        DefaultInteractiveMenu interactiveMenu = new DefaultInteractiveMenu(
            this.errorLog,
            menu,
            parser,
            this.reader,
            "> "
        ) {
            protected void outsideLoop() {
            };
            protected void loopBlock() {
                if (connected) {
                    this.optionMenu = new OptionMenu(
                        new String[]{
                            "Buscador",
                            "Visualizador",
                            "Salir"
                        },
                        "",
                        "ORM - CIDE",
                        "%s",
                        1,
                        true
                    );

                } else {
                    this.optionMenu = menu;
                }          

                this.optionMenu.show();
            };
        };

        interactiveMenu.show();
    }

    private void connectToDatabase() {
        try {
            System.out.print("Trying to connect");
            for (int i = 0; i < 4; i++) {
                System.out.print(".");
                Thread.sleep(500);
            }
            this.service = new CideService();
            Session session = this.service.getSessionFactory().openSession();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
            this.errorLog.add(e.getMessage());
            this.connected = false;
            return;
        }
        System.out.printf("\nConnected to database!\n");
        this.connected = true;
    }

    // Search Menu
    private void searchMenu() {

        String[] options = new String[]{
            "Buscar persona",
            "Buscar departamento",
            "Volver al menú"
        };

        final OptionsMenu menu = new OptionMenu(
            options,
            "",
            "BUSCADOR - CIDE",
            "%s",
            1,
            true
        );

        Parser parser = new Parser() {
            @Override
            protected int callBack(String arg0) throws Exception {
                switch (Integer.parseInt(arg0)) {
                    case 1:
                        searchPerson();
                        break;
                    case 2:
                        break;
                    case 3:
                        return -1;
                }
                return 0;
            }
        };

        DefaultInteractiveMenu interactiveMenu = new DefaultInteractiveMenu(
            this.errorLog,
            menu,
            parser,
            this.reader,
            "> "
        ) {
            protected void outsideLoop() {
            };
            protected void loopBlock() {
                this.optionMenu.show();
            };
        };

        interactiveMenu.show();
    }

    private void searchPerson() {

        String[] options = new String[]{
            "Buscar por id",
            "Buscar por nombre",
            "Buscar por primer apellido",
            "Buscar por segundo apellido",
            "Volver al menú"
        };

        final OptionsMenu menu = new OptionMenu(
            options,
            "",
            "PERSONA - CIDE",
            "%s",
            1,
            true
        );

        Parser parser = new Parser() {
            @Override
            protected int callBack(String arg0) throws Exception {
                switch (Integer.parseInt(arg0)) {
                    case 1:
                        searchPersonById();
                        break;
                    case 2:
                        searchPersonByName();
                        break;
                    case 3:
                        searchPersonByFirstLastName();
                        break;
                    case 4:
                        searchPersonBySecondLastName();
                        break;
                    case 5:
                        return -1;
                }
                return 0;
            }
        };

        DefaultInteractiveMenu interactiveMenu = new DefaultInteractiveMenu(
            this.errorLog,
            menu,
            parser,
            this.reader,
            "> "
        ) {
            protected void outsideLoop() {
            };
            protected void loopBlock() {
                this.optionMenu.show();
            };
        };

        interactiveMenu.show();
    }

    private void searchDepartment() {

        String[] options = new String[]{
            "Buscar por id",
            "Buscar por nombre",
            "Volver al menú"
        };

        final OptionsMenu menu = new OptionMenu(
            options,
            "",
            "DEPARTAMENTO - CIDE",
            "%s",
            1,
            true
        );

        Parser parser = new Parser() {
            @Override
            protected int callBack(String arg0) throws Exception {
                switch (Integer.parseInt(arg0)) {
                    case 1:
                        searchDepartmentById();
                        break;
                    case 2:
                        searchDepartmentByName();
                        break;
                    case 3:
                        return -1;
                }
                return 0;
            }
        };

        DefaultInteractiveMenu interactiveMenu = new DefaultInteractiveMenu(
            this.errorLog,
            menu,
            parser,
            this.reader,
            "> "
        ) {
            protected void outsideLoop() {
            };
            protected void loopBlock() {
                this.optionMenu.show();
            };
        };

        interactiveMenu.show();
    }

    // Person searchers
    private void searchPersonById() {

        String[] messages = {
            "Identificador"
        };

        String[] validation = {
            "^[0-9]*$",
        };

        SequentialMenu menu = new SequentialMenu(messages, this.reader, "", this.errorLog, validation);
        menu.show();

        ArrayList<String> values = menu.getOutput();

        Integer id = Integer.parseInt(values.get(0));

        Person p = this.service.getPersonUnit().get(id);
        Teacher[] t = this.service.getTeacherUnit().getByPersonIds(
            new Integer[]{id}
        );
        System.out.println(p.toString());
        if (t.length > 0) {
            System.out.println(t[0].toString());
        }
    }

    private void searchPersonByName() {

        String[] messages = {
            "Nombre"
        };

        String[] validation = {
            "^[a-zA-Z0-9 ]*$",
        };

        SequentialMenu menu = new SequentialMenu(messages, this.reader, "", this.errorLog, validation);
        menu.show();

        ArrayList<String> values = menu.getOutput();

        String name = values.get(0);

        Person[] p = this.service.getPersonUnit().getByName(name);
        
        // Set all person id to the teacher getter
        ArrayList<Integer> ids = new ArrayList<Integer>();
        for (Person person : p) {
            ids.add(person.getId());
        }
        Teacher[] t = this.service.getTeacherUnit().getByPersonIds(
            ids.toArray(new Integer[ids.size()])
        );

        for (Person person : p) {
            System.out.println(person.toString());
        }
    }

    private void searchPersonByFirstLastName() {

        String[] messages = {
            "Primer apellido"
        };

        String[] validation = {
            "^[a-zA-Z0-9 ]*$",
        };

        SequentialMenu menu = new SequentialMenu(messages, this.reader, "", this.errorLog, validation);
        menu.show();

        ArrayList<String> values = menu.getOutput();

        String name = values.get(0);

        Person[] p = this.service.getPersonUnit().getByFirstLastName(name);
        
        // Set all person id to the teacher getter
        ArrayList<Integer> ids = new ArrayList<Integer>();
        for (Person person : p) {
            ids.add(person.getId());
        }
        Teacher[] t = this.service.getTeacherUnit().getByPersonIds(
            ids.toArray(new Integer[ids.size()])
        );

        for (Person person : p) {
            System.out.println(person.toString());
        }
    }

    private void searchPersonBySecondLastName() {

        String[] messages = {
            "Primer apellido"
        };

        String[] validation = {
            "^[a-zA-Z0-9 ]*$",
        };

        SequentialMenu menu = new SequentialMenu(messages, this.reader, "", this.errorLog, validation);
        menu.show();

        ArrayList<String> values = menu.getOutput();

        String name = values.get(0);

        Person[] p = this.service.getPersonUnit().getBySecondLastName(name);
        
        // Set all person id to the teacher getter
        ArrayList<Integer> ids = new ArrayList<Integer>();
        for (Person person : p) {
            ids.add(person.getId());
        }
        Teacher[] t = this.service.getTeacherUnit().getByPersonIds(
            ids.toArray(new Integer[ids.size()])
        );

        for (Person person : p) {
            System.out.println(person.toString());
        }
    }

    // Department searchers
    private void searchDepartmentById() {

        String[] messages = {
            "Identificador"
        };

        String[] validation = {
            "^[0-9]*$",
        };

        SequentialMenu menu = new SequentialMenu(messages, this.reader, "", this.errorLog, validation);
        menu.show();

        ArrayList<String> values = menu.getOutput();

        Integer id = Integer.parseInt(values.get(0));

        Department d = this.service.getDepartmentUnit().get(id);
        System.out.println(d.toString());

    }

    private void searchDepartmentByName() {
        String[] messages = {
            "Nombre"
        };

        String[] validation = {
            "^[a-zA-Z0-9 ]*$",
        };

        SequentialMenu menu = new SequentialMenu(messages, this.reader, "", this.errorLog, validation);
        menu.show();

        ArrayList<String> values = menu.getOutput();

        String name = values.get(0);

        Department[] departments = this.service.getDepartmentUnit().getByName(name);
        
        for (Department d : departments) {
            System.out.println(d.toString());
        }
    }

    // Preview Menu
    private void previewMenu() {

        String[] options = new String[]{
            "Visualizer personas",
            "Visualizar departamentos",
            "Volver al menú"
        };

        final OptionsMenu menu = new OptionMenu(
            options,
            "",
            "Visualizar - CIDE",
            "%s",
            1,
            true
        );

        Parser parser = new Parser() {
            @Override
            protected int callBack(String arg0) throws Exception {
                switch (Integer.parseInt(arg0)) {
                    case 1:
                        // Convert normal list to ArrayList
                        ArrayList<Person> persons = new ArrayList<Person>();
                        for (Person p : service.getPersonUnit().getAll()) {
                            persons.add(p);
                        }
                        personView(
                            persons,    
                            false
                        );
                        break;
                    case 2:
                        // Convert normal list to ArrayList
                        ArrayList<Department> departments = new ArrayList<Department>();
                        for (Department p : service.getDepartmentUnit().getAll()) {
                            departments.add(p);
                        }
                        departmentView(
                            departments,
                            false
                        );
                        break;
                    case 3:
                        return -1;
                }
                return 0;
            }
        };

        DefaultInteractiveMenu interactiveMenu = new DefaultInteractiveMenu(
            this.errorLog,
            menu,
            parser,
            this.reader,
            "> "
        ) {
            protected void outsideLoop() {
            };
            protected void loopBlock() {
                this.optionMenu.show();
            };
        };

        interactiveMenu.show();
    }

    private Person personView(ArrayList<Person> products, final boolean selection) throws Exception {

        final Person[] selected = new Person[1];

        String header = String.format(
                "\n\t%-5s %-20s %-20s %-50s %-10s"
                ,"ID"
                ,"NIF"
                ,"NOMBRE"
                ,"APELLIDOS"
                ,"GENERO"
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
        ArrayList<Object> items = new ArrayList<Object>(products);

        final SelectionMenu selectionMenu = new SelectionMenu(
                "\t"
                ,items
                ,header
        ) {
            @Override
            protected void showItem(Object o, boolean selected) {
                Person p = (Person) o;
                String format = String.format(
                        "%-5s %-20s %-20s %-50s %-10s"
                        ,p.getId()
                        ,p.getNif()
                        ,p.getName()
                        ,p.getFirstLastname() + " " + p.getSecondLastname()
                        ,p.getGender()
                );

                if(selected){
                    Encapsulate.encapsulateString(format,"\t");
                } else {
                    System.out.printf("\n\t%s",format);
                }

            }
        };

        Parser orderParser = new Parser() {
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
                            selected[0] = (Person) selectionMenu.select();
                            return -1;
                        }
                    case 6:
                        return -1;
                }

                // Clear console
                System.out.print("\033[H\033[2J");
                System.out.flush();

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

    private Department departmentView(ArrayList<Department> departments, final boolean selection) throws Exception {

        final Department[] selected = new Department[1];

        String header = String.format(
                "\n\t%-5s %-20s"
                ,"ID"
                ,"NOMBRE"
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
        ArrayList<Object> items = new ArrayList<Object>(departments);

        final SelectionMenu selectionMenu = new SelectionMenu(
                "\t"
                ,items
                ,header
        ) {
            @Override
            protected void showItem(Object o, boolean selected) {
                Department p = (Department) o;
                String format = String.format(
                        "%-5s %-20s"
                        ,p.getId()
                        ,p.getName()
                );

                if(selected){
                    Encapsulate.encapsulateString(format,"\t");
                } else {
                    System.out.printf("\n\t%s",format);
                }

            }
        };

        Parser orderParser = new Parser() {
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
                            selected[0] = (Department) selectionMenu.select();
                            return -1;
                        }
                    case 6:
                        return -1;
                }

                // Clear console
                System.out.print("\033[H\033[2J");
                System.out.flush();

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

    @Override
    protected void main() {
        init();
    }

}
