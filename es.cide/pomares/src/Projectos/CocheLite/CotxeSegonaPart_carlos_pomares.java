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

    private int marxaActual;
    private boolean capota;
    private boolean aireAcondicionat;
    private boolean cinturo;
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
        this.capota = false;
    }

    // NEEDS
    public void CanviarMarxaAutomatic(char tipusOperacio) throws Exception {
        if(this.comprovaMotor() == EstatsMotorCotxe.EnMarxa && this.tipuscanvi == TipusCanvi.CanviAutomatic && (tipusOperacio=='+' || tipusOperacio=='-')){
            if(tipusOperacio=='+'){
                if(this.marxaActual == 1)
                    throw new RuntimeException("Máxima marxa.");
                this.marxaActual = Marxes.getCanvi(this.marxaActual + 1).getValorMarxa();
            } else {
                if(this.marxaActual == -1)
                    throw new RuntimeException("Mínima marxa.");
                this.marxaActual = Marxes.getCanvi(this.marxaActual - 1).getValorMarxa();
            }
        } else if(this.comprovaMotor() == EstatsMotorCotxe.Aturat){
            throw new Exception("Cotxe no ences.");
        } else {
            throw new Exception("Aquest cotxo no es automatic.");
        }
    }
    public void CanviarMarxaManual(char tipusOperacio) throws Exception {
        if(this.comprovaMotor() == EstatsMotorCotxe.EnMarxa && this.tipuscanvi == TipusCanvi.CanviManual && (tipusOperacio=='+' || tipusOperacio=='-')){
            if(tipusOperacio == '+'){
                if(this.marxaActual == 6)
                    throw new RuntimeException("Máxima marxa.");
                this.marxaActual = Marxes.getCanvi(this.marxaActual + 1).getValorMarxa();
            } else {
                if(this.marxaActual == -1)
                    throw new RuntimeException("Mínima marxa.");
                this.marxaActual = Marxes.getCanvi(this.marxaActual - 1).getValorMarxa();
            }
        } else if(this.comprovaMotor() == EstatsMotorCotxe.Aturat){
            throw new Exception("Cotxe no ences.");
        } else {
            throw new Exception("Aquest cotxo no es manual.");
        }
    }
    public int getValorMarxaActual() {
        return this.marxaActual;
    }
    public String getMarxaActual(){
        if(this.tipuscanvi == TipusCanvi.CanviAutomatic){
            if(this.marxaActual == 1)
                return "Forward";
            return Marxes.getCanvi(this.marxaActual).name();
        } else {
            return Projectos.CocheLite.Marxes.getCanvi(this.marxaActual).name();
        }
    }

    // FEATURES
    public void cambiarEstatCapota(){
        if(this.capota){
            this.capota = false;
        } else {
            this.capota = true;
        }
    }
    public boolean getEstatCapota(){
        return this.capota;
    }

}
