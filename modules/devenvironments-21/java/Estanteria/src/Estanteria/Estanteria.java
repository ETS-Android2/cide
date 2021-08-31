package Estanteria;

/*

    Project     ED21
    Package     Estanteria    
    
    Version     1.0      
    Author      Carlos Pomares
    Date        2021-02-04

    DESCRIPTION
    
*/

import java.util.ArrayList;

/**
 * @author Carlos Pomares
 */

public class Estanteria {

    private final int LIBROS_MAXIMOS = 10;
    private final Libro[] libros = new Libro[this.LIBROS_MAXIMOS];

    public void afegirLlibre(Libro llibre) throws Exception {
        if(containsLlibre(llibre)){
            throw new Exception("Libro ya en la estanteria.");
        } else {
            addAtFirstPosition(llibre);
        }
    }

    public void eliminarLlibre(String arg, String criterio) throws Exception {
        if(indexOfLlibre(arg,criterio) != -1){
            this.libros[indexOfLlibre(arg,criterio)] = null;
        } else {
            throw new Exception("El libro no esta en la estanteria.");
        }
    }

    public void veureTop(){

        System.out.println("-------- TOP --------");

        Libro[] orderedList = orderArray(this.libros);
        showEstanteria(orderedList);

        System.out.println("\n-------- END --------");

    }

    public void veureEstanteria(){
        showEstanteria(this.libros);
    }

    private void showEstanteria(Libro[] libros){
        System.out.printf("%-50s %-30s %-15s",
                "Título","Autor","Calificacion");
        for(Libro libro : libros){
            if(libro != null){
                System.out.printf("\n%-50s %-30s %-15f",
                        libro.getTitulo(),libro.getAutor(),libro.getCalificacion());
            } else {
                System.out.printf("\n%-15s %-15s %-15f",
                        null,null,null);
            }
        }
    }

    private Libro[] orderArray(Libro[] array){
        ArrayList<Libro> output = convertSimpleToList(array);
        int i, j;
        Libro temp;
        for(i = 0; i < output.size(); i++){
            for(j = 0; j < output.size()-i-1; j++){
                if(Libro.comparate(output.get(j),output.get(j+1)) == -1){
                    temp = output.get(j);
                    output.set(j,output.get(j+1));
                    output.set((j+1),temp);
                }
            }
        }
        return convertListToSimple(output);
    }

    private ArrayList<Libro> convertSimpleToList(Libro[] input){
        ArrayList<Libro> output = new ArrayList<Libro>();
        for(Libro libro : input){
            if(libro != null){
                output.add(libro);
            }
        }
        return output;
    }

    private Libro[] convertListToSimple(ArrayList<Libro> input){
        Libro[] output = new Libro[LIBROS_MAXIMOS];
        for (int i = 0; i < input.size(); i++) {
            output[i] = input.get(i);
        }
        return output;
    }

    private void addAtFirstPosition(Libro llibre){
        for (int i = 0; i < this.libros.length; i++) {
            if(this.libros[i] == null){
                this.libros[i] = llibre;
                break;
            }
        }
    }

    private int indexOfLlibre(String arg,String mode) throws Exception {
        for (int i = 0; i < this.libros.length; i++) {
            if(mode.toLowerCase().equals("autor")){
                if(this.libros[i].getAutor().equals(arg)){
                    return i;
                }
            } else if(mode.toLowerCase().equals("titulo") || mode.toLowerCase().equals("título")){
                if(this.libros[i].getTitulo().equals(arg)){
                    return i;
                }
            }
        }
        return -1;
    }

    private boolean containsLlibre(Libro llibre){
        for(Libro libro : this.libros){
            if(libro == llibre){
                return true;
            }
        }
        return false;
    }

}
