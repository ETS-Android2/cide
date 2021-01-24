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
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author Carlos Pomares
 */

public class ParkingTest_carlos_pomares {

    // COLORS
    private final String RESET = "\033[0m";
    private final String RED = "\033[0;31m";
    private final String GREEN = "\033[0;32m";
    private final String BLUE = "\033[0;34m";

    private final Scanner USER_IN = new Scanner(System.in);
    private final ArrayList<String> ALERTS = new ArrayList<>();

    private Parking_carlos_pomares parking;
    private String pathToFile;

    private boolean debug = false;
    private boolean readed = false;

    public ParkingTest_carlos_pomares(String[] args){

        componentInitialization();

        if(pathIsValid(args[0])){
            try {
                this.parking.llegirMatricules(args[0]);
                this.readed = true;
                this.ALERTS.add(String.format("Read file path: %s",args[0]));
            } catch (Exception e){
                ALERTS.add(e.getMessage());
            }
        }

        // APPLICATION RUNTIME LOOP
        menuPrincipal();

    }
    public ParkingTest_carlos_pomares(){
        componentInitialization();

        // APPLICATION RUNTIME LOOP
        menuPrincipal();
    }
    private void componentInitialization(){
        // PARKING INIT -- CHANGE HERE VEHICLE OCCUPATION
        this.parking = new Parking_carlos_pomares(25,10);
    }
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
                "Generar matriculas",
                "Ver matriculas",
                "Creditos"
        };

        initBrief();

        while(!exitApplication){

            if(ALERTS.size() > 0){
                errorBrief();
            }

            optionBrief(opcions);

            if(debug){
                optionBrief(debugOptions,8);
            }

            System.out.print("\n\n\tOrdre: ");

            try {

                String order = this.USER_IN.nextLine();

                if(order.contains("debug")){
                    debug = true;
                    this.ALERTS.add(this.GREEN + "DEBUG Enabled" + this.RESET);
                }

                switch (Integer.parseInt(order)){
                    case 1 -> {
                        if(!this.readed){
                            System.out.print("\n\tPath to file: ");
                            this.parking.llegirMatricules(USER_IN.nextLine());
                            this.readed = true;
                        } else {
                            throw new Exception("File already read.");
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
                        System.out.println("EXIT.");
                        exitApplication = true;
                    }
                    case 8 -> {
                        if(debug) this.ALERTS.clear();
                    }
                    case 9 -> {
                        if(debug){
                            System.out.print("LIMITE: ");
                            this.parking.generarMatriculas(Integer.parseInt(USER_IN.nextLine()));
                        }
                    }
                    case 10 -> {
                        if(debug) this.parking.mostrarParking();
                    }
                    case 11 -> {
                        if(debug) author();
                    }
                }

            } catch (NumberFormatException numberFormatException){
                // Nothing
            } catch (Exception error){
                this.ALERTS.add(error.getMessage());
            }
        }

    }
    private void initBrief(){

        System.out.print("\n\t-------------------------- PARKING -------------------------");

        // INFORMATION

        //System.out.print("\n\t------------------------------------------------------------");

    }
    private void errorBrief(){
        // Si hi ha errors en la conduccio surten
        System.out.print("\n\n\t-------------------------- ALERTAS -------------------------");

        for (int i = 0; i < ALERTS.size(); i++) {
            System.out.printf("\n\t%-10s %-15s",
                    (i+1), (this.RED + ALERTS.get(i) + this.RESET));
        }

        System.out.print("\n\t------------------------------------------------------------");
    }
    private void optionBrief(String[] options){
        System.out.printf("\n\n\t%-10s %-30s\n",
                "Nombre","Opcio");

        for (int i = 0; i < options.length ; i++) {
            System.out.printf("\t%-10d %-30s\n",(i + 1),this.BLUE + options[i] + this.RESET);
        }

    }
    private void optionBrief(String[] options,int firstNumber){
        System.out.printf("\n\n\t%-10s %-30s\n",
                "Nombre","Opcio");

        for (int i = 0; i < options.length ; i++) {
            System.out.printf("\t%-10d %-30s\n",(i + firstNumber),this.BLUE + options[i] + this.RESET);
        }

    }
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
    private void vehicleEntry(boolean type) throws Exception {
        System.out.print("MATRÍCULA: ");
        String matricula = this.USER_IN.nextLine();
        if(type){
            this.parking.entraCotxe(matricula);
        } else {
            this.parking.entraCotxeDiscapacitat(matricula);
        }
    }
    private void vehicleRemove(boolean type) throws Exception {
        System.out.print("MATRÍCULA: ");
        String matricula = this.USER_IN.nextLine();
        if(type){
            this.parking.surtCotxe(matricula);
        } else {
            this.parking.surtCotxeDiscapacitat(matricula);
        }
    }
    private void saveParkingData(){

        System.out.print("\n\tIntroduce la ruta del archivo: ");

        String path = "";

        try {

            path = this.USER_IN.nextLine();

            if(pathIsValid(path)) {
                this.parking.guardarMatricules(path);
            } else {
                throw new Exception(String.format("Path %s not valid.",path));
            }

            System.out.println("\n\n\t" + this.GREEN + "SUCCESFULL SAVE!" + this.RESET);

        } catch (Exception error){
            this.ALERTS.add(error.getMessage());
        }


    }
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

        // PARAMS DETECTION
        if(args.length > 0){
            // APPLICATION INITIALIZATION
            ParkingTest_carlos_pomares parkingTest = new ParkingTest_carlos_pomares(args);
        } else {
            ParkingTest_carlos_pomares parkingTest = new ParkingTest_carlos_pomares();
        }


    }

}
