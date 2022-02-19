package com.github.pomaretta.cide;

import com.github.pomaretta.cide.infrastructure.CacheService;

/**
 * @author Carlos Pomares <https://www.github.com/pomaretta>
 */
public class Server {

	// Puerto por defecto del servicio 56001
	private int port = 56001;
	private final CacheService cacheService;

	public Server() throws Exception {
		cacheService = new CacheService(port);
	}

	public Server(int port) throws Exception {
		cacheService = new CacheService(port);
	}

	public void start() throws Exception {
		cacheService.start();
	}

	public static void main(String[] args) throws Exception {

		int port = 56001;
		if (args.length > 0) {
			port = Integer.parseInt(args[0]);
		}

		Server server = new Server(port);
		server.start();
	}

}
