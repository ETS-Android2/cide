package com.github.pomaretta.cide;

import com.github.pomaretta.cide.console.Console;

/**
 * @author Carlos Pomares <https://www.github.com/pomaretta>
 */
public class Client {

	private final Console console;

	public Client() {
		this.console = new Console();
	}

	/**
	 * 
	 * Wrapper para el inicio de la consola, por si alguna funcionalidad
	 * necesita ser inicializada antes que la consola.
	 * 
	 * @param args Los diferentes argumentos que puedan ser pasados por el ususario.
	 */
	public void start(String[] args) {
		// Iniciar la consola
		this.console.start();
	}

	public static void main(String[] args) throws Exception {
		Client client = new Client();
		client.start(args);
	}
}
