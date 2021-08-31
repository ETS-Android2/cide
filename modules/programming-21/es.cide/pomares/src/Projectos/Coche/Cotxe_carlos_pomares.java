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

/**
 * @author Carlos Pomares
 */


public class Cotxe_carlos_pomares extends CotxeAbstracte {

    // Constants
    final private static int MAX_VELOCITY = 250;
    final private static int VELOCITAT_INCREMENTADOR = 5;
    final private static int REVOLUCIONS_ENMARXA = 930;
    final private static int MAX_MARXASECUENCIAL = 7;
    final private boolean DESCAPOTABLE;

    // Vehicle individual
    private int revolutions = 1;
    private int velocity = 0;
    private boolean reverse;
    private boolean hood;
    private EstatsMotorCotxe carState = EstatsMotorCotxe.Aturat;
    private int gear = 0;

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

    public void accelerate() throws Exception {
        if(this.velocity >= 0 && this.velocity <= MAX_VELOCITY){
            if(this.tipuscanvi == TipusCanvi.CanviManual){
                switch (this.gear){
                    case 0 -> {
                        if(this.reverse && this.velocity <= 5){
                            velocityIncrement();
                        } else {
                            throw new RuntimeException("Necesita poner una marxa o Ha superat la maxima velocitat.");
                        }
                    }
                    // First gear
                    case 1 -> {
                        if(this.velocity <= 10){
                            velocityIncrement();
                        } else {
                            throw new RuntimeException("Necesites aumentar marxa.");
                        }
                    }
                    case 2 -> {
                        if(this.velocity <= 25){
                            velocityIncrement();
                        } else {
                            throw new RuntimeException("Necesites aumentar marxa.");
                        }
                    }
                    case 3 -> {
                        if(this.velocity <= 50){
                            velocityIncrement();
                        } else {
                            throw new RuntimeException("Necesites aumentar marxa.");
                        }
                    }
                    case 4 -> {
                        if(this.velocity <= 75){
                            velocityIncrement();
                        } else {
                            throw new RuntimeException("Necesites aumentar marxa.");
                        }
                    }
                    case 5 -> {
                        if(this.velocity <= 100){
                            velocityIncrement();
                        } else {
                            throw new RuntimeException("Necesites aumentar marxa.");
                        }
                    }
                    case 6 -> {
                        if(this.velocity <= 145){
                            velocityIncrement();
                        } else {
                            throw new RuntimeException("Necesites aumentar marxa.");
                        }
                    }
                    case 7 -> {
                        if(this.velocity <= (MAX_VELOCITY - 5)){
                            velocityIncrement();
                        } else {
                            throw new RuntimeException("Necesites aumentar marxa.");
                        }
                    }
                    default -> throw new RuntimeException("Error");

                }
            } else if(this.tipuscanvi == TipusCanvi.CanviAutomatic){
                if(this.carState == EstatsMotorCotxe.EnMarxa){
                    if(this.velocity == 0 && this.gear == 0){
                        this.gearUp();
                        this.velocityIncrement();
                    } else if(this.gear >= 1 && this.gear <= MAX_MARXASECUENCIAL){
                        switch (this.velocity){
                            case 15, 25, 50, 80, 90, 120 -> {
                                gearUp();
                                velocityIncrement();
                            }
                            case 250 -> {
                                throw new RuntimeException("Maxima velocitat.");
                            }
                            default -> velocityIncrement();
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

    public void deccelerate() throws Exception {
        if(this.velocity >= 5){
            if(this.tipuscanvi == TipusCanvi.CanviAutomatic){
                switch (this.gear){
                    case 0 -> {
                        if(reverse){
                            velocityDecrement();
                        } else {
                            throw new RuntimeException("Error");
                        }
                    }
                    case 2 -> {
                        if(this.velocity <= 10) {
                            gearDown();
                        }
                        velocityDecrement();
                    }
                    case 3 -> {
                        if(this.velocity <= 20) {
                            gearDown();
                        }
                        velocityDecrement();
                    }
                    case 4 -> {
                        if(this.velocity <= 40) {
                            gearDown();
                        }
                        velocityDecrement();
                    }
                    case 5 -> {
                        if(this.velocity <= 60) {
                            gearDown();
                        }
                        velocityDecrement();
                    }
                    case 6 -> {
                        if(this.velocity <= 80) {
                            gearDown();
                        }
                        velocityDecrement();
                    }
                    case 7 -> {
                        if(this.velocity <= 110) {
                            gearDown();
                        }
                        velocityDecrement();
                    }
                    default -> velocityDecrement();
                }
            } else {
                velocityDecrement();
            }
        } else {
            throw new RuntimeException("Ya estas aturat.");
        }
    }

    public boolean canPutReverse() throws Exception {
        if(this.velocity <= 5 && this.gear <= 1){
            return true;
        } else {
            throw new RuntimeException("No pots posar marxa enrrera.");
        }
    }

    public void gearUp() throws Exception {
        if(this.gear <= MAX_MARXASECUENCIAL) {
            if(this.reverse){
                if(this.velocity == 0 || (this.velocity >= 0 && this.velocity <= 5)){
                    this.gear += 1;
                    this.reverse = false;
                }
            } else {
                switch (this.gear) {
                    case 0 -> {
                        if(this.velocity == 0){
                            this.gear += 1;
                        } else {
                            throw new RuntimeException("Error.");
                        }
                    }
                    case 1 -> {
                        if(this.velocity >= 5 && this.velocity <= 15){
                            this.gear += 1;
                        } else {
                            throw new RuntimeException("Velocitat insuficient.");
                        }
                    }
                    case 2 -> {
                        if(this.velocity >= 20 && this.velocity <= 30){
                            this.gear += 1;
                        } else {
                            throw new RuntimeException("Velocitat insuficient.");
                        }
                    }
                    case 3 -> {
                        if(this.velocity >= 35 && this.velocity <= 55){
                            this.gear += 1;
                        } else {
                            throw new RuntimeException("Velocitat insuficient.");
                        }
                    }
                    case 4 -> {
                        if(this.velocity >= 60 && this.velocity <= 85){
                            this.gear += 1;
                        } else {
                            throw new RuntimeException("Velocitat insuficient.");
                        }
                    }
                    case 5 -> {
                        if(this.velocity >= 90 && this.velocity <= 110){
                            this.gear += 1;
                        } else {
                            throw new RuntimeException("Velocitat insuficient.");
                        }
                    }
                    case 6 -> {
                        if(this.velocity >= 115 && this.velocity <= 150){
                            this.gear += 1;
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

    public void gearDown() throws Exception {
        if(this.gear <= MAX_MARXASECUENCIAL) {
            switch (this.gear) {
                case 0 -> {
                    if(this.velocity >= 0 && this.velocity <= 5){
                        this.reverse = true;
                    } else {
                        throw new RuntimeException("Error.");
                    }
                }
                case 1 -> {
                    if(this.velocity <= 15){
                        this.gear -= 1;
                    } else {
                        throw new RuntimeException("Molta velocitat.");
                    }
                }
                case 2 -> {
                    if(this.velocity <= 25){
                        this.gear -= 1;
                    } else {
                        throw new RuntimeException("Molta velocitat.");
                    }
                }
                case 3 -> {
                    if(this.velocity <= 45){
                        this.gear -= 1;
                    } else {
                        throw new RuntimeException("Molta velocitat.");
                    }
                }
                case 4 -> {
                    if(this.velocity <= 55){
                        this.gear -= 1;
                    } else {
                        throw new RuntimeException("Molta velocitat.");
                    }
                }
                case 5 -> {
                    if(this.velocity <= 70){
                        this.gear -= 1;
                    } else {
                        throw new RuntimeException("Molta velocitat.");
                    }
                }
                case 6 -> {
                    if(this.velocity <= 100){
                        this.gear -= 1;
                    } else {
                        throw new RuntimeException("Molta velocitat.");
                    }
                }
                case 7 -> {
                    if(this.velocity <= 130){
                        this.gear -= 1;
                    } else {
                        throw new RuntimeException("Molta velocitat.");
                    }
                }
                default -> throw new RuntimeException("Error.");
            }
        }
    }

    private void velocityIncrement(){
        if(this.velocity <= (MAX_VELOCITY - 5)){
            this.velocity += VELOCITAT_INCREMENTADOR;
            this.revolutions = (REVOLUCIONS_ENMARXA + ((this.velocity / 10) * this.gear));
        }
    }

    private void velocityDecrement(){
        if(this.velocity >= 5){
            this.velocity -= VELOCITAT_INCREMENTADOR;
        }
    }

    public void putHood() throws Exception {
        if(this.hood){
            throw new Exception("Ya esta posada.");
        } else {
            this.hood = true;
            this.components.replace("Capota",0,1);
        }
    }

    public void takeOffHood() throws Exception {
        if(this.hood){
            this.hood = false;
            this.components.replace("Capota",1,0);
        } else {
            throw new Exception("Ya esta quitada.");
        }
    }

    public void changeAirLevel() throws Exception {

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

    public void changeWindScreenLevel() throws Exception {

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
        if(this.carState == EstatsMotorCotxe.EnMarxa){
            throw new Exception("Error, es cotxe ja esta en marxa.");
        } else {
            this.carState = EstatsMotorCotxe.EnMarxa;
        }
    }

    @Override
    public void aturarMotor() throws Exception {
        if(this.carState == EstatsMotorCotxe.Aturat){
            throw new Exception("Error, es cotxe ja esta aturat.");
        } else {
            this.carState = EstatsMotorCotxe.Aturat;
        }
    }

    @Override
    public EstatsMotorCotxe comprovaMotor() {
        return this.carState;
    }

    public int getVelocity(){
        return this.velocity;
    }

    public String getBrand(){
        return this.marca;
    }

    public String getModel(){
        return this.model;
    }

    public String getTransmissionType(){
        return this.tipuscanvi.toString();
    }

    public HashMap<String,Integer> getComponents() {
        return components;
    }

    public boolean getConvertible(){
        return this.DESCAPOTABLE;
    }

    public boolean getHoodState() { return this.hood; }

    public boolean getReverse(){
        return this.reverse;
    }

    public int getGear(){
        return this.gear;
    }

    @Override
    public int getRevolutions() {
        if(this.carState == EstatsMotorCotxe.Aturat){
            return 0;
        } else {
            if(velocity == 0){
                return REVOLUCIONS_ENMARXA;
            } else {
                return this.revolutions;
            }
        }
    }
}
