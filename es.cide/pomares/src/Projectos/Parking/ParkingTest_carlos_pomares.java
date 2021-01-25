package Projectos.Parking;

/*

    Project     Programming21
    Package     Projectos.Parking    
    
    Version     1.0      
    Author      Carlos Pomares
    Date        2021-01-23

    DESCRIPTION
    Aquesta classe ha de poder rebre un paràmetre que sigui el path complet d’un fitxer.
    Si és el cas, el primer que haurà de fer serà omplir el parking amb les matrícules contingudes
    al fitxer*. S’ha d’implementar un menú.
    
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
    private boolean llegit = false;

    /**
     *
     * Constructor amb path predefinid.
     *
     * @param args asignarà el path a l'aplicació.
     */
    public ParkingTest_carlos_pomares(String[] args){
        componentInitialization();
        if(pathIsValid(args[0])){
            try {
                this.parking.llegirMatricules(args[0]);
                this.llegit = true;
                this.ALERTAS.add(String.format( this.GREEN + "Llegit desde arguments: %s" + this.RESET,args[0]));
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
     * Instanciarà la clase parking amb el paràmetres de persones no discapacitades y discapacitades.
     *
     */
    private void componentInitialization(){
        this.parking = new Parking_carlos_pomares(50,15);
    }

    /**
     *
     * El menu principal, el bucle de l'aplicació amb el que l'usuari interactuarà.
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
                "Credits"
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
                        if(!this.llegit){
                            System.out.print("\n\tRuta del arxiu: ");
                            this.parking.llegirMatricules(USUARI.nextLine());
                            this.llegit = true;
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
     * Mètode per donar per consola diferents missatges de comencament.
     *
     */
    private void initBrief(){
        System.out.print("\n\t-------------------------- PARKING -------------------------");
    }

    /**
     *
     * Mètode per imprimir per consola les alertes del programa.
     *
     */
    private void errorBrief(){
        System.out.print("\n\n\t-------------------------- ALERTES -------------------------");
        for (int i = 0; i < ALERTAS.size(); i++) {
            System.out.printf("\n\t%-10s %-15s",
                    (i+1), (this.RED + ALERTAS.get(i) + this.RESET));
        }
        System.out.print("\n\t------------------------------------------------------------");
    }

    /**
     *
     * Mètode per mostrar les opcions del menú.
     *
     * @param options un array d'opcions.
     */
    private void optionBrief(String[] options){
        System.out.printf("\n\n\t%-10s %-30s\n",
                "Nombre","Opció");
        for (int i = 0; i < options.length ; i++) {
            System.out.printf("\t%-10d %-30s\n",(i + 1),this.BLUE + options[i] + this.RESET);
        }
    }

    /**
     *
     * Métode per mostrar les opcions del menu amb un nombre donat.
     *
     * @param options un array d'opcions.
     * @param firstNumber el nombre per el que les opcions començaràn.
     */
    private void optionBrief(String[] options,int firstNumber){
        System.out.printf("\n\n\t%-10s %-30s\n",
                "Nombre","Opció");
        for (int i = 0; i < options.length ; i++) {
            System.out.printf("\t%-10d %-30s\n",(i + firstNumber),this.BLUE + options[i] + this.RESET);
        }
    }

    /**
     *
     * Un mètode per mostrar un petit dibuix amb informació sobre l'autor.
     *
     */
    private void author(){
        System.out.printf("\n\t========= %s =========\n", (this.BLUE + "Credits del autor" + this.RESET));
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
     * Mètode per introduir un cotxe a l'aplicacio mitjancant una inteficie per consola.
     *
     * @param type el tipus de cotxe que s'introduirà.
     * @throws Exception si ocurreix un error a l'hora de utilzar el mètodes de entrarCotxe o entrarCotxeDiscapacitat.
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
        System.out.print("\t" + this.GREEN + "OPERACIÓ SATISFACTORIA" + this.RESET);
        System.out.println("\n\n\t-------------- END --------------");
    }

    /**
     *
     * Mètode per introduir una matrícula d'un cotxe i que surti del parking.
     *
     * @param type el tipus de cotxo que ha de sortir.
     * @throws Exception si hi ha alguna excepció a l'hora de utilizar el mètodes sortirCotxe o sortirCotxeDiscapacitat.
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
        System.out.print("\t" + this.GREEN + "OPERACIÓ SATISFACTORIA" + this.RESET);
        System.out.println("\n\n\t-------------- END --------------");
    }

    /**
     *
     * Mètode que utilizarà el mètode guardarMatricules amb una petita interficie per introduir la ruta.
     *
     */
    private void saveParkingData(){
        System.out.print("\n\tIntrodueix la ruta del arxiu: ");
        String path = "";
        try {
            path = this.USUARI.nextLine();
            if(pathIsValid(path)) {
                this.parking.guardarMatricules(path);
            } else {
                throw new Exception(String.format("La ruta %s no es vàlida.",path));
            }
            System.out.println("\n\n\t" + this.GREEN + "GUARDAT SATISFACTORI!" + this.RESET);
        } catch (Exception error){
            this.ALERTAS.add(error.getMessage());
        }
    }

    /**
     *
     * Mètode per comprovar si una ruta es vàlida segons el tipus d'arxiu soportat.
     *
     * @param pathToValidate la ruta per validar.
     * @return true si la ruta es vàlida. false si no ho es.
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
        // Detectarà si hi ha arguments.
        if(args.length > 0){
            ParkingTest_carlos_pomares parkingTest = new ParkingTest_carlos_pomares(args);
        } else {
            ParkingTest_carlos_pomares parkingTest = new ParkingTest_carlos_pomares();
        }
    }

}
