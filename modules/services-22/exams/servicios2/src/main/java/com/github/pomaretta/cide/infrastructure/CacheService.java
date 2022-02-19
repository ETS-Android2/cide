package com.github.pomaretta.cide.infrastructure;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

import com.github.pomaretta.cide.console.ServerConsole;
import com.github.pomaretta.cide.domain.Request;
import com.github.pomaretta.cide.service.Cache;
import com.github.pomaretta.cide.service.Service;

public class CacheService implements Cache, Service {
    
    private final HashMap<String, String> cache;
    private ServerSocket socket;

    // LOGGING AND ACCESS
    private final HashMap<String, Boolean> access;
    private final HashMap<String, ArrayList<String>> accessLog;

    public CacheService() throws Exception {
        this.cache = new HashMap<>();
        this.access = new HashMap<>();
        this.accessLog = new HashMap<>();
        this.socket = new ServerSocket(56001);
    }

    public CacheService(int port) throws Exception {
        this();
        this.socket = new ServerSocket(port);
    }

    @Override
    public synchronized Object operate(Request request) throws Exception {
        switch (request.getMethod()) {
            case PUT:
                this.put(request.getKey(), request.getValue());
                return cache.containsKey(request.getKey());
            case GET:
                return this.get(request.getKey());
            case GET_ALL:
                return this.getAllWrapper();
            case POST:
                this.replace(request.getKey(), request.getValue());
                return true;
            case DELETE:
                this.remove(request.getKey());
                return !cache.containsKey(request.getValue());
            default:
                throw new IllegalArgumentException("Method not supported");
        }
    }

    @Override
    public void put(String key, String value) throws Exception {
        if (cache.containsKey(key)) throw new IllegalArgumentException("Key already exists");
        cache.put(key, value);
    }

    @Override
    public String get(String key) throws Exception {
        if (!cache.containsKey(key)) throw new RuntimeException("Key not found");
        return cache.get(key);
    }

    @Override
    public void replace(String key, String value) throws Exception {
        if (!cache.containsKey(key)) throw new RuntimeException("Key not found");
        cache.replace(key,value);
        if (!cache.get(key).equals(value)) throw new RuntimeException("Value not replaced");
    }

    @Override
    public void remove(String key) throws Exception {
        if (!cache.containsKey(key)) throw new RuntimeException("Key not found");
        cache.remove(key);
    }

    @Override
    public HashMap<String, String> getAll() throws Exception {
        return this.cache;
    }

    public String getAllWrapper() throws Exception {
        String map = "";
        HashMap<String, String> all = this.getAll();
        for (String k : all.keySet()) {
            map += k + "=" + all.get(k) + "\n";
        }
        return map;
    }

    public void start() {

        // Start console
        new Thread(new ServerConsole(this)).start();

        while (!this.socket.isClosed()) {
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
                    accessLog.put(clientId, new ArrayList<String>());
                }

                new ClientHandler(client, this, clientId).start();
            } catch (Exception e) {
                // Server closed
            }
        }

    }

    public void close() throws Exception {
        this.socket.close();
    }

    public HashMap<String, Boolean> getAccess() {
        return access;
    }

    public HashMap<String, String> getCache() {
        return cache;
    }

    public HashMap<String, ArrayList<String>> getAccessLog() {
        return accessLog;
    }

    public ServerSocket getSocket() {
        return socket;
    }

}
