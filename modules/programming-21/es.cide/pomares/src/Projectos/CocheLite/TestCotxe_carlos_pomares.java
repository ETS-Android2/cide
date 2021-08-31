package Projectos.CocheLite;

/*

    Project     Programming21
    Package     Projectos.CocheLite    
    
    Version     1.0      
    Author      Carlos Pomares
    Date        2020-12-09

    DESCRIPTION
    
*/

/**
 * @author Carlos Pomares
 */


public class TestCotxe_carlos_pomares {
    public static void main(String[] args) {

        // Cotxe_carlos_pomares c1 = new Cotxe_carlos_pomares("Fiat","500S",TipusCanvi.CanviManual);
        CotxeSegonaPart_carlos_pomares c1 = new CotxeSegonaPart_carlos_pomares("Fiat","500S",TipusCanvi.CanviManual);

        try {

            c1.arrancarMotor();
            c1.posarCinturo();
            c1.cambiarEstatCapota();
            c1.posarAireAcondicionat();
            c1.cambiarNivellAireAcondicionat('+');
            c1.CanviarMarxaManual('-');

            System.out.println(c1.getRevolutions());

        } catch (Exception e){
            System.out.println("ERROR: " + e.getMessage());
        }

    }
}
