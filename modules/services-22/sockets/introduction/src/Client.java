import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Client {
    
    public static void main(String[] args) throws Exception {
        
        Socket socket = new Socket();
        InetSocketAddress address = new InetSocketAddress("localhost", 9999);
        socket.connect(address);
        System.out.println("Connected to server");
        OutputStream os = socket.getOutputStream();
        os.write("Hello, World from Client!".getBytes());
        os.close();
        socket.close();

    }

}
