import java.util.Random;

public class Runners_carlos_pomares extends Thread {

    private int id;

    public Runners_carlos_pomares(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        for (int i = 0; i < 50; i++) {
            System.out.println("Runner " + id + ": " + i + "m");
            // Random time sleep
            try {
                Thread.sleep(new Random().nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Runner " + id + " has finished");
    }

    public static void main(String[] args) throws Exception {
    
        Thread[] runners = new Runners_carlos_pomares[3];

        for (int i = 0; i < 3; i++) {
            Thread r = new Runners_carlos_pomares(i);
            
            // Random priority between 1 and 10
            r.setPriority((int) (Math.random() * 10) + 1);

            runners[i] = r;
        }

        for (int i = 0; i < 3; i++) {
            runners[i].start();
        }

        for (int i = 0; i < 3; i++) {
            runners[i].join();
        }

    }
}
