public class Main {
    
    public static void main(String[] args) {
        
        Cima cima = new Cima();

        Escalador[] pasajeros = new Escalador[86];
        for (int i = 0; i < 86; i++) {
            pasajeros[i] = new Escalador(cima);
            pasajeros[i].start();
        }

        System.out.println(
            String.format(
                "Se encuentran %d escaladores en la cima\nHelicópteros en camino...",
                cima.getEscaladoresPendientes()
            )
        );

        Helicoptero[] helicopteros = new Helicoptero[3];

        // Helicoptero 1 con 5 pasajeros máximos
        helicopteros[0] = new Helicoptero(1, 5, cima);
        // Helicoptero 2 con 3 pasajeros máximos
        helicopteros[1] = new Helicoptero(2, 3, cima);
        // Helicoptero 3 con 1 pasajeros máximos
        helicopteros[2] = new Helicoptero(3, 1, cima);

        for (int i = 0; i < helicopteros.length; i++) {
            helicopteros[i].start();
        }
    
        for (int i = 0; i < helicopteros.length; i++) {
            try {
                helicopteros[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (int i = 0; i < pasajeros.length; i++) {
            try {
                pasajeros[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
