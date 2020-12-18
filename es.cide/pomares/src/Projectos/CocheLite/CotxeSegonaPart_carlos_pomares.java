package Projectos.CocheLite;

/*

    Project     Programming21
    Package     Projectos.CocheLite    
    
    Version     1.0      
    Author      Carlos Pomares
    Date        2020-12-11

    DESCRIPTION
    Segunda parte de la práctica de vehículo, donde se aplican nuevos métodos de uso de marchas,
    y complementariamente he añadido diversos métodos que permiten tener un control más amplio del
    vehículo.
    
*/

/**
 * @author Carlos Pomares
 */

enum Marxes {
    MARXA_ENRRERA(-1),
    NEUTRAL(0),
    PRIMERA_MARXA(1),
    SEGONA_MARXA(2),
    TERCERA_MARXA(3),
    CUARTA_MARXA(4),
    QUINTA_MARXA(5),
    SEXTA_MARXA(6);

    private final int valorMarxa;

    Marxes(int marxa){
        this.valorMarxa = marxa;
    }

    public int getValorMarxa(){
        return this.valorMarxa;
    }

    public static Marxes getCanvi(int marxa){
        Marxes canviActual = null;
        for(Marxes canvi : Marxes.values()) {
            if(canvi.getValorMarxa() == marxa){
                canviActual = canvi;
            }
        }
        return canviActual;
    }
}

public class CotxeSegonaPart_carlos_pomares extends Cotxe_carlos_pomares {

    private final int MAX_NIVELL_AIREACONDICIONAT = 3;

    // Marcha actual
    private int marxaActual;
    // Estado de la capota
    private boolean capota;
    // Estado del aire acondicionado
    private boolean aireAcondicionat;
    // Nivel del aire acondicionado
    private int nivellAireAcondicionat;
    // Estado del cinturon
    private boolean cinturo;
    // Estado de la luces
    private boolean llumns;

    /**
     * El método constructor permite introducir un vehículo
     * con su propia marca, modelo y el tipo de transmisión.
     * Para ello utilizando la clase padre que nos proporciona los accesos e
     * Inicializamos el estadoDelMotor en EstatsMotorCotxe.Aturat.
     *
     * @param marca        La marca del vehículo.
     * @param modelo       El modelo del vehículo.
     * @param transmission El tipo de transmisión según el ENUM TipusCanvi.
     */
    public CotxeSegonaPart_carlos_pomares(String marca, String modelo, TipusCanvi transmission) {
        super(marca, modelo, transmission);
        this.marxaActual = 0;
    }

    /**
     *
     * Cambia la marcha actual de la transmisión automático.
     * Permite subir o bajar la marcha teniendo en cuenta los límites de la transmisión.
     *
     * @param tipusOperacio el tipo de operación que se desea realizar, puede ser '+' o '-'.
     * @throws Exception Si el coche no está arrancado o el tipo de operación no es la correcta o se han alcanzado los límites.
     */
    public void CanviarMarxaAutomatic(char tipusOperacio) throws Exception {
        if(this.comprovaMotor() == EstatsMotorCotxe.EnMarxa && this.tipuscanvi == TipusCanvi.CanviAutomatic && (tipusOperacio=='+' || tipusOperacio=='-')){
            if(tipusOperacio=='+'){
                if(this.marxaActual == 1)
                    throw new RuntimeException("Máxima marcha.");
                this.marxaActual = Marxes.getCanvi(this.marxaActual + 1).getValorMarxa();
            } else {
                if(this.marxaActual == -1)
                    throw new RuntimeException("Mínima marcha.");
                this.marxaActual = Marxes.getCanvi(this.marxaActual - 1).getValorMarxa();
            }
        } else if(this.comprovaMotor() == EstatsMotorCotxe.Aturat){
            throw new Exception("Coche no arrancado.");
        } else {
            throw new Exception("Este coche no es automático.");
        }
    }

