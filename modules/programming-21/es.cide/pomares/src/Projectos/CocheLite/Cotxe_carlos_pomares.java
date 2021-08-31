package Projectos.CocheLite;

/*

    Project     Programming21
    Package     Projectos.CocheLite    
    
    Version     1.0      
    Author      Carlos Pomares
    Date        2020-12-09

    DESCRIPTION
    heu de crear una classe Cotxe que implementi la interface i que sigui una subclasse de la classe abstracta.
    Aquesta classe s’ha d’anomenar: Cotxe_nom_llinatge.java
    Addicionalment, creeu una classe TestCotxe_nom_llinatge.java que comprovi el correcte funcionament de la classe Cotxe.

    
*/

/**
 * @author Carlos Pomares
 */

public class Cotxe_carlos_pomares extends CotxeAbstracte {

    /**
     * Representa el estado del motor en el cual se encuentra el vehículo.
     */
    private EstatsMotorCotxe estadoDelMotor;

    /**
     *
     * El método constructor permite introducir un vehículo
     * con su propia marca, modelo y el tipo de transmisión.
     * Para ello utilizando la clase padre que nos proporciona los accesos e
     * Inicializamos el estadoDelMotor en EstatsMotorCotxe.Aturat.
     *
     * @param marca La marca del vehículo.
     * @param modelo El modelo del vehículo.
     * @param transmission El tipo de transmisión según el ENUM TipusCanvi.
     */
    public Cotxe_carlos_pomares(String marca, String modelo, TipusCanvi transmission){
        super(marca,modelo,transmission);
        // Por defecto el vehículo está parado.
        estadoDelMotor = EstatsMotorCotxe.Aturat;
    }

    /**
     *
     * Este método nos permite arrancar el motor del vehículo.
     * Nos devolvera error en caso de que el vehículo ya este
     * arrancado.
     *
     * @throws Exception si el vehículo ya está arrancado.
     */
    @Override
    public void arrancarMotor() throws Exception {
        if(comprovaMotor() == EstatsMotorCotxe.EnMarxa){
            throw new Exception("El coche ya está arrancado.");
        } else {
            this.estadoDelMotor = EstatsMotorCotxe.EnMarxa;
        }
    }

    /**
     *
     * El método comprovaMotor nos devolvera el estado actual
     * del motor, siendo este un posible estado según el ENUM
     * EstatsMotorCotxe.
     *
     * @return el estado del motor actual.
     */
    @Override
    public EstatsMotorCotxe comprovaMotor() {
        return this.estadoDelMotor;
    }

    /**
     *
     * El método getRevolutions nos devolvera las revoluciones
     * del vehiculo según en que estado se encuentre.
     *
     * @return Si está arrancado devolvera 930, en caso de estar parado devolvera 0.
     */
    @Override
    public int getRevolutions() {
        if(comprovaMotor() == EstatsMotorCotxe.Aturat){
            return 0;
        } else {
            return 930;
        }
    }

    /**
     *
     * El método aturarMotor permitira parar el motor siempre que
     * este, esté arrancado. Sino tirará una excepción diciendo
     * que está parado.
     *
     * @throws Exception si el vehículo se encuentra parado.
     */
    @Override
    public void aturarMotor() throws Exception {
        if(comprovaMotor() == EstatsMotorCotxe.Aturat){
            throw new Exception("El coche ya está apagado.");
        } else {
            this.estadoDelMotor = EstatsMotorCotxe.Aturat;
        }
    }
}
