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


public enum CanviManual {
    MARXA_ENRERA(-1),
    NEUTRAL(0),
    PRIMERA_MARXA(1),
    SEGONA_MARXA(2),
    TERCERA_MARXA(3),
    CUARTA_MARXA(4),
    QUINTA_MARXA(5),
    SEXTA_MARXA(6);

    private final int valorMarxa;

    CanviManual(int marxa){
        this.valorMarxa = marxa;
    }

    public int getValorMarxa(){
        return this.valorMarxa;
    }

    public static String getCanviString(int marxa){
        String canviActual = null;
        for(CanviManual canvi : CanviManual.values()) {
            if(canvi.getValorMarxa() == marxa){
                canviActual = canvi.name();
            }
        }
        return canviActual;
    }

}
