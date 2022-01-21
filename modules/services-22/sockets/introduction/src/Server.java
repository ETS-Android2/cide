import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    
    public static void main(String[] args) throws Exception {
        
        ServerSocket serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress(9999));

        Socket socket = serverSocket.accept();
        System.out.println("Connected to client");

        InputStream is = socket.getInputStream();
        byte[] buffer = new byte[1024];

        int read = is.read(buffer);
        System.out.println("Received: " + new String(buffer, 0, read));
        
        socket.close();
        serverSocket.close();

    }

}
