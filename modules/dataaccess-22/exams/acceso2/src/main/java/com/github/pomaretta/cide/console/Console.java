package com.github.pomaretta.cide.console;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Arrays;

import com.github.pomaretta.cide.dto.PersonTeacher;
import com.github.pomaretta.cide.entity.Department;
import com.github.pomaretta.cide.entity.Person;
import com.github.pomaretta.cide.service.CideService;
import com.github.pomaretta.termux.Console.DefaultConsole;
import com.github.pomaretta.termux.Error.ErrorLog;
import com.github.pomaretta.termux.Menu.DefaultInteractiveMenu;
import com.github.pomaretta.termux.Menu.OptionMenu;
import com.github.pomaretta.termux.Menu.OptionsMenu;

import org.hibernate.Session;

public class Console extends DefaultConsole {

    private CideService service;
    private boolean connected;

    private PersonMenu personMenu;
    private DepartmentMenu departmentMenu;

    public ErrorLog getErrorLog() {
        return errorLog;
    }

    public BufferedReader getReader() {
        return this.reader;
    }

    public CideService getService() {
        return service;
    }

    public PersonMenu getPersonMenu() {
        return personMenu;
    }

    public DepartmentMenu getDepartmentMenu() {
        return departmentMenu;
    }

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
                        createMenu();
                        break;
                    case 4:
                        if (!connected) break;
                        modifyMenu();
                        break;
                    case 5:
                        if (!connected) break;
                        removeMenu();
                        break;
                    case 6:
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
                            "Creador",
                            "Modificador",
                            "Eliminador",
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
                        personMenu.search();
                        break;
                    case 2:
                        departmentMenu.search();
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

    private void createMenu() {

        String[] options = new String[]{
            "Persona",
            "Departamento",
            "Profesor",
            "Volver al menú"
        };

        final OptionsMenu menu = new OptionMenu(
            options,
            "",
            "CREADOR - CIDE",
            "%s",
            1,
            true
        );

        Parser parser = new Parser() {
            @Override
            protected int callBack(String arg0) throws Exception {
                switch (Integer.parseInt(arg0)) {
                    case 1:
                        personMenu.create();
                        break;
                    case 2:
                        departmentMenu.create();
                        break;
                    case 3:
                        personMenu.createTeacher();
                        break;
                    case 4:
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
                        Person[] persons = service.getPersonUnit().getAll();
                        ArrayList<PersonTeacher> personsList = personMenu.getPersonsAsTeachers(persons);
                        personMenu.view(
                            personsList,
                            false
                        );
                        break;
                    case 2:
                        Department[] departments = service.getDepartmentUnit().getAll();
                        Department dep = departmentMenu.view(
                            new ArrayList<Department>(Arrays.asList(departments)),
                            true
                        );
                        personMenu.viewInDepartment(dep);
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

    private void modifyMenu() {

        String[] options = new String[]{
            "Modificar personas",
            "Modificar departamentos",
            "Volver al menú"
        };

        final OptionsMenu menu = new OptionMenu(
            options,
            "",
            "Modificar - CIDE",
            "%s",
            1,
            true
        );

        Parser parser = new Parser() {
            @Override
            protected int callBack(String arg0) throws Exception {
                switch (Integer.parseInt(arg0)) {
                    case 1:
                        personMenu.modify();
                        break;
                    case 2:
                        departmentMenu.modify();
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

    private void removeMenu() {

        String[] options = new String[]{
            "Eliminar persona",
            "Eliminar profesor",
            "Eliminar departamento",
            "Volver al menú"
        };

        final OptionsMenu menu = new OptionMenu(
            options,
            "",
            "Modificar - CIDE",
            "%s",
            1,
            true
        );

        Parser parser = new Parser() {
            @Override
            protected int callBack(String arg0) throws Exception {
                switch (Integer.parseInt(arg0)) {
                    case 1:
                        personMenu.remove();
                        break;
                    case 2:
                        personMenu.removeTeacher();
                        break;
                    case 3:
                        departmentMenu.remove();
                        break;
                    case 4:
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

    @Override
    protected void main() {

        this.personMenu = new PersonMenu(this);
        this.departmentMenu = new DepartmentMenu(this);

        init();
    }

}
