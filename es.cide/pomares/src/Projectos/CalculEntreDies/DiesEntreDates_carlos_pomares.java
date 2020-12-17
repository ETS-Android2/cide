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


public class DiesEntreDates_carlos_pomares extends CalcularDiesEntreDates {

    @Override
    protected int diesMes(int mes) {
        int[] meses = new int[]{
                31,28,31,
                30,31,30,
                31,31,30,
                31,30,31
        };
        return meses[mes - 1];
    }

    @Override
    protected int diesMesInicial(DataXS dataXS) {
        int diesTotal = diesMes(dataXS.mes);
        return diesTotal - dataXS.dia;
    }

    @Override
    protected int diesMesDesti(DataXS dataXS) {
        return dataXS.dia;
    }

    @Override
    protected int diesResteAnyInicial(DataXS datainicial) {
        return 0;
    }

    @Override
    protected int diesResteAnyDesti(DataXS datadesti) {
        return 0;
    }

    @Override
    protected int diesNumAnysComplets(DataXS datainicial, DataXS datadesti) {
        return 0;
    }

    @Override
    protected int numDiesPerAnysdeTraspas(DataXS datainicial, DataXS datadesti) {

        int anys = 0;

        try {
            for(int i = datainicial.any; datadesti.any <= i; i++){
                if((new DataXS().calculaAnyDeTraspas(i))) {
                    anys++;
                }
            }
        } catch (Exception e){
            e.getMessage();
        }

        return anys;
    }

    @Override
    protected boolean anyDeTraspas(int any) {
        return false;
    }

}
