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
    private static DataXS inici, desti,test1,test2;

    public static void main(String[] args) {
        calcul = new DiesEntreDates_carlos_pomares();
        try {
            test1 = new DataXS("01/01/2000");
            test2 = new DataXS("28/02/2016");
            inici = new DataXS("12/12/2017");
            desti = new DataXS("01/01/2018");
             System.out.println(calcul.nombreDiesTotals(inici,desti));
            //System.out.println(calcul.numDiesPerAnysdeTraspas(inici,desti));
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

}
