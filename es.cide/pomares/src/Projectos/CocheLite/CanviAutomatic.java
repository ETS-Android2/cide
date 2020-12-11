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


public enum CanviAutomatic {
    FORWARD(1),
    NEUTRAL(0),
    REVERSE(-1);

    private final int marxaAutomatica;

    private CanviAutomatic(int canvi){
        this.marxaAutomatica = canvi;
    }

    public int getValorMarxa(){
        return this.marxaAutomatica;
    }

    public static CanviAutomatic getCanvi(int marxa){
        CanviAutomatic canviActual = null;
        for(CanviAutomatic canvi : CanviAutomatic.values()) {
            if(canvi.getValorMarxa() == marxa){
                canviActual = canvi;
            }
        }
        return canviActual;
    }

    public static String getCanviString(int marxa){
        String canviActual = null;
        for(CanviAutomatic canvi : CanviAutomatic.values()) {
            if(canvi.getValorMarxa() == marxa){
                canviActual = canvi.name();
            }
        }
        return canviActual;
    }

}
