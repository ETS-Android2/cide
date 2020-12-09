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

        Cotxe_carlos_pomares c1 = new Cotxe_carlos_pomares("Fiat","500S",TipusCanvi.CanviManual);

        try {

            c1.arrancarMotor();
            System.out.println(c1.comprovaMotor() + " REV: " + c1.getRevolutions());

            c1.aturarMotor();
            System.out.println(c1.comprovaMotor() + " REV: " + c1.getRevolutions());

        } catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
}
