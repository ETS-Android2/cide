package com.github.pomaretta.cide.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import com.github.pomaretta.cide.domain.Method;
import com.github.pomaretta.cide.domain.Request;
import com.github.pomaretta.cide.domain.Response;

public class CacheProtocol implements Cache {

    private final int SERVER_PORT;
    private final String SERVER_HOST;

    private Socket socket;
    private OutputStream outputStream;
    private InputStream inputStream;

    public CacheProtocol() {
        this.SERVER_PORT = 67023;
        this.SERVER_HOST = "myhost";
    }

    public CacheProtocol(String host, int port) {
        this.SERVER_HOST = host;
        this.SERVER_PORT = port;
    }

    @Override
    public void put(String key, String value) throws Exception {
        Request request = new Request(Method.PO, key, value);
        Response response = sendRequest(request);
        if (response.getStatus() == 400) {
            throw new Exception(response.getValue());
        }
    }

    @Override
    public void replace(String key, String value) throws Exception {
        Request request = new Request(Method.LOS, key, value);
        Response response = sendRequest(request);
        if (response.getStatus() == 400) {
            throw new Exception(response.getValue());
        }
    }

    @Override
    public String get(String key) throws Exception {
        Request request = new Request(
            Method.CAR,
            key,
            null
        );
        Response response = this.sendRequest(request);
        if (response.getStatus() == 400) {
            throw new Exception(response.getValue());
        }
        return response.getValue();
    }

    @Override
    public void remove(String key) throws Exception {
        Request request = new Request(
            Method.MARES,
            key,
            null
        );
        Response response = this.sendRequest(request);
        if (response.getStatus() == 400) {
            throw new Exception(response.getValue());
        }
    }

    public boolean isConnected() {
        return this.socket != null && this.socket.isConnected();
    }

    public void close() throws IOException, IllegalStateException {
        if (!this.isConnected()) throw new IllegalStateException("Not connected");
        this.socket.close();
    }

    public void openSession() throws IOException {
        this.socket = new Socket(this.SERVER_HOST, this.SERVER_PORT);
        this.outputStream = this.socket.getOutputStream();
        this.inputStream = this.socket.getInputStream();
    }

    private Response sendRequest(
        Request request
    ) throws IOException {
        
        // Send request
        this.outputStream.write(request.toString().getBytes());
        this.outputStream.flush();

        // Wait until receive the response.
        byte[] response = new byte[1024];
        int read = this.inputStream.read(response);

        return Response.parseResponse(new String(response, 0, read));

    }

}
