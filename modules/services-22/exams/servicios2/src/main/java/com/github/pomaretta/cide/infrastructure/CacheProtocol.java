package com.github.pomaretta.cide.infrastructure;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.HashMap;

import com.github.pomaretta.cide.domain.Method;
import com.github.pomaretta.cide.domain.Request;
import com.github.pomaretta.cide.domain.Response;
import com.github.pomaretta.cide.service.Cache;
import com.github.pomaretta.cide.service.Protocol;

public class CacheProtocol implements Cache, Protocol {

	private final int SERVER_PORT;
	private final String SERVER_HOST;

	private Socket socket;
	private OutputStream outputStream;
	private InputStream inputStream;

	/**
	 * Inicializa el protocolo con los valores por defecto.
	 */
	public CacheProtocol() {
		this.SERVER_PORT = 56001;
		this.SERVER_HOST = "localhost";
	}

	/**
	 * Inicializa el protocolo con los valores pasa como parámetros.
	 * 
	 * @param host El host del servidor.
	 * @param port El puerto del servidor.
	 */
	public CacheProtocol(String host, int port) {
		this.SERVER_HOST = host;
		this.SERVER_PORT = port;
	}

	@Override
	public void put(String key, String value) throws Exception {
		Request request = new Request(Method.PUT, key, value);
		Response response = send(request);
		if (response.getStatus() == 400) {
			throw new Exception(response.getValue());
		}
	}

	@Override
	public void replace(String key, String value) throws Exception {
		Request request = new Request(Method.POST, key, value);
		Response response = send(request);
		if (response.getStatus() == 400) {
			throw new Exception(response.getValue());
		}
	}

	@Override
	public String get(String key) throws Exception {
		Request request = new Request(
				Method.GET,
				key,
				null);
		Response response = this.send(request);
		if (response.getStatus() == 400) {
			throw new Exception(response.getValue());
		}
		return response.getValue();
	}

	@Override
	public HashMap<String, String> getAll() throws Exception {
		Request request = new Request(
				Method.GET_ALL,
				null);
		Response response = this.send(request);

		HashMap<String, String> map = new HashMap<>();
		String[] lines = response.getValue().split("\n");
		for (String line : lines) {
			String[] keyValue = line.split("=");
			map.put(keyValue[0], keyValue[1]);
		}

		return map;
	}

	@Override
	public void remove(String key) throws Exception {
		Request request = new Request(
				Method.DELETE,
				key,
				null);
		Response response = this.send(request);
		if (response.getStatus() == 400) {
			throw new Exception(response.getValue());
		}
	}

	public boolean isConnected() {
		return this.socket != null && !this.socket.isClosed();
	}

	public void close() {
		if (!this.isConnected())
			throw new IllegalStateException("Not connected");
		// Send close request
		Request request = new Request(
				Method.SHUTDOWN,
				null,
				null);
		try {
			this.send(request);
			this.socket.close();
		} catch (Exception e) {
			// Ignore
		}
	}

	public void openSession() throws IOException {
		if (this.isConnected())
			throw new IOException("Session already open");
		this.socket = new Socket(this.SERVER_HOST, this.SERVER_PORT);
		this.outputStream = this.socket.getOutputStream();
		this.inputStream = this.socket.getInputStream();
	}

	@Override
	public Response send(
			Request request) throws IOException {
		if (socket.isClosed())
			throw new IOException("Socket is closed");
		if (socket.isOutputShutdown())
			throw new IOException("Socket is output shutdown");
		if (socket.isInputShutdown())
			throw new IOException("Socket is input shutdown");

		// Enviamos la petición a través del OutputStream
		this.outputStream.write(request.encode().getBytes());
		this.outputStream.flush();

		// Esperamos a la respuesta
		byte[] response = new byte[1024];
		int read = this.inputStream.read(response);

		// A través del InputStream, leemos la respuesta y devolvemos un objeto
		// Response.
		return Response.decode(new String(response, 0, read));
	}

}
