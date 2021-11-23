import java.util.PriorityQueue;

public class Cima {
 
    // Lista de escaladores a recoger
    private PriorityQueue<Integer> escaladoresPendientes;

    public Cima() {
        this.escaladoresPendientes = new PriorityQueue<Integer>();
    }

    /**
     * 
     * Obtenemos cuantos escaladores quedan pendientes.
     * 
     * @return Cantidad de escaladores pendientes.
     */
    public Integer getEscaladoresPendientes() {
        return escaladoresPendientes.size();
    }

    /**
     * 
     * Obtenemos si quedan escaladores en la cima.
     * 
     * @return True si quedan escaladores en la cima.
     */
    public boolean isEscaladoresPendientes() {
        return !escaladoresPendientes.isEmpty();
    }
    
    /**
     * 
     * Añadimos un escalador a la cima.
     * 
     * @param escalador El escalador a añadir.
     */
    public synchronized void nuevoEscalador(Escalador escalador) {
        escaladoresPendientes.add(1);
    }

    /**
     * 
     * Sacamos un escalador de la cima.
     * 
     * @return El escalador que se ha sacado de la cima.
     */
    public synchronized void recogerEscaladores(Helicoptero helicoptero) throws Exception{
        if (isEscaladoresPendientes()) {
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
        }
    }

}