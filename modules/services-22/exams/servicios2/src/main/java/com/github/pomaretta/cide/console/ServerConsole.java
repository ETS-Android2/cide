package com.github.pomaretta.cide.console;

import java.util.ArrayList;
import java.util.HashMap;

import com.github.pomaretta.cide.infrastructure.CacheService;
import com.github.pomaretta.termux.Console.DefaultConsole;
import com.github.pomaretta.termux.Menu.OptionsMenu;
import com.github.pomaretta.termux.Menu.SelectionInteractiveMenu;
import com.github.pomaretta.termux.Menu.SelectionMenu;
import com.github.pomaretta.termux.Util.Encapsulate;
import com.github.pomaretta.termux.Menu.DefaultInteractiveMenu;
import com.github.pomaretta.termux.Menu.InlineMenu;
import com.github.pomaretta.termux.Menu.OptionMenu;

public class ServerConsole extends DefaultConsole implements Runnable {

	private final CacheService service;

	public ServerConsole(CacheService service) {
		this.service = service;
	}

	@Override
	protected void main() {

		String[] options = new String[] {
				"Ver los valores almacenados",
				"Ver los accesos al servidor",
				"Ver los logs de un cliente",
				"Apagar el servidor",
		};

		final OptionsMenu menu = new OptionMenu(
				options,
				"",
				"Server - " + this.service.getPort(),
				"%s",
				1,
				true);

		Parser parser = new Parser() {
			@Override
			protected int callBack(String arg0) throws Exception {
				switch (Integer.parseInt(arg0)) {
					case 1:
						viewValues();
						break;
					case 2:
						viewClients();
						break;
					case 3:
						viewLogs();
						break;
					case 4:
						service.close();
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
				"> ") {
			protected void outsideLoop() {
			};

			protected void loopBlock() {
				this.optionMenu.show();
			};
		};

		interactiveMenu.show();
	}

	private void viewValues() {

		final HashMap<String, String> snapshot = this.service.getCache();

		String header = String.format(
				"\n\t%-25s %-50s", "CLAVE", "VALOR");

		String[] options = {
				"SIGUIENTE", "ANTERIOR", "SIGUIENTE PÁGINA", "ANTERIOR PÁGINA", "SALIR"
		};

		InlineMenu inlineMenu = new InlineMenu(options, "\t", 1);

		final SelectionMenu selectionMenu = new SelectionMenu(
				"\t", new ArrayList<Object>(snapshot.keySet()), header) {
			@Override
			protected void showItem(Object o, boolean selected) {

				String k = (String) o;
				String format = String.format(
						"%-25s %-50s",
						k,
						snapshot.get(k));

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
						return -1;
				}
				return 0;
			}
		};

		DefaultInteractiveMenu menu = new SelectionInteractiveMenu(
				this.errorLog, inlineMenu, orderParser, reader, "\n\t> ", selectionMenu) {
			@Override
			protected void outsideLoop() {
			}
		};

		menu.show();
	}

	private String viewClients() {

		final String[] selectedClientId = { "" };
		final HashMap<String, Boolean> snapshot = this.service.getAccess();

		String header = String.format(
				"\n\t%-25s %-50s", "CLIENT ID", "CONNECTED");

		String[] options = {
				"SIGUIENTE", "ANTERIOR", "SIGUIENTE PÁGINA", "ANTERIOR PÁGINA", "SELECCIONAR", "SALIR"
		};

		InlineMenu inlineMenu = new InlineMenu(options, "\t", 1);

		final SelectionMenu selectionMenu = new SelectionMenu(
				"\t", new ArrayList<Object>(snapshot.keySet()), header) {
			@Override
			protected void showItem(Object o, boolean selected) {

				String k = (String) o;
				String format = String.format(
						"%-25s %-50s",
						k,
						String.valueOf(snapshot.get(k)));

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
						selectedClientId[0] = (String) selectionMenu.select();
						return -1;
					case 6:
						return -1;
				}
				return 0;
			}
		};

		DefaultInteractiveMenu menu = new SelectionInteractiveMenu(
				this.errorLog, inlineMenu, orderParser, reader, "\n\t> ", selectionMenu) {
			@Override
			protected void outsideLoop() {
			}
		};

		menu.show();

		return selectedClientId[0];
	}

	private void viewLogs() {

		String clientId = this.viewClients();

		if (clientId.isEmpty()) {
			return;
		}

		// Obtain client logs
		ArrayList<String> logs = this.service.getAccessLog().get(clientId);

		if (logs.size() == 0)
			throw new RuntimeException("No hay logs para el cliente " + clientId);

		String header = String.format(
				"\n\t%-150s", "REQUEST");

		String[] options = {
				"SIGUIENTE", "ANTERIOR", "SIGUIENTE PÁGINA", "ANTERIOR PÁGINA", "SALIR"
		};

		InlineMenu inlineMenu = new InlineMenu(options, "\t", 1);

		final SelectionMenu selectionMenu = new SelectionMenu(
				"\t", new ArrayList<Object>(logs), header) {
			@Override
			protected void showItem(Object o, boolean selected) {

				String k = (String) o;
				String format = String.format(
						"%-150s",
						k);

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
						return -1;
				}
				return 0;
			}
		};

		DefaultInteractiveMenu menu = new SelectionInteractiveMenu(
				this.errorLog, inlineMenu, orderParser, reader, "\n\t> ", selectionMenu) {
			@Override
			protected void outsideLoop() {
			}
		};

		menu.show();
	}

	@Override
	public void run() {
		main();
	}

}
