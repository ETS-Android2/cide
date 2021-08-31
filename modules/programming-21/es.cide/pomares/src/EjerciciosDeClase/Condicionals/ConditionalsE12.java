package EjerciciosDeClase.Condicionals;

/*

    Project     Programming21
    Package     EjerciciosDeClase.Condicionals    
    
    Version     1.0      
    Author      Carlos Pomares
    Date        2020-11-17

    DESCRIPTION
    Realitza un programa que faci un petit qüestionari amb 10 preguntes
    tipus test sobre les assignatures que s’imparteixen al CF.
    Cada pregunta encertada sumarà un punt. El programa mostrarà
    al final del qüestionari la qüalificació. Passeu el vostre
    
*/

import java.util.Scanner;

/**
 * @author Carlos Pomares
 */

public class ConditionalsE12 {
    public static void start(){

        Scanner userIn = new Scanner(System.in);

        String respuesta;
        int preguntasAcertadas = 0;
        int preguntasTotales = 0;

        String[][] preguntas = new String[][]{
                {"¿Qué asignatura tenemos a primera hora el lunes?","1"},
                {"¿Qué es un router?","2"},
                {"¿Qué paradigma de la programación utiliza Java?","3"},
                {"¿Cómo se declara una variable?","2"},
                {"¿Cómo se sube el contenido a github?","3"},
                {"¿Cómo se sube el contenido a github?","3"},
                {"¿Cómo se sube el contenido a github?","3"},
                {"¿Cómo se sube el contenido a github?","3"},
                {"¿Cómo se sube el contenido a github?","3"},
                {"¿Cómo se sube el contenido a github?","3"}
        };

        String[][] respuestas = new String[][]{
            {"1","2","3","4"},
            {"1","2","3","4"},
            {"1","2","3","4"},
            {"1","2","3","4"},
            {"1","2","3","4"},
            {"1","2","3","4"},
            {"1","2","3","4"},
            {"1","2","3","4"},
            {"1","2","3","4"},
            {"1","2","3","4"}
        };

        do {

            System.out.println(preguntas[preguntasTotales][0]);
            System.out.println("Respuestas: \n" +
                        "" + respuestas[preguntasTotales][0] + "\n" +
                        "" + respuestas[preguntasTotales][1] + "\n" +
                        "" + respuestas[preguntasTotales][2] + "\n" +
                        "" + respuestas[preguntasTotales][3]
                    );

            respuesta = userIn.nextLine();

            if(respuesta.equals(preguntas[preguntasTotales][1])){
                preguntasAcertadas++;
            }

            preguntasTotales++;

        } while (preguntasTotales < 10);

        System.out.println("Has acertado " + preguntasAcertadas + " preguntas.");

    }
}
