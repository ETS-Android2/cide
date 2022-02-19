package com.github.pomaretta.cide.infrastructure;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;

import com.github.pomaretta.cide.domain.Method;
import com.github.pomaretta.cide.domain.Request;
import com.github.pomaretta.cide.domain.Response;

public class ClientHandler extends Thread {

	/**
	 * El socket conectado al cliente.
	 */
	private final Socket socket;
	/**
	 * El servicio para poder acceder a las funcionalidades de Cache.
	 */
	private final CacheService service;
	private final String clientId;

	private InputStream inputStream;
	private OutputStream outputStream;
	private ArrayList<String> accessLog;

	public ClientHandler(Socket socket, CacheService cacheService, String clientId) {
		this.socket = socket;
		this.service = cacheService;
		this.clientId = clientId;
	}

	/**
	 * Permite enviar un objeto Response codificado a traves del socket.
	 * 
	 * @param response El objeto Response que se va a enviar.
	 * @throws Exception Si hay algún problema con la comunicación.
	 */
	private void sendResponse(Response response) throws Exception {
		outputStream.write(response.encode().getBytes());
		outputStream.flush();
	}

	@Override
	public void run() {

		try {
			inputStream = socket.getInputStream();
			outputStream = socket.getOutputStream();
			this.accessLog = service.getAccessLog().get(this.clientId);
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		while (!socket.isClosed() && !this.service.getSocket().isClosed() && !socket.isInputShutdown()) {

			try {

				// Comprobar si hay alguna petición pendiente.
				if (inputStream.available() == 0) {
					// En caso de no haber datos, hacemos un sleep de 100ms.
					// Esto permite que el hilo no consuma mucha CPU.
					Thread.sleep(100);
					continue;
				}

				// Leemos la petición a traves del socket.
				byte[] buffer = new byte[1024];
				int bytesRead = inputStream.read(buffer);

				// Obtenemos los bytes en un objeto String.
				String requestString = new String(buffer, 0, bytesRead);

				// Obtenemos la petición a traves de un objeto Request.
				Request request = Request.decode(requestString);

				// En caso de ser una petición de tipo SHUTDOWN, cerramos la comunicación.
				if (request.getMethod().equals(Method.SHUTDOWN)) {
					// Se envía un OK al cliente.
					Response response = new Response(200, "OK");
					sendResponse(response);
					// Se cierra el socket.
					socket.close();
					continue;
				}

				// Por defecto se tratan como peticiones correctas.
				int status = 200;//
				String result = null;

				try {
					// Realizamos la petición al servicio y obtenemos el resultado.
					Object obj = this.service.operate(request);
					// Obtenemos el resultado en formato de String.
					result = obj.toString();
				} catch (Exception e) {
					// En caso de ocurrir una excepción, se trata como un error.
					// Se establece el código de error 400.
					status = 400;
					result = e.getMessage();
				}

				// Se crea un objeto Response con el resultado y el código de estado.
				Response response = new Response(status, result);

				// Agregamos la petición y respuesta al log de acceso.
				this.accessLog.add(String.format(
						"%s | %s",
						request.toString(),
						response.toString()));

				// Enviamos la respuesta al servidor
				this.sendResponse(response);
			} catch (Exception ex) {
				// If there is an error, we must close the socket
			}

		}

		// Una vez acaba la ejecución del bucle, establecemos como desconectado
		// al cliente.
		this.service.getAccess().replace(this.clientId, false);

	}

}