    /**
     *
     * Cambia la marcha actual de la transmisión manual.
     * Permite subir o bajar la marcha teniendo en cuenta los límites de la transmisión.
     *
     * @param tipusOperacio el tipo de operación que se desea realizar, puede ser '+' o '-'
     * @throws Exception Si el coche no está arrancado o el tipo de operación no es la correcta o se han alcanzado los límites.
     */
    public void CanviarMarxaManual(char tipusOperacio) throws Exception {
        if(this.comprovaMotor() == EstatsMotorCotxe.EnMarxa && this.tipuscanvi == TipusCanvi.CanviManual && (tipusOperacio=='+' || tipusOperacio=='-')){
            if(tipusOperacio == '+'){
                if(this.marxaActual == 6)
                    throw new RuntimeException("Máxima marcha.");
                this.marxaActual = Marxes.getCanvi(this.marxaActual + 1).getValorMarxa();
            } else {
                if(this.marxaActual == -1)
                    throw new RuntimeException("Mínima marcha.");
                this.marxaActual = Marxes.getCanvi(this.marxaActual - 1).getValorMarxa();
            }
        } else if(this.comprovaMotor() == EstatsMotorCotxe.Aturat){
            throw new Exception("Coche no arrancado.");
        } else {
            throw new Exception("Este coche no es manual.");
        }
    }

    /**
     *
     * Permite cambiar el estado de la capota al contrario de su estado actual.
     *
     */
    public void cambiarEstatCapota(){
        this.capota = !this.capota;
    }

    /**
     *
     * Permite ponerse el cinturón.
     *
     * @throws Exception si el cinturón ya esta puesto.
     */
    public void posarCinturo() throws Exception {
        if(this.cinturo)
            throw new Exception("Cinturón ya puesto.");
        this.cinturo = true;
    }

    /**
     *
     * Permite quitarse el cinturon.
     *
     * @throws Exception si el cinturon ya está quitado.
     */
    public void quitarCinturo() throws Exception {
        if(!this.cinturo)
            throw new Exception("Cinturón ya quitado.");
        this.cinturo = false;
    }

    /**
     *
     * Permite poner el aire acondicionado.
     *
     * @throws Exception si el aire acondicionado está encendido.
     */
    public void posarAireAcondicionat() throws Exception {
        if(this.aireAcondicionat)
            throw new Exception("Aire acondicionado ya encendido.");
        this.aireAcondicionat = true;
    }

    /**
     *
     * Permite quitar el aire acondicionado.
     *
     * @throws Exception si el aire acondicionado ya está apagado.
     */
    public void quitarAireAcondicionat() throws Exception {
        if(!this.aireAcondicionat)
            throw new Exception("Aire acondicionado ya apagado.");
        this.aireAcondicionat = false;
    }

    /**
     *
     * Permite cambiar el nivel del aire acondicionado.
     *
     * @param tipusOperacio el tipo de operacion que se quiere realizar, puede ser '+' o '-'.
     * @throws Exception si se superan los limites de velocidad o el aire no está encendido.
     */
    public void cambiarNivellAireAcondicionat(char tipusOperacio) throws Exception {
        if(tipusOperacio == '+' || tipusOperacio == '-'){
            if(tipusOperacio == '+' && this.nivellAireAcondicionat < this.MAX_NIVELL_AIREACONDICIONAT){
                this.nivellAireAcondicionat++;
            } else if(tipusOperacio == '-' && this.nivellAireAcondicionat > 1){
                this.nivellAireAcondicionat--;
            } else {
                throw new Exception("Máximo nivel o mínimo nivel de velocidad.");
            }
        } else if(!this.aireAcondicionat){
            throw new Exception("Aire acondicionado no encendido.");
        }
    }

    /**
     *
     * Permite encender las luces del vehiculo.
     *
     * @throws Exception si las luces ya están encendidas.
     */
    public void encendreLlum() throws Exception {
        if(this.llumns)
            throw new Exception("Luces ya encendidas.");
        this.llumns = true;
    }

    /**
     *
     * Permite apagar las luces del vehículo.
     *
     * @throws Exception si las luces ya están apagadas.
     */
    public void apagarLlum() throws Exception {
        if(!this.llumns)
            throw new Exception("Luces ya apagadas.");
        this.llumns = false;
    }

}
