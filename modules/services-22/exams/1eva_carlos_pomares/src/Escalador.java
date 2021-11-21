public class Escalador extends Thread {
    
    private Cima cima;

    public Escalador(Cima cima) {
        this.cima = cima;
    }

    @Override
    public void run() {
        this.cima.nuevoEscalador(this);
    }

}
