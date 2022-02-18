package com.github.pomaretta.cide;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

import com.github.pomaretta.cide.console.ServerConsole;
import com.github.pomaretta.cide.domain.Method;
import com.github.pomaretta.cide.domain.Request;
import com.github.pomaretta.cide.domain.Response;
/**
 * @author Carlos Pomares <https://www.github.com/pomaretta>
 */
public class Server {

    private final String[] SPECIAL_WORDS = {
        "GET_ALL"
    };

    private final HashMap<String, String> cache;
    private final ServerSocket socket;

    // LOGGING AND ACCESS
    private final HashMap<String, Boolean> access;

    class ClientHandler extends Thread {

        private final Socket socket;
        private final Server server;
        private final String clientId;

        private InputStream inputStream;
        private OutputStream outputStream;

        public ClientHandler(Socket socket, Server server, String clientId) {
            this.socket = socket;
            this.server = server;
            this.clientId = clientId;
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

            while (!socket.isClosed()) {

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

                    // See if request is a SHUTDOWN request
                    if (request.getMethod().equals(Method.SHUTDOWN)) {
                        Response response = new Response(200, "OK");
                        sendResponse(response);
                        socket.close();
                        continue;
                    }

                    int status = 200;
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
                    ex.printStackTrace();
                }
            }

            // Client disconnected
            this.server.access.replace(this.clientId, false);

        }

    }

    public Server() throws Exception {
        this.cache = new HashMap<>();
        this.access = new HashMap<>();
        this.socket = new ServerSocket(56001);
    }

    private synchronized Object operate(Method method, String key, String value) {
        switch (method) {
            case PUT:
                if (this.isInSpecialWords(key)) throw new IllegalArgumentException("Key is a reserved word");
                if (cache.containsKey(key)) throw new IllegalArgumentException("Key already exists");
                cache.put(key, value);
                return cache.containsKey(key);
            case GET:
                if (key.equals("GET_ALL")) {
                    String map = "";
                    for (String k : cache.keySet()) {
                        map += k + "=" + cache.get(k) + "\n";
                    }
                    return map;
                }
                if (!cache.containsKey(key)) throw new RuntimeException("Key not found");
                return cache.get(key);
            case POST:
                if (this.isInSpecialWords(key)) throw new IllegalArgumentException("Key is a reserved word");
                if (!cache.containsKey(key)) throw new RuntimeException("Key not found");
                cache.replace(key,value);
                if (!cache.get(key).equals(value)) throw new RuntimeException("Value not replaced");
                return true;
            case DELETE:
                if (this.isInSpecialWords(key)) throw new IllegalArgumentException("Key is a reserved word");
                if (!cache.containsKey(key)) throw new RuntimeException("Key not found");
                cache.remove(key);
                return !cache.containsKey(key);
            default:
                throw new IllegalArgumentException("Method not supported");
        }
    }

    public boolean isInSpecialWords(String key) {
        for (String word : SPECIAL_WORDS) {
            if (key.equals(word)) return true;
        }
        return false;
    }

    public void start() {

        // Start console
        new Thread(new ServerConsole(this)).start();

        while (true) {
            try {
                Socket client = this.socket.accept();

                // ClientID
                String clientIp = client.getInetAddress().getHostAddress();
                String clientPort = String.valueOf(client.getPort());
                String clientId = clientIp + ":" + clientPort;

                if (access.containsKey(clientId) && !access.get(clientId)) {
                    access.replace(clientId, true);
                    continue;
                } else {
                    access.put(clientId, true);
                }

                new ClientHandler(client, this, clientId).start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public HashMap<String, Boolean> getAccess() {
        return access;
    }

    public HashMap<String, String> getCache() {
        return cache;
    }

    public static void main(String[] args) throws Exception {
        Server server = new Server();
        server.start();
    }
}
