public class Helicoptero extends Thread {
    
    private Integer id;
    private Integer capacidad;
    private Cima cima;
    // Los escaladores que se van a asignar a este helicoptero
    private Integer escaladores;
    
    /**
     * 
     * Un helicóptero se crea con un id, un numero de escaladores, una capacidad, pasandole una cima.
     * 
     * @param id id del helicóptero
     * @param capacidad capacidad del helicóptero
     * @param base cima a la que se asocia el helicóptero
     */
    public Helicoptero(Integer id, Integer capacidad, Cima base) {
        this.id = id;
        this.capacidad = capacidad;
        this.cima = base;
        this.escaladores = 0;
    }

    public Integer getHelicopteryId() {
        return this.id;
    }

    public Integer getEscaladores() {
        return escaladores;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    /**
     * Cada vez que recoja un pasajero, se le sumará uno a la variable escaladores.
     */
    public synchronized void recogerEscalador() {
        this.escaladores++;
    }

    @Override
    public void run() {
        // Mientras haya escaladores en la cima va a ir a recogerlos.
        while (cima.isEscaladoresPendientes()) {
            try {
                // Mensaje de espera, dando a entender que hay un helicóptero ocupando la cima.
                System.out.println(
                    String.format(
                        "Helicóptero [%d]: En espera base.",
                        this.getHelicopteryId()
                    )
                );
                // Una vez tenga paso el helicóptero, se dispone a recoger escaladores.
                this.cima.recogerEscaladores(this);
                // Una vez haya obtenido los escaladores, mandará un mensaje a base diciendo cuantos hay recogido y cuantos quedan.
                System.out.println(
                    String.format(
                        "Helicóptero [%d]: Volviendo a base, Escaladores{Recogidos=%d, Restantes=%d}",
                        this.getHelicopteryId(),
                        // this.cima.getEscaladoresRecogidos(),
                        this.getEscaladores(),
                        this.cima.getEscaladoresPendientes()
                    )
                );
                // Se espera entre unos 5-10 segundos a dejar a los escaladores en la base.
                Thread.sleep((int) (Math.random() * 10000) + 5000);
                // Asignamos los escaladores a 0, dado que ha dejado a los escaladores en la base.
                this.escaladores = 0;
                // Se avisa a base, que el helicóptero vuelve a la cima.
                System.out.println(
                    String.format(
                        "Helicóptero [%d]: Escaladores en base, volviendo a la Cima",
                        this.getHelicopteryId()
                    )
                );
            } catch (Exception e) {
                // En caso de no poder obtener los escaladores esperados, se avisa a base.
                System.out.println(
                    String.format(
                        "Helicóptero [%d]: No ha podido seguir recogiendo escaladores...\n\tMotivo: %s\n\tResultado: Ha intentado recoger a %d escaladores.",
                        this.getHelicopteryId(),
                        e.getMessage(),
                        this.getCapacidad(),
                        this.getEscaladores()
                    )
                );
            }
        }
        // Una vez haya acabado su trabajo en la cima, se le avisa a base.
        System.out.println(
            String.format(
                "Helicóptero [%d]: Buen trabajo base, todos los escaladores recogidos.",
                this.getHelicopteryId()
            )
        );
    }

}
