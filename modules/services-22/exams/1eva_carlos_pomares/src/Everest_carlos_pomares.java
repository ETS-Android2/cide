/**
 * 
 * Examen 1 evaluación -- Carlos Pomares
 * Contexto Everest
 * 
 * El desastre del monte Everest de 1996 se refiere a los eventos acontecidos en una franja de apenas 24 horas, entre el 10 y el 11 de mayo de 1996, cuando ochenta y seis personas quedaron atrapadas en una tormenta de nieve. 
 * Numerosos escaladores, entre ellos varios equipos grandes, así como algunas pequeñas asociaciones e incluso algunos escaladores en solitario, se encontraban en las alturas del Everest durante la tormenta.
 * Desde el campo base se movilizaron para coordinar el rescate de esas personas. Para ello contactaron con el gobierno nepalí para que mandase ayuda. El gobierno mandó 3 helicópteros (hilos) capaces de sobrevolar el techo de los 8000m.
 * 
 * Uno con capacidad para 5 personas.
 * Otro con capacidad para 3 personas.
 * Y el último con capacidad para 1 persona.
 * 
 * Los helicópteros tienen que irse turnando para realizar viajes hasta la cima y rescatar a los escaladores ya que el espacio que hay en la cima es muy pequeño y no cabe más que un helicóptero a la vez.
 * 
 * Se tarda entre 10 y 20 segundos en recoger los escaladores y llevarselos, y llegar el siguiente helicóptero.
 * 
 * @author Carlos Pomares (https://www.github.com/pomaretta)
 * 
 */

public class Everest_carlos_pomares {
    
    public static void main(String[] args) {
        
        // Crear una instancia de la cima
        Cima cima = new Cima();

        // Creamos los escaladores
        Escalador[] pasajeros = new Escalador[86];
        for (int i = 0; i < 86; i++) {
            pasajeros[i] = new Escalador(cima);
            pasajeros[i].start();
        }

        // Esperamos a que terminen todos los escaladores
        for (int i = 0; i < pasajeros.length; i++) {
            try {
                pasajeros[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
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

        // Iniciamos los helicopteros
        for (int i = 0; i < helicopteros.length; i++) {
            helicopteros[i].start();
        }

    }

}
