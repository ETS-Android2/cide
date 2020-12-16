package Projectos.CalculEntreDies;

/*

    Project     Programming21
    Package     Projectos.CalculEntreDies    
    
    Version     1.0      
    Author      Carlos Pomares
    Date        2020-12-15

    DESCRIPTION
    
*/

/**
 * @author Carlos Pomares
 */


public class TestDiesEntreDates {

    private static DiesEntreDates_carlos_pomares calcul;
    private static DataXS inici, desti;

    public static void main(String[] args) {
        calcul = new DiesEntreDates_carlos_pomares();
        try {
            inici = new DataXS("12/12/2017");
            desti = new DataXS("1/1/2018");
            System.out.println(calcul.nombreDiesTotals(inici,desti));
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

}
