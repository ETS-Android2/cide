import java.util.PriorityQueue;
import java.util.concurrent.Semaphore;

public class Cima {
 
    private Semaphore semaphore;
    // Lista de escaladores a recoger
    private PriorityQueue<Integer> escaladoresPendientes;

    public Cima() {
        this.semaphore = new Semaphore(1);
        this.escaladoresPendientes = new PriorityQueue<Integer>();
    }

    /**
     * 
     * Obtenemos cuantos escaladores quedan pendientes.
     * 
     * @return Cantidad de escaladores pendientes.
     */
    public synchronized Integer getEscaladoresPendientes() {
        return escaladoresPendientes.size();
    }

    /**
     * 
     * Obtenemos si quedan escaladores en la cima.
     * 
     * @return True si quedan escaladores en la cima.
     */
    public synchronized boolean isEscaladoresPendientes() {
        return !escaladoresPendientes.isEmpty();
    }
    
    /**
     * 
     * Añadimos un escalador a la cima.
     * 
     * @param escalador El escalador a añadir.
     */
    public void nuevoEscalador(Escalador escalador) {
        try {
            semaphore.acquire();
            // Un escalador representa a una persona.
            escaladoresPendientes.add(1);
            semaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 
     * Sacamos un escalador de la cima.
     * 
     * @return El escalador que se ha sacado de la cima.
     */
    public void recogerEscaladores(Helicoptero helicoptero) throws Exception{
        if (isEscaladoresPendientes()) {
            // Obtenemos el paso para poder sacar un escalador.
            semaphore.acquire();
            // Avisamos a base que recogemos un escalador.
            System.out.println(
                String.format(
                    "Helicoptero [%d]: Recogiendo %d pasajeros",
                    helicoptero.getHelicopteryId(),
                    helicoptero.getCapacidad()
                )
            );
            // Por cada pasajero que pueda recoger el helicoptero. Recogemos uno.
            for (int i = 0; i < helicoptero.getCapacidad(); i++) {
                // Sacamos un escalador de la cima.
                Integer es = escaladoresPendientes.poll();
                // Si no hay más escaladores en la cima, salimos del bucle.
                if (es == null) {
                    throw new Exception("No hay más escaladores");
                }
                // Si ha podido recoger un escalador, incrementamos la cantidad de pasajeros.
                helicoptero.recogerEscalador();
            }
            // Simulamos que tarda entre 15 y 20 segundos en recoger los pasajeros.
            Thread.sleep((int) (Math.random() * 20000) + 15000);
            // Permitimos el paso al siguiente helicóptero.
            semaphore.release();
        }
    }

}