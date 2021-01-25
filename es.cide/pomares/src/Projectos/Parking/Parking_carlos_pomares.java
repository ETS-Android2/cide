package Projectos.Parking;

/*

    Project     Programming21
    Package     Projectos.Parking    
    
    Version     1.0      
    Author      Carlos Pomares
    Date        2021-01-23

    DESCRIPTION
    Ha de simular el funcionament d’un programa de gestió de parking.

    
*/

import java.io.*;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Carlos Pomares
 */

/**
 *
 * Enumeració de tipus de plaça posibles.
 *
 */
enum TipusPlacesParking {
    Discapacitat,No_Discapacitat
}

public class Parking_carlos_pomares {

    // OBJECTES DE PERSISTENCIA
    private BufferedReader llegir;
    private BufferedWriter escribir;

    // VARIABLES DEL PARKING
    private HashMap<String,TipusPlacesParking> matricules;
    private HashMap<String,Integer> vehicles;
    private HashMap<Integer,TipusPlacesParking> places;

    // PROPIETATS DEL PARKING
    private int placesDiscapacitats;
    private int placesNormals;
    boolean ocupacioNoDiscapacitats = false;
    boolean ocupacioDiscapacitats = false;

    /**
     *
     * Inicialitza diferents propietats i genera la estructura del parking.
     * Amb els paràmetres d'entrada s'assignen les plaçes per persones no discapacitades
     * i discapacitades.
     *
     * @param places_no_discapacitats nombre de persones no discapacitades.
     * @param places_discapacitats nombre de persones discapacitades.
     */
    public Parking_carlos_pomares(int places_no_discapacitats,int places_discapacitats){
        this.placesNormals = places_no_discapacitats;
        this.placesDiscapacitats = places_discapacitats;
        this.places = generarParking();
        this.vehicles = new HashMap<>();
        this.matricules = new HashMap<>();
    }

    /**
     *
     * Llegeix matrícules d'un fitxer de tipus TXT (text pla), on per cada matrícula correcte,
     * entrarà un cotxe al parking.
     *
     * @param path la ruta del arxiu.
     * @throws Exception si l'arxiu no te el format correcte o es inexsistent.
     */
    public void llegirMatricules(String path) throws Exception {
        try {
            isRutaValida(path);
            this.llegir = new BufferedReader(new FileReader(path));
            String plate = this.llegir.readLine();
            while(plate != null){
                try {
                    if(isMatriculaValida(plate)){
                        int random = (int)(Math.random() * 100 + 1);
                        if(random > 20){
                            entraCotxe(plate);
                        } else {
                            entraCotxeDiscapacitat(plate);
                        }
                    }
                } catch (Exception e){
                    System.out.println("ALERTA ====> " + e.getMessage());
                }
                plate = this.llegir.readLine();
            }
            this.llegir.close();
        } catch (FileNotFoundException notFoundException){
            throw new Exception("ALERTA =====> Fitxer incorrecte o inexistent.");
        } catch (Exception exception){
            throw new Exception(exception.getMessage());
        }
    }

    /**
     *
     * Guardarà les matrícules que el programa tengui en el moment de cridada del mètode.
     *
     * @param path la ruta del arxiu.
     * @throws Exception si el format del arxiu no es correcte o no hi ha dades que guardar.
     */
    public void guardarMatricules(String path) throws Exception {
        if(this.vehicles.size() == 0) throw new Exception("No hay datos que guardar.");
        try {
            if(isRutaValida(path)){
                this.escribir = new BufferedWriter(new FileWriter(path));
            }
            for(String plate : this.vehicles.keySet()){
                this.escribir.write(plate + "\n");
            }
            this.escribir.close();
        } catch (FileNotFoundException notFoundException){
            throw new Exception("ALERTA =====> Fitxer incorrecte o inexistent.");
        } catch (Exception exception){
            throw new Exception(exception.getMessage());
        }
    }

    /**
     *
     * Aquest mètode entrarà un cotxe al parking y comprovarà si está ple es parking o si ja es al parking, o si es una
     * matrícula correcte. Tambè llançara una excepció si detecta que es un garrulo.
     *
     * @param matricula la matrícula des cotxe.
     * @return el nombre de plaça que ha ocupat.
     * @throws Exception si detecta que el parking està ple o la matrícula es de format incorrecte o si ja es al parking.
     */
    public int entraCotxe(String matricula) throws Exception {

        if(getPlacesLliures(TipusPlacesParking.No_Discapacitat) == 0){
            throw new Exception("Parking ple.");
        }

        if(isMatriculaValida(matricula) && !isMatriculaExistent(matricula) && getPlacesLliures(TipusPlacesParking.No_Discapacitat) != 0){
            sercarLloc(matricula,TipusPlacesParking.No_Discapacitat);
            this.matricules.put(matricula,TipusPlacesParking.No_Discapacitat);
        } else {
            throw new Exception("El cotxe ja està al parking. No pot entrar.");
        }

        comprobarEstat();
        comprobarIntegritat(matricula);

        return this.vehicles.get(matricula);
    }

