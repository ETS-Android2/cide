package Projectos.Coche;

/*

    Project     Programming21
    Package     Projectos.Coche    
    
    Version     1.0      
    Author      Carlos Pomares
    Date        2020-12-04

    DESCRIPTION
    
*/

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Carlos Pomares
 */


public class Cotxe_carlos_pomares extends CotxeAbstracte {

    // Constants
    final private static int MAX_VELOCITAT = 250;
    final private static int VELOCITAT_INCREMENTADOR = 5;
    final private static int REVOLUCIONS_ENMARXA = 930;
    final private static int MAX_MARXASECUENCIAL = 7;
    final private boolean DESCAPOTABLE;

    // Vehicle individual
    private int revolucions = 1;
    private int velocitat = 0;
    private boolean reverse;
    private boolean capota;
    private boolean cinturo;
    private EstatsMotorCotxe estatCoxte = EstatsMotorCotxe.Aturat;
    private int marxaActual = 0;

    // Components
    final private LinkedHashMap<String,Integer> components = new LinkedHashMap<>();

    public Cotxe_carlos_pomares(String marca, String model, TipusCanvi tipuscanvi, boolean descapotable) {
        super(marca,model,tipuscanvi);
        this.DESCAPOTABLE = descapotable;

        components.put("Aire acondicionat",0);
        if(DESCAPOTABLE){
            components.put("Capota",0);
        }
        components.put("Limpia parabrisas",0);

    }
    public void accelerar() throws Exception {
        if(this.velocitat >= 0 && this.velocitat <= MAX_VELOCITAT){
            if(this.tipuscanvi == TipusCanvi.CanviManual){
                switch (this.marxaActual){
                    case 0 -> {
                        if(this.reverse && this.velocitat <= 5){
                            incrementarVelocitat();
                        } else {
                            throw new RuntimeException("Necesita poner una marxa o Ha superat la maxima velocitat.");
                        }
                    }
                    // First gear
                    case 1 -> {
                        if(this.velocitat <= 10){
                            incrementarVelocitat();
                        } else {
                            throw new RuntimeException("Necesites aumentar marxa.");
                        }
                    }
                    case 2 -> {
                        if(this.velocitat <= 25){
                            incrementarVelocitat();
                        } else {
                            throw new RuntimeException("Necesites aumentar marxa.");
                        }
                    }
                    case 3 -> {
                        if(this.velocitat <= 50){
                            incrementarVelocitat();
                        } else {
                            throw new RuntimeException("Necesites aumentar marxa.");
                        }
                    }
                    case 4 -> {
                        if(this.velocitat <= 75){
                            incrementarVelocitat();
                        } else {
                            throw new RuntimeException("Necesites aumentar marxa.");
                        }
                    }
                    case 5 -> {
                        if(this.velocitat <= 100){
                            incrementarVelocitat();
                        } else {
                            throw new RuntimeException("Necesites aumentar marxa.");
                        }
                    }
                    case 6 -> {
                        if(this.velocitat <= 145){
                            incrementarVelocitat();
                        } else {
                            throw new RuntimeException("Necesites aumentar marxa.");
                        }
                    }
                    case 7 -> {
                        if(this.velocitat <= (MAX_VELOCITAT - 5)){
                            incrementarVelocitat();
                        } else {
                            throw new RuntimeException("Necesites aumentar marxa.");
                        }
                    }
                    default -> throw new RuntimeException("Error");

                }
            } else if(this.tipuscanvi == TipusCanvi.CanviAutomatic){
                if(this.estatCoxte == EstatsMotorCotxe.EnMarxa){
                    if(this.velocitat == 0 && this.marxaActual == 0){
                        this.incrementarMarxa();
                        this.incrementarVelocitat();
                    } else if(this.marxaActual >= 1 && this.marxaActual <= MAX_MARXASECUENCIAL){
                        switch (this.velocitat){
                            case 15, 25, 50, 80, 90, 120 -> {
                                incrementarMarxa();
                                incrementarVelocitat();
                            }
                            case 250 -> {
                                throw new RuntimeException("Maxima velocitat.");
                            }
                            default -> incrementarVelocitat();
                        }
                    }
                }
            } else {
                throw new Exception("Error.");
            }
        } else {
            throw new Exception("No se pot accelerar.");
        }
    }
    public void frenar() throws Exception {
        if(this.velocitat >= 5){
            if(this.tipuscanvi == TipusCanvi.CanviAutomatic){
                switch (this.marxaActual){
                    case 0 -> {
                        if(reverse){
                            decrementarVelocitat();
                        } else {
                            throw new RuntimeException("Error");
                        }
                    }
                    case 2 -> {
                        if(this.velocitat <= 10) {
                            decrementarMarxa();
                        }
                        decrementarVelocitat();
                    }
                    case 3 -> {
                        if(this.velocitat <= 20) {
                            decrementarMarxa();
                        }
                        decrementarVelocitat();
                    }
                    case 4 -> {
                        if(this.velocitat <= 40) {
                            decrementarMarxa();
                        }
                        decrementarVelocitat();
                    }
                    case 5 -> {
                        if(this.velocitat <= 60) {
                            decrementarMarxa();
                        }
                        decrementarVelocitat();
                    }
                    case 6 -> {
                        if(this.velocitat <= 80) {
                            decrementarMarxa();
                            decrementarVelocitat();
                        }
                    }
                    case 7 -> {
                        if(this.velocitat <= 110) {
                            decrementarMarxa();
                        }
                        decrementarVelocitat();
                    }
                    default -> decrementarVelocitat();
                }
            } else {
                decrementarVelocitat();
            }
        } else {
            throw new RuntimeException("Ya estas aturat.");
        }
    }
    private boolean pucPosarMarxaEnrrera() throws Exception {
        if(this.velocitat <= 5 && this.marxaActual <= 1){
            return true;
        } else {
            throw new RuntimeException("No pots posar marxa enrrera.");
        }
    }
    public void incrementarMarxa() throws Exception {
        if(this.marxaActual <= MAX_MARXASECUENCIAL) {
            if(this.reverse){
                if(this.velocitat == 0 || (this.velocitat >= 0 && this.velocitat <= 5)){
                    this.marxaActual += 1;
                    this.reverse = false;
                }
            } else {
                switch (this.marxaActual) {
                    case 0 -> {
                        if(this.velocitat == 0){
                            this.marxaActual += 1;
                        } else {
                            throw new RuntimeException("Error.");
                        }
                    }
                    case 1 -> {
                        if(this.velocitat >= 5 && this.velocitat <= 15){
                            this.marxaActual += 1;
                        } else {
                            throw new RuntimeException("Velocitat insuficient.");
                        }
                    }
                    case 2 -> {
                        if(this.velocitat >= 20 && this.velocitat <= 30){
                            this.marxaActual += 1;
                        } else {
                            throw new RuntimeException("Velocitat insuficient.");
                        }
                    }
                    case 3 -> {
                        if(this.velocitat >= 35 && this.velocitat <= 55){
                            this.marxaActual += 1;
                        } else {
                            throw new RuntimeException("Velocitat insuficient.");
                        }
                    }
                    case 4 -> {
                        if(this.velocitat >= 60 && this.velocitat <= 85){
                            this.marxaActual += 1;
                        } else {
                            throw new RuntimeException("Velocitat insuficient.");
                        }
                    }
                    case 5 -> {
                        if(this.velocitat >= 90 && this.velocitat <= 110){
                            this.marxaActual += 1;
                        } else {
                            throw new RuntimeException("Velocitat insuficient.");
                        }
                    }
                    case 6 -> {
                        if(this.velocitat >= 115 && this.velocitat <= 150){
                            this.marxaActual += 1;
                        } else {
                            throw new RuntimeException("Velocitat insuficient.");
                        }
                    }
                    case 7 -> throw new RuntimeException("Máxima marxa.");
                    default -> throw new RuntimeException("Error.");
                }
            }
        }
    }
    public void decrementarMarxa() throws Exception {
        if(this.marxaActual <= MAX_MARXASECUENCIAL) {
            switch (this.marxaActual) {
                case 0 -> {
                    if(this.velocitat >= 0 && this.velocitat <= 5){
                        this.reverse = true;
                    } else {
                        throw new RuntimeException("Error.");
                    }
                }
                case 1 -> {
                    if(this.velocitat <= 15){
                        this.marxaActual -= 1;
                    } else {
                        throw new RuntimeException("Molta velocitat.");
                    }
                }
                case 2 -> {
                    if(this.velocitat <= 25){
                        this.marxaActual -= 1;
                    } else {
                        throw new RuntimeException("Molta velocitat.");
                    }
                }
                case 3 -> {
                    if(this.velocitat <= 45){
                        this.marxaActual -= 1;
                    } else {
                        throw new RuntimeException("Molta velocitat.");
                    }
                }
                case 4 -> {
                    if(this.velocitat <= 55){
                        this.marxaActual -= 1;
                    } else {
                        throw new RuntimeException("Molta velocitat.");
                    }
                }
                case 5 -> {
                    if(this.velocitat <= 70){
                        this.marxaActual -= 1;
                    } else {
                        throw new RuntimeException("Molta velocitat.");
                    }
                }
                case 6 -> {
                    if(this.velocitat <= 100){
                        this.marxaActual -= 1;
                    } else {
                        throw new RuntimeException("Molta velocitat.");
                    }
                }
                case 7 -> {
                    if(this.velocitat <= 130){
                        this.marxaActual -= 1;
                    } else {
                        throw new RuntimeException("Molta velocitat.");
                    }
                }
                default -> throw new RuntimeException("Error.");
            }
        }
    }
    private void incrementarVelocitat(){
        if(this.velocitat <= (MAX_VELOCITAT - 5)){
            this.velocitat += VELOCITAT_INCREMENTADOR;
            this.revolucions = (REVOLUCIONS_ENMARXA + ((this.velocitat / 10) * this.marxaActual));
        }
    }
    private void decrementarVelocitat(){
        if(this.velocitat >= 5){
            this.velocitat -= VELOCITAT_INCREMENTADOR;
        }
    }
    public void posarCapota() throws Exception {
        if(this.capota){
            throw new Exception("Ya esta posada.");
        } else {
            this.capota = true;
            this.components.replace("Capota",0,1);
        }
    }
    public void quitarCapota() throws Exception {
        if(this.capota){
            this.capota = false;
            this.components.replace("Capota",1,0);
        } else {
            throw new Exception("Ya esta quitada.");
        }
    }
    public void cambiarNivellAire() throws Exception {

        String key = "Aire acondicionat";
        int component = this.components.get(key);

        if(component == 2){
            this.components.replace(key,0);
        } else if(component == 1){
            this.components.replace(key,2);
        } else if(component == 0){
            this.components.replace(key,1);
        }
    }
    public void cambiarNivellLimpia() throws Exception {

        String key = "Limpia parabrisas";

        int component = this.components.get(key);

        if(component == 1){
            this.components.replace(key,0);
        } else if(component == 0){
            this.components.replace(key,1);
        }
    }
    @Override
    public void arrancarMotor() throws Exception {
        if(this.estatCoxte == EstatsMotorCotxe.EnMarxa){
            throw new Exception("Error, es cotxe ja esta en marxa.");
        } else {
            this.estatCoxte = EstatsMotorCotxe.EnMarxa;
        }
    }
    @Override
    public void aturarMotor() throws Exception {
        if(this.estatCoxte == EstatsMotorCotxe.Aturat){
            throw new Exception("Error, es cotxe ja esta aturat.");
        } else {
            this.estatCoxte = EstatsMotorCotxe.Aturat;
        }
    }
    @Override
    public EstatsMotorCotxe comprovaMotor() {
        return this.estatCoxte;
    }
    public int getVelocitat(){
        return this.velocitat;
    }
    public String getMarca(){
        return this.marca;
    }
    public String getModel(){
        return this.model;
    }
    public String getTipusCanvi(){
        return this.tipuscanvi.toString();
    }
    public HashMap<String,Integer> getComponents() {
        return components;
    }
    public boolean getSiEsDescapotable(){
        return this.DESCAPOTABLE;
    }
    public boolean getCapotaEstat() { return this.capota; }
    public boolean getSiEsReverse(){
        return this.reverse;
    }
    public int getMarxaActual(){
        return this.marxaActual;
    }
    @Override
    public int getRevolucions() {
        if(this.estatCoxte == EstatsMotorCotxe.Aturat){
            return 0;
        } else {
            if(velocitat == 0){
                return REVOLUCIONS_ENMARXA;
            } else {
                return this.revolucions;
            }
        }
    }
}
