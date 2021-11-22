public class Escalador extends Thread {
    
    private Cima cima;

    /**
     * 
     * Un escalador espera a ser resctado por un helicoptero, y cuando se inicia
     * se añade a la cola de espera de la cima.
     * 
     * @param cima
     */
    public Escalador(Cima cima) {
        this.cima = cima;
    }

    @Override
    public void run() {
        // Se añade a la cola de espera de la cima.
        this.cima.nuevoEscalador(this);
    }

}
