package EjerciciosDeClase.HashMap;

/*

    Project     Programming21
    Package     EjerciciosDeClase.HashMap    
    
    Version     1.0      
    Author      Carlos Pomares
    Date        2021-01-15

    DESCRIPTION
    
*/

import java.util.HashMap;
import java.util.Map;

/**
 * @author Carlos Pomares
 */


public class HashMapsE02 {
    public static void start(){
        // CODE
        HashMap<Integer,String> moneda = new HashMap<>();

        System.out.println(generarMonedas(6));

    }

    private static HashMap<Integer,String> generarMonedas(int numberOfDesiredCoins){

        HashMap<Integer,String> output = new HashMap<>();
        String polaridadAnterior = "";
        int valorAnterior = 0;


        for (int i = 0; i < numberOfDesiredCoins; i++) {

            System.out.println("BUCLE");

            HashMap<Integer,String> input;

            if(output.isEmpty()){
                input = generaMoneda();
                for(Map.Entry<Integer,String> entry : input.entrySet()){
                    output.put(entry.getKey(),entry.getValue());
                }
            } else {
                input = generaMoneda(valorAnterior,polaridadAnterior);
                for(Map.Entry<Integer,String> entry : input.entrySet()){
                    output.put(entry.getKey(),entry.getValue());
                }
            }

            for(Map.Entry<Integer,String> entry : input.entrySet()){
                valorAnterior = entry.getKey();
                polaridadAnterior = entry.getValue();
                System.out.println("VALOR ANTERIOR " + valorAnterior + " POLARIDAD ANTERIOR" + polaridadAnterior);
            }

        }

        return output;

    }

    private static HashMap<Integer,String> generaMoneda(){

        HashMap<Integer,String> output = new HashMap<>();

        int possibilitat = (int)(Math.random() * 10 + 1);

        //System.out.println(possibilitat);

        output.put(generaValor(),generaPolaridad());

        return output;

    }

    private static HashMap<Integer,String> generaMoneda(Integer monedaValor, String monedaPolaridad){

        HashMap<Integer,String> output = new HashMap<>();

        int possibilitat = (int)(Math.random() * 10 + 1);

        //System.out.println(possibilitat);

        if(possibilitat > 5){
            output.put(monedaValor,generaPolaridad(monedaPolaridad));
        } else {
            output.put(generaValor(),monedaPolaridad);
        }

        return output;

    }

    private static String generaPolaridad(){
        return ((int)(Math.random() * 2 + 1) == 1) ? "cara" : "cruz";
    }

    private static String generaPolaridad(String polaridad){
        return (polaridad.equals("cara")) ? "cruz" : "cara";
    }

    private static int generaValor(){
        int[] possiblesValores = new int[]{1,2,5};
        return ((int)(Math.random() * 2 + 1) == 2) ? (possiblesValores[(int)(Math.random() * 3)] * 10) : (possiblesValores[(int)(Math.random() * 3)]);
    }

}
