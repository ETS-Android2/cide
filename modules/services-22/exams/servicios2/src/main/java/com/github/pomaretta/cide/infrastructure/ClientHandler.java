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

    private final Socket socket;
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

        while (!socket.isClosed() && !this.service.getSocket().isClosed()) {

            try {

                // Receive requests from client
                if (inputStream.available() == 0) {
                    // If we don't have any data, we must sleep the thread x milliseconds
                    // else we will be constantly looping and consuming CPU
                    Thread.sleep(100);
                    continue;
                }

                // Receive request
                byte[] buffer = new byte[1024];
                int bytesRead = inputStream.read(buffer);

                // Parse request
                String requestString = new String(buffer, 0, bytesRead);

                // Parse request
                Request request = Request.decode(requestString);

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
                    Object obj = this.service.operate(request);
                    result = obj.toString();
                } catch (Exception e) {
                    status = 400;
                    result = e.getMessage();
                }

                Response response = new Response(status, result);

                this.accessLog.add(String.format(
                        "%s | %s",
                        request.toString(),
                        response.toString()));

                this.sendResponse(response);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        // Client disconnected
        this.service.getAccess().replace(this.clientId, false);

    }

}