    /**
     *
     * Aquest mètode entrarà un cotxe discapacitat al parking y comprovarà si ja es al parking, o si es una
     * matrícula correcte. Tambè llançara una excepció si detecta que el parking per discapacitats esta ple i s'aficarà
     * a una plaçaa per persones no discapacitades.
     *
     * @param matricula la matrícula des cotxe.
     * @return el nombre de plaçaa que ha ocupat.
     * @throws Exception si detecta que la matrícula es de format incorrecte o si ja es al parking.
     */
    public int entraCotxeDiscapacitat(String matricula) throws Exception {
        if(getPlacesLliures(TipusPlacesParking.Discapacitat) == 0){
            throw new Exception("Parking ple.");
        }
        if(isMatriculaValida(matricula) && !isMatriculaExistent(matricula)){
            sercarLloc(matricula,TipusPlacesParking.Discapacitat);
            this.matricules.put(matricula,TipusPlacesParking.Discapacitat);
        } else {
            throw new Exception("El cotxe ja està al parking. No pot entrar.");
        }
        comprobarEstat();
        comprobarIntegritat(matricula);
        return this.vehicles.get(matricula);
    }

    /**
     *
     * Comprovarà si el cotxe està al parking y si està sortira del parking.
     *
     * @param matricula la matrícula del cotxe que ha de sortir.
     * @throws Exception si la matrícula es de format incorrecte o el cotxe no està al parking o no es de tipus no discapacitat.
     */
    public void surtCotxe(String matricula) throws Exception {
        if(isMatriculaExistent(matricula) && this.matricules.get(matricula) == TipusPlacesParking.No_Discapacitat && isMatriculaValida(matricula)){
            this.vehicles.remove(matricula);
            this.matricules.remove(matricula);
        } else {
            throw new Exception("El cotxe no és al parking.");
        }
    }

    /**
     *
     * Comprovarà si el cotxe està al parking y si está sortira del parking.
     *
     * @param matricula la matrícula del cotxe que ha de sortir.
     * @throws Exception si la matrícula es de format incorrecte o el cotxe no està al parking o no es de tipus no discapacitat.
     */
    public void surtCotxeDiscapacitat(String matricula) throws Exception {
        if(isMatriculaExistent(matricula) && this.matricules.get(matricula) == TipusPlacesParking.Discapacitat && isMatriculaValida(matricula)){
            this.vehicles.remove(matricula);
            this.matricules.remove(matricula);
        } else {
            throw new Exception("El cotxe no és al parking.");
        }
    }

    /**
     *
     * Tornarà el nombre de places ocupades segons el tipus de persones que donís per paràmetres.
     *
     * @param tipus tipus de persones, pot ser no_discapacitat o discapacitat.
     * @return el nombre de places ocupades.
     */
    public int getPlacesOcupades(TipusPlacesParking tipus) throws Exception {
        int counter = 0;
        for(Integer placa : this.vehicles.values()){
            if(this.places.get(placa) == tipus){
                counter++;
            }
        }
        return counter;
    }

    /**
     *
     * Tornará el nombre de places lliures segons el tipus de persones que pasis per parámetres.
     *
     * @param tipus tipus de persones, pot ser no_discapacitat o discapacitat.
     * @return  el nombre de places lliures.
     */
    public int getPlacesLliures(TipusPlacesParking tipus) throws Exception {
        int counter = 0;
        for(Integer placa : this.places.keySet()){
            if(this.places.get(placa) == tipus && this.vehicles.containsValue(placa)){
                counter++;
            }
        }
        return (tipus == TipusPlacesParking.No_Discapacitat) ? (this.placesNormals - counter) : (this.placesDiscapacitats - counter);
    }

