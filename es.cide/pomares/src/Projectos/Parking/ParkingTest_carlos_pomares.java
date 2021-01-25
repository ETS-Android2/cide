package Projectos.Parking;

/*

    Project     Programming21
    Package     Projectos.Parking    
    
    Version     1.0      
    Author      Carlos Pomares
    Date        2021-01-23

    DESCRIPTION
    
*/

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Carlos Pomares
 */

public class ParkingTest_carlos_pomares {

    private final String RESET = "\033[0m";
    private final String RED = "\033[0;31m";
    private final String GREEN = "\033[0;32m";
    private final String BLUE = "\033[0;34m";

    private final Scanner USUARI = new Scanner(System.in);
    private final ArrayList<String> ALERTAS = new ArrayList<>();
    private Parking_carlos_pomares parking;

    private boolean debug = false;
    private boolean leido = false;

    /**
     *
     * Constructor amb path predefinid.
     *
     * @param args asignará el path a l'aplicació.
     */
    public ParkingTest_carlos_pomares(String[] args){
        componentInitialization();
        if(pathIsValid(args[0])){
            try {
                this.parking.llegirMatricules(args[0]);
                this.leido = true;
                this.ALERTAS.add(String.format( this.GREEN + "Read file path: %s" + this.RESET,args[0]));
            } catch (Exception e){
                ALERTAS.add(e.getMessage());
            }
        } else {
            ALERTAS.add(this.RED + "ALERTA ===> Fitxer amb format no correcte." + this.RESET);
        }
        menuPrincipal();
    }

    /**
     *
     * Constructor sense path.
     *
     */
    public ParkingTest_carlos_pomares(){
        componentInitialization();
        menuPrincipal();
    }

    /**
     *
     * Instanciara la clase parking amb el parámetres de persones no discapacitades y discapacitades.
     *
     */
    private void componentInitialization(){
        this.parking = new Parking_carlos_pomares(50,15);
    }

    /**
     *
     * El menu principal, el bucle de l'aplicació amb el que l'usuari interactuará.
     *
     */
    private void menuPrincipal(){

        boolean exitApplication = false;

        String[] opcions = new String[]{
                "Omplir parking a partir de fitxer",
                "Entrar Cotxe",
                "Entrar Cotxe Discapacitat",
                "Sortir Cotxe",
                "Sortir Cotxe Discapacitat",
                "Guardar llistat de matricules en fitxer",
                "Sortir"
        };

        String[] debugOptions = new String[]{
                "Borrar errors",
                "Creditos"
        };

        initBrief();

        while(!exitApplication){

            if(ALERTAS.size() > 0){
                errorBrief();
            }

            optionBrief(opcions);

            if(debug){
                optionBrief(debugOptions,8);
            }

            System.out.print("\n\n\tOrdre: ");

            try {

                String order = this.USUARI.nextLine();

                if(order.contains("debug")){
                    debug = true;
                    this.ALERTAS.add(this.GREEN + "DEBUG Habilitat" + this.RESET);
                }

                switch (Integer.parseInt(order)){
                    case 1 -> {
                        if(!this.leido){
                            System.out.print("\n\tRuta del arxiu: ");
                            this.parking.llegirMatricules(USUARI.nextLine());
                            this.leido = true;
                        } else {
                            throw new Exception("Fitxer ja llegit.");
                        }
                    }
                    case 2 -> {
                        vehicleEntry(true);
                    }
                    case 3 -> {
                        vehicleEntry(false);
                    }
                    case 4 -> {
                        vehicleRemove(true);
                    }
                    case 5 -> {
                        vehicleRemove(false);
                    }
                    case 6 -> {
                        saveParkingData();
                    }
                    case 7 -> {
                        exitApplication = true;
                    }
                    case 8 -> {
                        if(debug) this.ALERTAS.clear();
                    }
                    case 9 -> {
                        if(debug) author();
                    }
                }

            } catch (NumberFormatException numberFormatException){
                // Nothing
            } catch (Exception error){
                this.ALERTAS.add(error.getMessage());
            }
        }

    }

    /**
     *
     * Métode per donar per consola diferents missatges de comencament.
     *
     */
    private void initBrief(){
        System.out.print("\n\t-------------------------- PARKING -------------------------");
    }

    /**
     *
     * Métode per imprimir per consola les alertas del programa.
     *
     */
    private void errorBrief(){
        System.out.print("\n\n\t-------------------------- ALERTAS -------------------------");
        for (int i = 0; i < ALERTAS.size(); i++) {
            System.out.printf("\n\t%-10s %-15s",
                    (i+1), (this.RED + ALERTAS.get(i) + this.RESET));
        }
        System.out.print("\n\t------------------------------------------------------------");
    }

    /**
     *
     * Métode per mostrar les opcions del menu.
     *
     * @param options un array d'opcions.
     */
    private void optionBrief(String[] options){
        System.out.printf("\n\n\t%-10s %-30s\n",
                "Nombre","Opcio");
        for (int i = 0; i < options.length ; i++) {
            System.out.printf("\t%-10d %-30s\n",(i + 1),this.BLUE + options[i] + this.RESET);
        }
    }

