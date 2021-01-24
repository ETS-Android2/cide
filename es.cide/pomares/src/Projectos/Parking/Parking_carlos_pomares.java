package Projectos.Parking;

/*

    Project     Programming21
    Package     Projectos.Parking    
    
    Version     1.0      
    Author      Carlos Pomares
    Date        2021-01-23

    DESCRIPTION
    
*/

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Carlos Pomares
 */

enum TipusPlacesParking {
    Discapacitat,No_Discapacitat
}

public class Parking_carlos_pomares {

    // PERSISTENT OBJECTS
    private BufferedReader reader;
    private BufferedWriter writer;

    // CONSTANT VARIABLES
    private final String AUTHOR = "Carlos Pomares";

    // PARKING VARIABLES
    private HashMap<String,TipusPlacesParking> matricules;
    private HashMap<String,Integer> vehicles;
    private HashMap<Integer,TipusPlacesParking> places;

    // PROPERTIES
    private int placesDiscapacitat;
    private int placesNormals;
    boolean normalOccupation = false;
    boolean disabledOccupation = false;

    public Parking_carlos_pomares(int places_no_discapacitats,int places_discapacitats){

        this.placesNormals = places_no_discapacitats;
        this.placesDiscapacitat = places_discapacitats;

        this.places = generateParking();
        this.vehicles = new HashMap<>();
        this.matricules = new HashMap<>();

    }

    /*
    *
    * Public methods
    *
    * */

    // FUNCTIONAL(OK) -- DEPLOY(OK)
    public void llegirMatricules(String path) throws Exception {
        try {

            // READ ALL LINES
            this.reader = new BufferedReader(new FileReader(path));

            String plate = this.reader.readLine();

            // VEHICLE ENTERS
            while(plate != null){

                try {
                    if(platePattern(plate)){
                        int random = (int)(Math.random() * 10 + 1);
                        if(random > 2){
                            entraCotxe(plate);
                        } else {
                            entraCotxeDiscapacitat(plate);
                        }
                    }
                } catch (Exception e){
                    System.out.println(e.getMessage());
                }


                plate = this.reader.readLine();

            }

            this.reader.close();

        } catch (FileNotFoundException notFoundException){
            throw new Exception("ALERTA =====> Fitxer incorrecte o inexistent.");
        } catch (Exception exception){
            throw new Exception(exception.getMessage());
        }
    }
    // FUNCTIONAL(OK) -- DEPLOY(OK)
    public void guardarMatricules(String path) throws Exception {

        if(this.vehicles.size() == 0) throw new Exception("No hay datos que guardar.");

        try {

            this.writer = new BufferedWriter(new FileWriter(path));

            for(String plate : this.vehicles.keySet()){
                this.writer.write(plate + "\n");
            }

            this.writer.close();

        } catch (FileNotFoundException notFoundException){
            System.out.println("ALERTA =====> Fitxer incorrecte o inexistent.");
        }

    }
    // FUNCTIONAL(OK) -- DEPLOY(OK)
    public int entraCotxe(String matricula) throws Exception {

        if(platePattern(matricula) && !plateExists(matricula) && getPlacesLliures(TipusPlacesParking.No_Discapacitat) != 0){
            searchForPlace(matricula,TipusPlacesParking.No_Discapacitat);
            this.matricules.put(matricula,TipusPlacesParking.No_Discapacitat);
        } else {
            throw new Exception("El cotxe ja està al parking. No pot entrar.");
        }

        checkParkingStatus();
        checkTypeIntegrity(matricula);

        return this.vehicles.get(matricula);
    }
    // FUNCTIONAL(OK) -- DEPLOY(OK)
    public int entraCotxeDiscapacitat(String matricula) throws Exception {
        if(platePattern(matricula) && !plateExists(matricula)){
            searchForPlace(matricula,TipusPlacesParking.Discapacitat);
            this.matricules.put(matricula,TipusPlacesParking.Discapacitat);
        } else {
            throw new Exception("El cotxe ja està al parking. No pot entrar.");
        }

        checkParkingStatus();
        checkTypeIntegrity(matricula);

        return this.vehicles.get(matricula);
    }
    // FUNCTIONAL(OK) -- DEPLOY(OK)
    public void surtCotxe(String matricula) throws Exception {
        // CHECK PLATE AND TYPE
        if(this.matricules.containsKey(matricula) && this.matricules.get(matricula) == TipusPlacesParking.No_Discapacitat){
            this.vehicles.remove(matricula);
            this.matricules.remove(matricula);
        } else {
            throw new Exception("El cotxe no és al parking.");
        }
    }
    // FUNCTIONAL(OK) -- DEPLOY(OK)
    public void surtCotxeDiscapacitat(String matricula) throws Exception {
        // CHECK PLATE AND TYPE
        if(this.matricules.containsKey(matricula) && this.matricules.get(matricula) == TipusPlacesParking.Discapacitat){
            this.vehicles.remove(matricula);
            this.matricules.remove(matricula);
        } else {
            throw new Exception("El cotxe no és al parking.");
        }
    }
    // FUNCTIONAL(OK) -- DEPLOY(OK)
    public int getPlacesOcupades(TipusPlacesParking tipus) throws Exception {
        int counter = 0;
        for(Integer placa : this.vehicles.values()){
            if(this.places.get(placa) == tipus){
                counter++;
            }
        }

        //getParkingVehicleStatus(tipus);

        return counter;
    }
    // FUNCTIONAL(OK) -- DEPLOY(OK)
    public int getPlacesLliures(TipusPlacesParking tipus) throws Exception {
        int counter = 0;
        for(Integer placa : this.places.keySet()){
            if(this.places.get(placa) == tipus && this.vehicles.containsValue(placa)){
                counter++;
            }
        }

        /*if(getParkingVehicleStatus(tipus) >= 85){
            if(tipus == TipusPlacesParking.No_Discapacitat){
                throw new Exception("ALERTA =====> Ocupació de places per no discapacitats supera el 85%.");
            } else {
                throw new Exception("ALERTA =====> Ocupació de places per discapacitats supera el 85%.");
            }
        }*/


        return (tipus == TipusPlacesParking.No_Discapacitat) ? (this.placesNormals - counter) : (this.placesDiscapacitat - counter);
    }

