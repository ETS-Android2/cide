package com.github.pomaretta.cide.console;

import java.util.ArrayList;
import java.util.Arrays;

import com.github.pomaretta.cide.entity.Department;
import com.github.pomaretta.termux.Menu.DefaultInteractiveMenu;
import com.github.pomaretta.termux.Menu.InlineMenu;
import com.github.pomaretta.termux.Menu.OptionMenu;
import com.github.pomaretta.termux.Menu.OptionsMenu;
import com.github.pomaretta.termux.Menu.SelectionInteractiveMenu;
import com.github.pomaretta.termux.Menu.SelectionMenu;
import com.github.pomaretta.termux.Menu.SequentialMenu;
import com.github.pomaretta.termux.Util.Encapsulate;

public class DepartmentMenu implements Menu<Department> {

	private Console console;

	public DepartmentMenu(Console console) {
		this.console = console;
	}

	@Override
	public Department view(ArrayList<Department> list, final boolean selection) {

		final Department[] selected = new Department[1];

		String header = String.format(
				"\n\t%-5s %-20s", "ID", "NOMBRE");

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
				Department p = (Department) o;
				String format = String.format(
						"%-5s %-20s", p.getId(), p.getName());

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
				this.console.getErrorLog(), inlineMenu, orderParser, this.console.getReader(), "\n\t> ",
				selectionMenu) {
			@Override
			protected void outsideLoop() {

			}
		};

		menu.show();

		return selected[0];
	}

	@Override
	public void create() {

		String[] messages = {
				"Nombre"
		};

		String[] validation = {
				"^[a-zA-Z0-9 -_]*$",
		};

		SequentialMenu menu = new SequentialMenu(messages, this.console.getReader(), "", this.console.getErrorLog(),
				validation);
		menu.show();

		ArrayList<String> values = menu.getOutput();
		String name = values.get(0);

		Department p = new Department();
		p.setName(name);

		try {
			this.console.getService().getDepartmentUnit().save(p);
		} catch (Exception e) {
			System.out.println("\nNo se ha podido crear el departamento.");
			return;
		}

		System.out.println("\nDepartamento creada con éxito.");
	}

	@Override
	public void search() {
		String[] options = new String[] {
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
				true);

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
		Department[] departments = this.console.getService()
				.getDepartmentUnit()
				.getAll();

		ArrayList<Department> list = new ArrayList<Department>(
				Arrays.asList(departments));

		Department department = this.view(list, true);

		if (department == null) {
			System.out.println("\nNo se ha seleccionado ningún departamento.");
			return;
		}

		try {
			this.console.getService().getDepartmentUnit().delete(department);
		} catch (Exception e) {
			System.out.println("\nNo se ha podido eliminar el departamento.");
			return;
		}

		System.out.println("\nDepartamento eliminado con éxito.");
	}

	@Override
	public void modify() {

		// Get all persons
		Department[] departments = this.console.getService()
				.getDepartmentUnit()
				.getAll();

		ArrayList<Department> list = new ArrayList<Department>(
				Arrays.asList(departments));

		final Department department = this.view(list, true);

		if (department == null) {
			System.out.println("\nNo se ha seleccionado ningún departamento.");
			return;
		}

		String[] options = {
				"Nombre",
				"Ejecutar modificación",
				"Salir"
		};

		final OptionsMenu menu = new OptionMenu(
				options,
				"",
				String.format(
						"%s - MODIFICAR",
						department.getName()),
				"%s",
				1,
				true);

		Parser parser = new Parser() {
			@Override
			protected int callBack(String arg0) throws Exception {
				switch (Integer.parseInt(arg0)) {
					case 1:
						String name = getValue(
								"Nombre",
								"^[a-zA-Z0-9_- ]*$");
						department.setName(name);
						break;
					case 2:
						try {
							console.getService().getDepartmentUnit().update(department);
						} catch (Exception e) {
							System.out.println("\nNo se ha podido modificar la persona.");
							return -1;
						}
						System.out.println("\nPersona modificada con éxito.");
						return -1;
					case 3:
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

		SequentialMenu menu = new SequentialMenu(messages, this.console.getReader(), "", this.console.getErrorLog(),
				validation);
		menu.show();

		ArrayList<String> values = menu.getOutput();

		return values.get(0);
	}

	private void searchDepartmentById() throws Exception {

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

		Department d = this.console.getService().getDepartmentUnit().get(id);

		if (d == null)
			throw new Exception("No se encontro el departamento");

		ArrayList<Department> departments = new ArrayList<Department>();
		departments.add(d);

		try {
			Department dep = view(departments, true);
			if (dep != null) {
				this.console.getPersonMenu().viewInDepartment(dep);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void searchDepartmentByName() throws Exception {
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

		Department[] departments = this.console.getService().getDepartmentUnit().getByName(name);

		if (departments.length == 0)
			throw new Exception("No se encontraron departamentos con ese nombre");

		ArrayList<Department> departmentsList = new ArrayList<Department>();
		departmentsList.addAll(Arrays.asList(departments));

		try {
			Department dep = view(departmentsList, true);
			if (dep != null) {
				this.console.getPersonMenu().viewInDepartment(dep);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
