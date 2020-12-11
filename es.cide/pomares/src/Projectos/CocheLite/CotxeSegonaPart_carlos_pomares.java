package Projectos.CocheLite;

/*

    Project     Programming21
    Package     Projectos.CocheLite    
    
    Version     1.0      
    Author      Carlos Pomares
    Date        2020-12-11

    DESCRIPTION
    
*/

/**
 * @author Carlos Pomares
 */


public class CotxeSegonaPart_carlos_pomares extends Cotxe_carlos_pomares {

    private int marxaActual;

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

    public void CanviarMarxaAutomatic(char tipusOperacio) throws Exception {
        if(this.comprovaMotor() == EstatsMotorCotxe.EnMarxa && this.tipuscanvi == TipusCanvi.CanviAutomatic && (tipusOperacio=='+' || tipusOperacio=='-')){
            switch (this.marxaActual){
                case 1 -> {
                    if(tipusOperacio=='+'){
                        throw new RuntimeException("Máxima marxa.");
                    } else {
                        this.marxaActual = CanviAutomatic.NEUTRAL.getValorMarxa();
                    }
                }
                case 0 -> {
                    if(tipusOperacio=='+'){
                        this.marxaActual = CanviAutomatic.FORWARD.getValorMarxa();
                    } else {
                        this.marxaActual = CanviAutomatic.REVERSE.getValorMarxa();
                    }
                }
                case -1 -> {
                    if(tipusOperacio=='+'){
                        this.marxaActual = CanviAutomatic.NEUTRAL.getValorMarxa();
                    } else {
                        throw new RuntimeException("Minima marxa.");
                    }
                }
                default -> throw new RuntimeException("Error.");
            }
        } else {
            throw new Exception("Aquest cotxo, no es automatic.");
        }
    }
    public void CanviarMarxaManual(char tipusOperacio) throws Exception {
        if(this.comprovaMotor() == EstatsMotorCotxe.EnMarxa && this.tipuscanvi == TipusCanvi.CanviManual && (tipusOperacio=='+' || tipusOperacio=='-')){
            switch (this.marxaActual){
                case 6 -> {
                    if(tipusOperacio=='+'){
                        throw new RuntimeException("Máxima marxa.");
                    } else {
                        this.marxaActual = CanviManual.QUINTA_MARXA.getValorMarxa();
                    }
                }
                case 5 -> {
                    if(tipusOperacio=='+'){
                        this.marxaActual = CanviManual.SEXTA_MARXA.getValorMarxa();
                    } else {
                        this.marxaActual = CanviManual.CUARTA_MARXA.getValorMarxa();
                    }
                }
                case 4 -> {
                    if(tipusOperacio=='+'){
                        this.marxaActual = CanviManual.QUINTA_MARXA.getValorMarxa();
                    } else {
                        this.marxaActual = CanviManual.CUARTA_MARXA.getValorMarxa();
                    }
                }
                case 3 -> {
                    if(tipusOperacio=='+'){
                        this.marxaActual = CanviManual.CUARTA_MARXA.getValorMarxa();
                    } else {
                        this.marxaActual = CanviManual.SEGONA_MARXA.getValorMarxa();
                    }
                }
                case 2 -> {
                    if(tipusOperacio=='+'){
                        this.marxaActual = CanviManual.TERCERA_MARXA.getValorMarxa();
                    } else {
                        this.marxaActual = CanviManual.PRIMERA_MARXA.getValorMarxa();
                    }
                }
                case 1 -> {
                    if(tipusOperacio=='+'){
                        this.marxaActual = CanviManual.SEGONA_MARXA.getValorMarxa();
                    } else {
                        this.marxaActual = CanviManual.NEUTRAL.getValorMarxa();
                    }
                }
                case 0 -> {
                    if(tipusOperacio=='+'){
                        this.marxaActual = CanviManual.PRIMERA_MARXA.getValorMarxa();
                    } else {
                        this.marxaActual = CanviManual.MARXA_ENRERA.getValorMarxa();
                    }
                }
                case -1 -> {
                    if(tipusOperacio=='+'){
                        this.marxaActual = CanviManual.NEUTRAL.getValorMarxa();
                    } else {
                        throw new RuntimeException("Minima marxa.");
                    }
                }
                default -> throw new RuntimeException("Error.");
            }
        } else {
            throw new Exception("Aquest cotxo, no es manual.");
        }
    }

    public int getMarxaActual() {
        return this.marxaActual;
    }

}
