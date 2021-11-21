import java.util.PriorityQueue;
import java.util.concurrent.Semaphore;

public class Cima {
 
    private Semaphore semaphore;
    private PriorityQueue<Integer> escaladoresPendientes;
    // private Integer escaladoresRecogidos;

    public Cima() {
        this.semaphore = new Semaphore(1);
        this.escaladoresPendientes = new PriorityQueue<Integer>();
    }

    public Integer getEscaladoresPendientes() {
        return escaladoresPendientes.size();
    }

    // public Integer getEscaladoresRecogidos() {
    //     return escaladoresRecogidos;
    // }

    public boolean isEscaladoresPendientes() {
        return !escaladoresPendientes.isEmpty();
    }
    
    public void nuevoEscalador(Escalador pasajero) {
        try {
            semaphore.acquire();
            escaladoresPendientes.add(1);
            semaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int recogerPasajeros(Helicoptero helicoptero) throws Exception{
        if (isEscaladoresPendientes()) {
            semaphore.acquire();   
            System.out.println(
                String.format(
                    "Helicoptero [%d]: Recogiendo %d pasajeros",
                    helicoptero.getHelicopteryId(),
                    helicoptero.getCapacidad()
                )
            );
            for (int i = 0; i < helicoptero.getCapacidad(); i++) {
                Integer es = escaladoresPendientes.poll();
                helicoptero.recogerEscalador();
                // escaladoresRecogidos++;
            }
            Thread.sleep((int) (Math.random() * 20000) + 15000);
            semaphore.release();
        }
        return helicoptero.getPasajeros();
    }

}