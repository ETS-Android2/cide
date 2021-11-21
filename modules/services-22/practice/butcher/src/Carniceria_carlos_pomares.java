import java.util.concurrent.ExecutorService;
import java.util.concurrent.Semaphore;

public class Carniceria_carlos_pomares {

    public void pedirPedido(int numeroCliente) {
        try {
            System.out.println("El cliente " + numeroCliente + " ha solicitado un pedido");
            // Random sleep between 1 and 10 seconds
            Thread.sleep((long) (Math.random() * 10000) + 1000);
            System.out.println("El cliente " + numeroCliente + " ha recibido su pedido");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        
        Carniceria_carlos_pomares carniceria = new Carniceria_carlos_pomares();
        ExecutorService exec = java.util.concurrent.Executors.newFixedThreadPool(5);

        // 20 Clientes
        for (int i = 0; i < 20; i++) {
            exec.execute(new Cliente(i, carniceria));
        }
        exec.shutdown();

    }
}

class Cliente implements Runnable {
 
    private int id;
    private Carniceria_carlos_pomares carniceria;
    
    // 5 dependientes
    private Semaphore semaforo = new Semaphore(5);

    public Cliente(int id, Carniceria_carlos_pomares carniceria) {
        this.id = id;
        this.carniceria = carniceria;
    }

    @Override
    public void run() {
        try {
            // Esperar a que se libere un dependiente
            semaforo.acquire();
            // Pedir pedido
            carniceria.pedirPedido(id);
            // Liberar dependiente
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        semaforo.release();
    }

}