    /*
    *
    * Private methods
    *
    * */

    private HashMap<Integer,TipusPlacesParking> generateParking() {

        HashMap<Integer,TipusPlacesParking> output = new HashMap<>();

        float numberOfNormalPlaces = (float)this.placesNormals / 3;
        float numberOfDisabledPlaces = (float)this.placesDiscapacitat / 3;
        float remainOfDisabled = this.placesDiscapacitat - ((int)numberOfDisabledPlaces * 3);

        int counter = 1;
        int disabledCounter = 1;
        boolean disabled = false;

        for(int index = 1; index <= (this.placesNormals + this.placesDiscapacitat); index++){
            if(this.placesDiscapacitat <= 5 && disabledCounter <= this.placesDiscapacitat){
                output.put(index,TipusPlacesParking.Discapacitat);
                disabledCounter++;
            } else if(disabled && this.placesDiscapacitat > 5){
                output.put(index,TipusPlacesParking.Discapacitat);
                if(remainOfDisabled > 0 && counter == 2){
                    if(disabledCounter == ((int)numberOfDisabledPlaces + remainOfDisabled)){
                        disabledCounter = 1;
                        disabled = false;
                    } else {
                        disabledCounter++;
                    }
                } else {
                    if(disabledCounter == (int)numberOfDisabledPlaces){
                        disabledCounter = 1;
                        disabled = false;
                    } else {
                        disabledCounter++;
                    }
                }
            } else {
                output.put(index,TipusPlacesParking.No_Discapacitat);
                if(index == ((int)numberOfNormalPlaces * counter) && counter <= 3){
                    disabled = true;
                    counter++;
                }
            }
        }

        /*for(Map.Entry<Integer,TipusPlacesParking> entry : output.entrySet()){
            System.out.println("PLAZA: " + entry.getKey() + " TIPO: " + entry.getValue());
        }*/

        return output;
    }

