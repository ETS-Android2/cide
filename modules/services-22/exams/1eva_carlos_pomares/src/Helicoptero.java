public class Helicoptero extends Thread {
    
    private Integer id;
    private Integer pasajeros;
    private Integer capacidad;
    private Cima cima;
    
    public Helicoptero(Integer id, Integer capacidad, Cima base) {
        this.id = id;
        this.capacidad = capacidad;
        this.cima = base;
        this.pasajeros = 0;
    }

    public Integer getHelicopteryId() {
        return this.id;
    }

    public Integer getPasajeros() {
        return pasajeros;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public synchronized void recogerEscalador() {
        this.pasajeros++;
    }

    @Override
    public void run() {
        while (cima.isEscaladoresPendientes()) {
            try {
                System.out.println(
                    String.format(
                        "Helicóptero [%d]: En espera base.",
                        this.getHelicopteryId()
                    )
                );
                Integer pasajerosRecogidos = this.cima.recogerPasajeros(this);
                System.out.println(
                    String.format(
                        "Helicóptero [%d]: Volviendo a base, Escaladores{Recogidos=%d, Restantes=%d}",
                        this.getHelicopteryId(),
                        // this.cima.getEscaladoresRecogidos(),
                        this.getPasajeros(),
                        this.cima.getEscaladoresPendientes()
                    )
                );
                Thread.sleep((int) (Math.random() * 10000) + 5000);
                this.pasajeros = 0;
                System.out.println(
                    String.format(
                        "Helicóptero [%d]: Escaladores en base, volviendo a la Cima",
                        this.getHelicopteryId()
                    )
                );
            } catch (Exception e) {
                System.out.println(
                    String.format(
                        "Helicóptero [%d]: No ha podido seguir recogiendo escaladores...",
                        this.getHelicopteryId()
                    )
                );
                System.out.println(
                    String.format(
                        "Helicóptero [%d]: Ha conseguido recoger a %d escaladores.",
                        this.getHelicopteryId(),
                        this.getPasajeros()
                    )
                );
            }
        }
        System.out.println(
            String.format(
                "Helicóptero [%d]: Buen trabajo base, todos los escaladores recogidos.",
                this.getHelicopteryId()
            )
        );
    }

}