    /**
     *
     * Genera un "mapa" del parking segons els paràmetres d'entrada en el constructor, si el
     * nombre de discapacitats supera 10, repartirà les plaçes entre 3 y les repartirà entre grups de
     * la tercera part.
     *
     * @return un mapa del parking.
     */
    private HashMap<Integer,TipusPlacesParking> generarParking() {
        HashMap<Integer,TipusPlacesParking> output = new HashMap<>();
        float numberOfNormalPlaces = (float)this.placesNormals / 3;
        float numberOfDisabledPlaces = (float)this.placesDiscapacitats / 3;
        float remainOfDisabled = this.placesDiscapacitats - ((int)numberOfDisabledPlaces * 3);
        int counter = 1;
        int disabledCounter = 1;
        boolean disabled = false;
        for(int index = 1; index <= (this.placesNormals + this.placesDiscapacitats); index++){
            if(this.placesDiscapacitats <= 5 && disabledCounter <= this.placesDiscapacitats){
                output.put(index,TipusPlacesParking.Discapacitat);
                disabledCounter++;
            } else if(disabled && this.placesDiscapacitats > 5){
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
        return output;
    }

    /**
     *
     * Comprovarà l'estat del parking y si supera el 85% el donarà per pantalla una sola vegada,
     * aquest métode es per ser utilitzat després de ficar un cotxe.
     *
     * @throws Exception si la capacitat supera el 85%.
     */
    private void comprobarEstat() throws Exception {
        if(getTipusParkingEstat(TipusPlacesParking.No_Discapacitat) >= 85 && !this.ocupacioNoDiscapacitats){
            this.ocupacioNoDiscapacitats = true;
            throw new Exception("Ocupació de places per no discapacitats supera el 85%.");
        } else if(getTipusParkingEstat(TipusPlacesParking.Discapacitat) >= 85 && !this.ocupacioDiscapacitats){
            this.ocupacioDiscapacitats = true;
            throw new Exception("Ocupació de places per discapacitats supera el 85%.");
        }
    }

    /**
     *
     * Sercarà puesto per un cotxe amb un nombre aleatori, si se compleixen les condicions,
     * ficarà el cotxe al lloc, sino es farà una recursivitat fins encontrar un lloc adecuat.
     * Si el parking está ple no sercara lloc y retornara una excepció.
     *
     * @param matricula la matrícula del cotxe.
     * @param tipus el tipus de matrícula que es.
     * @throws Exception si el parking esta ple.
     */
    private void sercarLloc(String matricula, TipusPlacesParking tipus) throws Exception {
        int place = (int)(Math.random() * (this.placesNormals + this.placesDiscapacitats) + 1);
        if(tipus == TipusPlacesParking.No_Discapacitat && getTipusParkingEstat(tipus) == 100) {
            throw new Exception("ALERTA ====> Parking ple");
        } else {
            if(getTipusParkingEstat(tipus) == 100 && tipus == TipusPlacesParking.Discapacitat){
                sercarLloc(matricula,TipusPlacesParking.No_Discapacitat);
            } else if(this.vehicles.containsValue(place)){
                sercarLloc(matricula,tipus);
            } else if(this.places.get(place) != tipus){
                int garruloProbability = (int)(Math.random() * 100 + 1);
                if(garruloProbability <= 15 && this.places.get(place) == TipusPlacesParking.Discapacitat){
                    this.vehicles.put(matricula,place);
                } else {
                    sercarLloc(matricula,tipus);
                }
            } else {
                this.vehicles.put(matricula,place);
            }
        }
    }

    /**
     *
     * Comprovarà l'integritat de les plaçes que hi ha al parking, si detecta que hi ha una persona
     * no discapacitada en un lloc per discapacitats, tornarà una excepció diguent que ha trobat un garrulo
     * amb el seu nombre de plaçaa.
     *
     * @param matricula la matricula per comprobar l'integritat de la plaça.
     * @throws Exception si detecta que és un garrulo.
     */
    private void comprobarIntegritat(String matricula) throws Exception {
        if(this.places.get(this.vehicles.get(matricula)) != this.matricules.get(matricula) && this.matricules.get(matricula) == TipusPlacesParking.No_Discapacitat){
            throw new Exception("Garrulo detected!!! Ha aparcat a la plaça: " + this.vehicles.get(matricula));
        } else if(this.places.get(this.vehicles.get(matricula)) == TipusPlacesParking.No_Discapacitat && this.matricules.get(matricula) == TipusPlacesParking.Discapacitat) {
            throw new Exception("Parking per discapacitats ple. Ha ocupat plaça normal num: " + this.vehicles.get(matricula));
        }
    }

    /**
     *
     * Retornarà el percentatge de la ocupació del parking segons el seu tipus.
     *
     * @param tipus el tipus de lloc per obtenir el percentatge.
     * @return el percentatge d'ocupació d'un tipus.
     */
    private float getTipusParkingEstat(TipusPlacesParking tipus) throws Exception {
        return (tipus == TipusPlacesParking.No_Discapacitat) ?  ((float)getPlacesOcupades(tipus) * 100 / this.placesNormals) : ((float)getPlacesOcupades(tipus) * 100 / this.placesDiscapacitats);
    }

    /**
     *
     * Retornara true si la matricula existeix en el parking, false si no existeix.
     *
     * @param matricula la matrícula per comprobar.
     * @return true si existeix, false si no existeix.
     */
    private boolean isMatriculaExistent(String matricula) {
        return this.vehicles.containsKey(matricula);
    }

    /**
     *
     * Retornata true si la matrícula es valida segons el pattern, si no es correcte retornara una excepció de
     * matrícula incorrecte.
     *
     * @param matricula la matrícula per comprobar.
     * @return true si es correcte.
     * @throws Exception si la matrícula no es correcte.
     */
    private boolean isMatriculaValida(String matricula) throws Exception{
        Pattern pattern = Pattern.compile("[0-9]{4}[A-Z]{3}");
        Matcher matcher = pattern.matcher(matricula);
        if(matcher.find()){
            return true;
        } else {
            throw new Exception("Matrícula incorrecte.");
        }
    }

    /**
     *
     * Comprovarà si la ruta es correcte amb el tipus de fitxer soportat.
     *
     * @param rutaPerValidar la ruta per validar.
     * @return true si es correcte, false si no es correcte.
     */
    private boolean isRutaValida(String rutaPerValidar){
        String[] supportedTypes = new String[]{
                "txt"
        };
        String[] path = rutaPerValidar.replace("\\","/").split("/");
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

}