    private void checkParkingStatus() throws Exception {
        // CHECK FOR BOTH TYPES
        if(getParkingVehicleStatus(TipusPlacesParking.No_Discapacitat) >= 85 && !this.normalOccupation){
            this.normalOccupation = true;
            throw new Exception("Ocupació de places per no discapacitats supera el 85%.");
        } else if(getParkingVehicleStatus(TipusPlacesParking.Discapacitat) >= 85 && !this.disabledOccupation){
            this.disabledOccupation = true;
            throw new Exception("Ocupació de places per discapacitats supera el 85%.");
        }
    }
    private void searchForPlace(String matricula,TipusPlacesParking tipus) throws Exception {
        int place = (int)(Math.random() * (this.placesNormals + this.placesDiscapacitat) + 1);
        if(tipus == TipusPlacesParking.No_Discapacitat && getParkingVehicleStatus(tipus) == 100) {
            throw new Exception("PARKING LLENO");
        } else {
            if(getParkingVehicleStatus(tipus) == 100 && tipus == TipusPlacesParking.Discapacitat){
                searchForPlace(matricula,TipusPlacesParking.No_Discapacitat);
            } else if(this.vehicles.containsValue(place)){
                searchForPlace(matricula,tipus);
            } else if(this.places.get(place) != tipus){
                int garruloProbability = (int)(Math.random() * 100 + 1);
                if(garruloProbability <= 15 && this.places.get(place) == TipusPlacesParking.Discapacitat){
                    this.vehicles.put(matricula,place);
                } else {
                    searchForPlace(matricula,tipus);
                }
            } else {
                this.vehicles.put(matricula,place);
            }
        }
    }
    private void checkTypeIntegrity() throws Exception {
        for(String matricula : this.vehicles.keySet()){
            if(this.places.get(this.vehicles.get(matricula)) != this.matricules.get(matricula) && this.matricules.get(matricula) == TipusPlacesParking.No_Discapacitat){
                throw new Exception("Garrulo detected!!! Ha aparcat a la plaça: " + this.vehicles.get(matricula));
            } else if(this.places.get(this.vehicles.get(matricula)) == TipusPlacesParking.No_Discapacitat && this.matricules.get(matricula) == TipusPlacesParking.Discapacitat) {
                throw new Exception("Parking per discapacitats ple. Ha ocupat plaça normal num: " + this.vehicles.get(matricula));
            }
        }
    }
    private void checkTypeIntegrity(String matricula) throws Exception {
        if(this.places.get(this.vehicles.get(matricula)) != this.matricules.get(matricula) && this.matricules.get(matricula) == TipusPlacesParking.No_Discapacitat){
            throw new Exception("Garrulo detected!!! Ha aparcat a la plaça: " + this.vehicles.get(matricula));
        } else if(this.places.get(this.vehicles.get(matricula)) == TipusPlacesParking.No_Discapacitat && this.matricules.get(matricula) == TipusPlacesParking.Discapacitat) {
            throw new Exception("Parking per discapacitats ple. Ha ocupat plaça normal num: " + this.vehicles.get(matricula));
        }
    }
    private float getParkingVehicleStatus(TipusPlacesParking tipus) throws Exception {
        return (tipus == TipusPlacesParking.No_Discapacitat) ?  ((float)getPlacesOcupades(tipus) * 100 / this.placesNormals) : ((float)getPlacesOcupades(tipus) * 100 / this.placesDiscapacitat);
    }
    private boolean plateExists(String plate) {
        if(this.vehicles.containsKey(plate)){
            return true;
        } else {
            return false;
        }
    }
    private boolean platePattern(String plate) throws Exception{

        // PARSE PATTERN
        Pattern pattern = Pattern.compile("[0-9]{4}[A-Z]{3}");
        Matcher matcher = pattern.matcher(plate);

        if(matcher.find()){
            return true;
        } else {
            throw new Exception("Matrícula incorrecte.");
        }

    }

    /*
    *
    * EXPERIMENTAL
    *
    * */

    public void generarMatriculas(int lenght) throws Exception{
        if(lenght <= (this.placesNormals + this.placesDiscapacitat)){
            for (int i = 0; i < lenght ; i++) {
                int x = (int)(Math.random() * 10 + 1);
                if(x > 2){
                    entraCotxe(generatePlate());
                } else {
                    entraCotxeDiscapacitat(generatePlate());
                }
            }
        } else {
            throw new Exception("Mas usuarios que plazas en el parking.");
        }
    }
    public void mostrarParking(){
        for(Map.Entry<String,Integer> vehiculo : this.vehicles.entrySet()){
            System.out.println("MATRÍCULA: " + vehiculo.getKey() + " TIPO DE MATRICULA: " + this.matricules.get(vehiculo.getKey()) + " PLAZA: " + vehiculo.getValue() + " TIPO DE PLAZA: " + this.places.get(vehiculo.getValue()));
        }
    }
    private String generatePlate(){
        int plateNumber = (int)(Math.random() * 8888 + 1000);

        String plateLetter = "";
        String dictionary = "ABCDEFGHIJALMNOPQRSTUWXYZ";
        while(plateLetter.toCharArray().length <= 2){
            int x = (int)(Math.random() * dictionary.toCharArray().length);
            plateLetter += dictionary.toCharArray()[x];
        }
        return String.valueOf(plateNumber) + plateLetter;
    }

}
