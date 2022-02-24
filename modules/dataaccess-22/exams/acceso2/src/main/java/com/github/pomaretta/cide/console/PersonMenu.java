package com.github.pomaretta.cide.console;

import java.util.ArrayList;
import java.util.Arrays;

import com.github.pomaretta.cide.dto.PersonTeacher;
import com.github.pomaretta.cide.entity.Department;
import com.github.pomaretta.cide.entity.Person;
import com.github.pomaretta.cide.entity.Teacher;
import com.github.pomaretta.termux.Menu.DefaultInteractiveMenu;
import com.github.pomaretta.termux.Menu.InlineMenu;
import com.github.pomaretta.termux.Menu.OptionMenu;
import com.github.pomaretta.termux.Menu.OptionsMenu;
import com.github.pomaretta.termux.Menu.SelectionInteractiveMenu;
import com.github.pomaretta.termux.Menu.SelectionMenu;
import com.github.pomaretta.termux.Menu.SequentialMenu;
import com.github.pomaretta.termux.Util.Encapsulate;

public class PersonMenu implements Menu<PersonTeacher> {

    private Console console;

    public PersonMenu(Console console) {
        this.console = console;
    }

    @Override
    public PersonTeacher view(ArrayList<PersonTeacher> list, final boolean selection) {
        final PersonTeacher[] selected = new PersonTeacher[1];

        String header = String.format(
                "\n\t%-5s %-20s %-20s %-50s %-10s %-10s", "ID", "NIF", "NOMBRE", "APELLIDOS", "GENERO", "TUTOR");

        String[] options = {
                "SIGUIENTE", "ANTERIOR", "SIGUIENTE PÁGINA", "ANTERIOR PÁGINA", "SELECCIONAR", "SALIR"
        };

        InlineMenu inlineMenu = new InlineMenu(options, "\t", 1);

        // CASTING LIST
        ArrayList<Object> items = new ArrayList<Object>(list);

        final SelectionMenu selectionMenu = new SelectionMenu(
                "\t", items, header) {
            @Override
            protected void showItem(Object o, boolean selected) {

                PersonTeacher personTeacher = (PersonTeacher) o;
                Person p = personTeacher.getPerson();

                String format = String.format(
                        "%-5s %-20s %-20s %-50s %-10s %-10s", p.getId(), p.getNif(), p.getName(),
                        p.getFirstLastname() + " " + p.getSecondLastname(), p.getGender(), personTeacher.isTeacher());

                if (selected) {
                    Encapsulate.encapsulateString(format, "\t");
                } else {
                    System.out.printf("\n\t%s", format);
                }

            }
        };

        Parser orderParser = new Parser() {
            @Override
            protected int callBack(String command) throws Exception {
                switch (Integer.parseInt(command)) {
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
                        if (!selection) {
                            throw new Exception("NOT ENABLED");
                        } else {
                            selected[0] = (PersonTeacher) selectionMenu.select();
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
                this.console.getErrorLog(), inlineMenu, orderParser, this.console.getReader(), "\n\t> ",
                selectionMenu) {
            @Override
            protected void outsideLoop() {

            }
        };

        menu.show();

        return selected[0];
    }

    public void viewInDepartment(Department department) {
        Person[] persons = this.console.getService().getPersonUnit().getPersonsByDepartment(department);
        ArrayList<PersonTeacher> personTeachers = this.getPersonsAsTeachers(persons);
        for (PersonTeacher personTeacher : personTeachers) {
            // This teachers belongs to the department
            personTeacher.setTeacher(true);
        }
        this.view(personTeachers, false);
    }

    @Override
    public void create() {

        String[] messages = {
                "NIF",
                "Nombre",
                "Primer apellido",
                "Segundo apellido",
                "Fecha de nacimiento",
                "Género",
                "Teléfono"
        };

        String[] validation = {
                "^[A-Z0-9 ]*$",
                "^[a-zA-Z0-9 ]*$",
                "^[a-zA-Z0-9 ]*$",
                "^[a-zA-Z0-9 ]*$",
                "^[a-zA-Z0-9-]*$",
                "[male | female]",
                "^[0-9]*$",
        };

        SequentialMenu menu = new SequentialMenu(messages, this.console.getReader(), "", this.console.getErrorLog(),
                validation);
        menu.show();

        ArrayList<String> values = menu.getOutput();

        String nif = values.get(0);
        String name = values.get(1);
        String firstLastName = values.get(2);
        String secondLastName = values.get(3);
        String birthDate = values.get(4);
        String gender = values.get(5);
        String telephone = values.get(6);

        Person p = new Person();
        p.setNif(nif);
        p.setName(name);
        p.setFirstLastname(firstLastName);
        p.setSecondLastname(secondLastName);
        p.setBirthdate(java.sql.Date.valueOf(birthDate));
        p.setGender(gender);
        p.setTelephone(telephone);

        try {
            this.console.getService().getPersonUnit().save(p);
        } catch (Exception e) {
            System.out.println("\nNo se ha podido crear la persona.");
            return;
        }

        System.out.println("\nPersona creada con éxito.");
    }

    public void createTeacher() {

        // Select the person

        Person[] persons = this.console.getService().getPersonUnit().getAll();

        ArrayList<PersonTeacher> personTeachers = new ArrayList<PersonTeacher>();

        for (Person p : persons) {
            PersonTeacher personTeacher = new PersonTeacher();
            personTeacher.setPerson(p);
            personTeachers.add(personTeacher);
        }

        PersonTeacher p = view(
            personTeachers,
            true
        );

        if (p == null) {
            return;
        }        

        // Select the department
        Department[] departments = this.console.getService(). getDepartmentUnit().getAll();

        Department dep = this.console.getDepartmentMenu().view(
            new ArrayList<Department>(Arrays.asList(departments)),
            true
        );

        Teacher teacher = new Teacher();
        teacher.setPersonId(p.getPerson().getId());
        teacher.setDepartmentId(dep.getId());

        try {
            this.console.getService().getTeacherUnit().save(teacher);
        } catch (Exception e) {
            System.out.println("\nNo se ha podido crear el profesor.");
            return;
        }

        System.out.println("\nProfesor creado con éxito.");
    }

    @Override
    public void search() {

        String[] options = new String[] {
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
                true);

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
                this.console.getErrorLog(),
                menu,
                parser,
                this.console.getReader(),
                "> ") {
            protected void outsideLoop() {
            };

            protected void loopBlock() {
                this.optionMenu.show();
            };
        };

        interactiveMenu.show();
    }

    @Override
    public void remove() {

        // Get all persons
        Person[] persons = this.console.getService()
            .getPersonUnit()
            .getAll();

        ArrayList<PersonTeacher> personTeachers = this.getPersonsAsTeachers(persons);
        
        PersonTeacher person = this.view(personTeachers, true);
        Person p = person.getPerson();

        try {
            this.console.getService().getPersonUnit().delete(p);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("\nNo se ha podido eliminar la persona.");
            return;
        }

        System.out.println("\nPersona eliminada con éxito.");
    }

    @Override
    public void modify() {

        // Get all persons
        Person[] persons = this.console.getService()
            .getPersonUnit()
            .getAll();

        ArrayList<PersonTeacher> personTeachers = new ArrayList<PersonTeacher>();

        for (Person p : persons) {
            PersonTeacher personTeacher = new PersonTeacher();
            personTeacher.setPerson(p);
            personTeachers.add(
                personTeacher
            );
        }
        
        PersonTeacher person = this.view(personTeachers, true);
        final Person p = person.getPerson();

        String[] options = {
                "NIF",
                "Nombre",
                "Primer apellido",
                "Segundo apellido",
                "Fecha de nacimiento",
                "Género",
                "Teléfono",
                "Ejecutar modificación",
                "Salir"
        };

        final OptionsMenu menu = new OptionMenu(
                options,
                "",
                String.format(
                    "%s %s - MODIFICAR",
                    p.getName(),
                    p.getFirstLastname()
                ),
                "%s",
                1,
                true);

        Parser parser = new Parser() {
            @Override
            protected int callBack(String arg0) throws Exception {
                switch (Integer.parseInt(arg0)) {
                    case 1:
                        String nif = getValue(
                            "NIF",
                            "^[A-Z0-9 ]*$"
                        );
                        p.setNif(nif);
                        break;
                    case 2:
                        String name = getValue(
                            "Nombre",
                            "^[a-zA-Z0-9 ]*$"
                        );
                        p.setName(name);
                        break;
                    case 3:
                        String firstLastName = getValue(
                            "Primer apellido",
                            "^[a-zA-Z0-9 ]*$"
                        );
                        p.setFirstLastname(firstLastName);
                        break;
                    case 4:
                        String secondLastName = getValue(
                            "Segundo apellido",
                            "^[a-zA-Z0-9 ]*$"
                        );
                        p.setSecondLastname(secondLastName);
                        break;
                    case 5:
                        String birthDate = getValue(
                            "Fecha de nacimiento",
                            "^[0-9-]*$"
                        );
                        p.setBirthdate(java.sql.Date.valueOf(birthDate));
                        break;
                    case 6:
                        String gender = getValue(
                            "Género",
                            "^[a-zA-Z0-9 ]*$"
                        );
                        p.setGender(gender);
                        break;
                    case 7:
                        String telephone = getValue(
                            "Teléfono",
                            "^[0-9]*$"
                        );
                        p.setTelephone(telephone);
                        break;
                    case 8:
                        try {
                            console.getService().getPersonUnit().update(p);
                        } catch (Exception e) {
                            System.out.println("\nNo se ha podido modificar la persona.");
                            return -1;
                        }
                        System.out.println("\nPersona modificada con éxito.");
                        return -1;
                    case 9:
                        return -1;
                }
                return 0;
            }
        };

        DefaultInteractiveMenu interactiveMenu = new DefaultInteractiveMenu(
                this.console.getErrorLog(),
                menu,
                parser,
                this.console.getReader(),
                "> ") {
            protected void outsideLoop() {
            };

            protected void loopBlock() {
                this.optionMenu.show();
            };
        };

        interactiveMenu.show();
    }

    private String getValue(String option, String val) {

        String[] messages = {
            option
        };

        String[] validation = {
            val
        };

        SequentialMenu menu = new SequentialMenu(messages, this.console.getReader(), "", this.console.getErrorLog(), validation);
        menu.show();

        ArrayList<String> values = menu.getOutput();

        return values.get(0);
    }

    private void searchPersonById() {

        String[] messages = {
                "Identificador"
        };

        String[] validation = {
                "^[0-9]*$",
        };

        SequentialMenu menu = new SequentialMenu(messages, this.console.getReader(), "", this.console.getErrorLog(), validation);
        menu.show();

        ArrayList<String> values = menu.getOutput();

        Integer id = Integer.parseInt(values.get(0));

        Person p = this.console.getService().getPersonUnit().get(id);

        if (p == null) {
            System.out.println("\nNo se ha encontrado la persona.");
            return;
        }

        Teacher[] t = this.console.getService().getTeacherUnit().getByPersonIds(
                new Integer[] { id });
        ArrayList<Person> persons = new ArrayList<Person>();
        persons.add(p);

        ArrayList<Teacher> teachers = new ArrayList<Teacher>();
        teachers.addAll(Arrays.asList(t));

        ArrayList<PersonTeacher> personTeachers = new ArrayList<PersonTeacher>();

        for (Person person : persons) {
            PersonTeacher personTeacher = new PersonTeacher();
            personTeacher.setPerson(person);
            for (Teacher teacher : teachers) {
                if (teacher.getPersonId() == person.getId()) {
                    personTeacher.setTeacher(true);
                    break;
                }
            }
            personTeachers.add(
                personTeacher
            );
        }

        try {
            this.view(personTeachers, false);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void searchPersonByName() {

        String[] messages = {
                "Nombre"
        };

        String[] validation = {
                "^[a-zA-Z0-9 ]*$",
        };

        SequentialMenu menu = new SequentialMenu(messages, this.console.getReader(), "", this.console.getErrorLog(), validation);
        menu.show();

        ArrayList<String> values = menu.getOutput();

        String name = values.get(0);

        Person[] p = this.console.getService().getPersonUnit().getByName(name);

        if (p == null || p.length == 0) {
            System.out.println("\nNo se han encontrado la personas.");
            return;
        }

        // Set all person id to the teacher getter
        ArrayList<Integer> ids = new ArrayList<Integer>();
        for (Person person : p) {
            ids.add(person.getId());
        }
        Teacher[] t = this.console.getService().getTeacherUnit().getByPersonIds(
                ids.toArray(new Integer[ids.size()]));

        ArrayList<Person> persons = new ArrayList<Person>();
        persons.addAll(Arrays.asList(p));

        ArrayList<Teacher> teachers = new ArrayList<Teacher>();
        teachers.addAll(Arrays.asList(t));

        ArrayList<PersonTeacher> personTeachers = new ArrayList<PersonTeacher>();

        for (Person person : persons) {
            PersonTeacher personTeacher = new PersonTeacher();
            personTeacher.setPerson(person);
            for (Teacher teacher : teachers) {
                if (teacher.getPersonId() == person.getId()) {
                    personTeacher.setTeacher(true);
                    break;
                }
            }
            personTeachers.add(
                personTeacher
            );
        }

        try {
            this.view(personTeachers, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void searchPersonByFirstLastName() {

        String[] messages = {
                "Primer apellido"
        };

        String[] validation = {
                "^[a-zA-Z0-9 ]*$",
        };

        SequentialMenu menu = new SequentialMenu(messages, this.console.getReader(), "", this.console.getErrorLog(), validation);
        menu.show();

        ArrayList<String> values = menu.getOutput();

        String name = values.get(0);

        Person[] p = this.console.getService().getPersonUnit().getByFirstLastName(name);

        if (p == null || p.length == 0) {
            System.out.println("\nNo se han encontrado la personas.");
            return;
        }

        // Set all person id to the teacher getter
        ArrayList<Integer> ids = new ArrayList<Integer>();
        for (Person person : p) {
            ids.add(person.getId());
        }
        Teacher[] t = this.console.getService().getTeacherUnit().getByPersonIds(
                ids.toArray(new Integer[ids.size()]));

        ArrayList<Person> persons = new ArrayList<Person>();
        persons.addAll(Arrays.asList(p));

        ArrayList<Teacher> teachers = new ArrayList<Teacher>();
        teachers.addAll(Arrays.asList(t));

        ArrayList<PersonTeacher> personTeachers = new ArrayList<PersonTeacher>();

        for (Person person : persons) {
            PersonTeacher personTeacher = new PersonTeacher();
            personTeacher.setPerson(person);
            for (Teacher teacher : teachers) {
                if (teacher.getPersonId() == person.getId()) {
                    personTeacher.setTeacher(true);
                    break;
                }
            }
            personTeachers.add(
                personTeacher
            );
        }

        try {
            this.view(personTeachers, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void searchPersonBySecondLastName() {

        String[] messages = {
                "Segundo apellido"
        };

        String[] validation = {
                "^[a-zA-Z0-9 ]*$",
        };

        SequentialMenu menu = new SequentialMenu(messages, this.console.getReader(), "", this.console.getErrorLog(), validation);
        menu.show();

        ArrayList<String> values = menu.getOutput();

        String name = values.get(0);

        Person[] p = this.console.getService().getPersonUnit().getBySecondLastName(name);

        if (p == null || p.length == 0) {
            System.out.println("\nNo se han encontrado la personas.");
            return;
        }

        // Set all person id to the teacher getter
        ArrayList<Integer> ids = new ArrayList<Integer>();
        for (Person person : p) {
            ids.add(person.getId());
        }
        Teacher[] t = this.console.getService().getTeacherUnit().getByPersonIds(
                ids.toArray(new Integer[ids.size()]));

        ArrayList<Person> persons = new ArrayList<Person>();
        persons.addAll(Arrays.asList(p));

        ArrayList<Teacher> teachers = new ArrayList<Teacher>();
        teachers.addAll(Arrays.asList(t));

        ArrayList<PersonTeacher> personTeachers = new ArrayList<PersonTeacher>();

        for (Person person : persons) {
            PersonTeacher personTeacher = new PersonTeacher();
            personTeacher.setPerson(person);
            for (Teacher teacher : teachers) {
                if (teacher.getPersonId() == person.getId()) {
                    personTeacher.setTeacher(true);
                    break;
                }
            }
            personTeachers.add(
                personTeacher
            );
        }

        try {
            this.view(personTeachers, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<PersonTeacher> getPersonsAsTeachers(Person[] persons) {
            ArrayList<PersonTeacher> personTeachers = new ArrayList<PersonTeacher>();
            for (Person person : persons) {
                PersonTeacher personTeacher = new PersonTeacher();
                personTeacher.setPerson(person);
                personTeachers.add(
                    personTeacher
                );
            }
            return personTeachers;
    }

}