    /**
     *
     * Métode per mostrar les opcions del menu amb un nombre donat.
     *
     * @param options un array d'opcions.
     * @param firstNumber el nombre per el que les opcions comencaran.
     */
    private void optionBrief(String[] options,int firstNumber){
        System.out.printf("\n\n\t%-10s %-30s\n",
                "Nombre","Opcio");
        for (int i = 0; i < options.length ; i++) {
            System.out.printf("\t%-10d %-30s\n",(i + firstNumber),this.BLUE + options[i] + this.RESET);
        }
    }

    /**
     *
     * Un métode per mostrar un petit dibuix amb informació sobre l'autor.
     *
     */
    private void author(){
        System.out.printf("\n\t========= %s =========\n", (this.BLUE + "Creditos del autor" + this.RESET));
        System.out.print("\t   ______________________________\n" +
                "\t / \\                             \\.\n" +
                "\t|   |                            |.\n" +
                "\t \\_ |  Gracias por probar        |.\n" +
                "\t    |  esta aplicación,          |.\n" +
                "\t    |  espero que te haya        |.\n" +
                "\t    |  gustado y te haya         |.\n" +
                "\t    |  servido de inspiración,   |.\n" +
                "\t    |  lo he hecho con cariño    |.\n" +
                "\t    |  y he intentado aplicar    |.\n" +
                "\t    |  nuevos conocimientos      |.\n" +
                "\t    |                            |.\n" +
                "\t    |  Gracias, Carlos Pomares   |.\n" +
                "\t    |                            |.\n" +
                "\t    |  github.com/pomaretta      |.\n" +
                "\t    |                            |.\n" +
                "\t    |  https://carlospomares.es  |.\n" +
                "\t    |                            |.\n" +
                "\t    |   _________________________|___\n" +
                "\t    |  /                            /.\n" +
                "\t    \\_/____________________________/.\n\n");
    }

    /**]
     *
     * Métode per introduir un cotxe a l'aplicacio mitjancant una interfaz per consola.
     *
     * @param type el tipus de cotxe que s'introduirá.
     * @throws Exception si ocurreix un error a l'hora de utilzar el métodes de entrarCotxe o entrarCotxeDiscapacitat.
     */
    private void vehicleEntry(boolean type) throws Exception {
        System.out.println("\n\t----------- MATRÍCULA -----------\n");
        System.out.print("\t" + this.BLUE + "MATRÍCULA: " + this.RESET);
        String matricula = this.USUARI.nextLine();
        if(type){
            this.parking.entraCotxe(matricula);
        } else {
            this.parking.entraCotxeDiscapacitat(matricula);
        }
        System.out.print("\t" + this.GREEN + "SUCCESSFULLY OPERATION" + this.RESET);
        System.out.println("\n\n\t-------------- END --------------");
    }

    /**
     *
     * Métode per introduir una matrícula d'un cotxo i que surti del parking.
     *
     * @param type el tipus de cotxo que ha de sortir.
     * @throws Exception si hi ha alguna excepció a l'hora de utilizar el métodes sortirCotxe o sortirCotxeDiscapacitat.
     */
    private void vehicleRemove(boolean type) throws Exception {
        System.out.println("\n\t----------- MATRÍCULA -----------\n");
        System.out.print("\t" + this.BLUE + "MATRÍCULA: " + this.RESET);
        String matricula = this.USUARI.nextLine();
        if(type){
            this.parking.surtCotxe(matricula);
        } else {
            this.parking.surtCotxeDiscapacitat(matricula);
        }
        System.out.print("\t" + this.GREEN + "SUCCESSFULLY OPERATION" + this.RESET);
        System.out.println("\n\n\t-------------- END --------------");
    }

    /**
     *
     * Métode que utilizará el métode guardarMatrícules amb una petita interficie per introduir la ruta.
     *
     */
    private void saveParkingData(){
        System.out.print("\n\tIntroduce la ruta del archivo: ");
        String path = "";
        try {
            path = this.USUARI.nextLine();
            if(pathIsValid(path)) {
                this.parking.guardarMatricules(path);
            } else {
                throw new Exception(String.format("Path %s not valid.",path));
            }
            System.out.println("\n\n\t" + this.GREEN + "SUCCESFULL SAVE!" + this.RESET);
        } catch (Exception error){
            this.ALERTAS.add(error.getMessage());
        }
    }

    /**
     *
     * Métode per comprobar si una ruta es valida segons el tipus d'arxiu soportat.
     *
     * @param pathToValidate la ruta per validar.
     * @return true si la ruta es valida. false si no ho es.
     */
    private boolean pathIsValid(String pathToValidate){
        String[] supportedTypes = new String[]{
                "txt"
        };
        String[] path = pathToValidate.replace("\\","/").split("/");
        String[] fileType = path[path.length - 1].split("\\.");
        boolean output = false;
        for(String type : supportedTypes){
            if (fileType[1].equals(type)) {
                output = true;
                break;
            }
        }
        return output;
    }

    public static void main(String[] args) {
        // Detectará si hi ha arguments.
        if(args.length > 0){
            ParkingTest_carlos_pomares parkingTest = new ParkingTest_carlos_pomares(args);
        } else {
            ParkingTest_carlos_pomares parkingTest = new ParkingTest_carlos_pomares();
        }
    }

}
