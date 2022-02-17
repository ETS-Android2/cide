package com.github.pomaretta.cide;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

import com.github.pomaretta.cide.domain.Method;
import com.github.pomaretta.cide.domain.Request;
import com.github.pomaretta.cide.domain.Response;

/**
 * @author Carlos Pomares <https://www.github.com/pomaretta>
 */
public class Server {

    private final HashMap<String, String> cache;
    private final ServerSocket socket;

    class ClientHandler extends Thread {

        private final Socket socket;
        private final Server server;

        private InputStream inputStream;
        private OutputStream outputStream;

        public ClientHandler(Socket socket, Server server) {
            this.socket = socket;
            this.server = server;
        }

        private void sendResponse(Response response) throws Exception {
            outputStream.write(response.toString().getBytes());
            outputStream.flush();
        }

        @Override
        public void run() {
            
            try {
                inputStream = socket.getInputStream();
                outputStream = socket.getOutputStream();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            while (socket.isConnected()) {
                try {

                    // Receive requests from client
                    if (inputStream.available() == 0) continue;

                    // Receive request
                    byte[] buffer = new byte[1024];
                    int bytesRead = inputStream.read(buffer);

                    // Parse request
                    String requestString = new String(buffer, 0, bytesRead);

                    // Parse request
                    Request request = Request.parseRequest(requestString);

                    int status = 600;
                    String result = null;
                    try {
                        Object obj = this.server.operate(
                            request.getMethod(),
                            request.getKey(),
                            request.getValue()
                        );
                        result = obj.toString();
                    } catch (Exception e) {
                        status = 400;
                        result = e.getMessage();
                    }
                    
                    Response response = new Response(status, result);
                    this.sendResponse(response);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }

        }

    }

    public Server() throws Exception {
        this.cache = new HashMap<>();
        this.socket = new ServerSocket(56001);
    }

    private synchronized Object operate(Method method, String key, String value) {
        switch (method) {
            case PO:
                cache.put(key, value);
                return cache.containsKey(key);
            case CAR:
                return cache.get(key);
            case LOS:
                cache.replace(key,value);
                return true;
            case MARES:
                cache.clear();
                throw new IllegalArgumentException("The key is to know your code, make your own things.");
            default:
                throw new IllegalArgumentException("Method not supported");
        }
    }

    public void start() {

        while (true) {
            try {
                Socket client = this.socket.accept();
                new ClientHandler(client, this).start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) throws Exception {
        Server server = new Server();
        server.start();
    }
}
