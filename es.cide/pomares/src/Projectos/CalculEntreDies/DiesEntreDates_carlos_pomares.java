package Projectos.CalculEntreDies;

/*

    Project     Programming21
    Package     Projectos.CalculEntreDies    
    
    Version     1.0      
    Author      Carlos Pomares
    Date        2020-12-15

    DESCRIPTION
    Programa para contar el número de dias entre dos fechas.
    
*/

/**
 * @author Carlos Pomares
 */


public class DiesEntreDates_carlos_pomares extends CalcularDiesEntreDates {

    /**
     *
     * Devuelve el número de días que contiene un mes,
     * especificando el número del mes por parámetros.
     *
     * @param mes el número del mes.
     * @return el número de dias que contiene el mes.
     */
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

    /**
     *
     * Devuelve el número de días restantes entre el número máximo
     * de dias en el mes menos el dia de la fecha introducida.
     *
     * @param dataXS la fecha incial.
     * @return el número de dias del mes inicial.
     */
    @Override
    protected int diesMesInicial(DataXS dataXS) {
        return diesMes(dataXS.mes) - dataXS.dia;
    }

    /**
     *
     * Devuelve el número de días del mes destino.
     *
     * @param dataXS la fecha destino.
     * @return el numero de dias de la fecha destino del mes destino.
     */
    @Override
    protected int diesMesDesti(DataXS dataXS) {
        return dataXS.dia;
    }

    /**
     *
     * Devuelve el número de dias que faltan para terminar el año inicial.
     * Contando hasta noviembre.
     *
     * @param datainicial la fecha inicial.
     * @return el número de dias para terminar el año inicial hasta noviembre.
     */
    @Override
    protected int diesResteAnyInicial(DataXS datainicial) {
        int dies = 0;
        for(int i = datainicial.mes; i < 12 && datainicial.mes != 12; i++){
            dies += diesMes(i);
        }
        return dies;
    }

    /**
     *
     * Devuelve el número de dias que faltan para rellenar el año destino.
     * Contando desde febrero.
     *
     * @param datadesti la fecha destino
     * @return el número de dias entre el inicio de febrero y la fecha destino.
     */
    @Override
    protected int diesResteAnyDesti(DataXS datadesti) {
        int dies = 0;
        for(int i = 1; i < datadesti.mes && datadesti.mes != 1; i++){
            dies += diesMes(i);
        }
        return dies;
    }

    /**
     *
     * Devuelve el número de dias de los años completos entre la fecha inicial
     * y destino.
     *
     * @param datainicial la fecha inicial.
     * @param datadesti la fecha destino.
     * @return el número total de dias entre los años completos.
     */
    @Override
    protected int diesNumAnysComplets(DataXS datainicial, DataXS datadesti) {
        return (((datadesti.any) - (datainicial.any + 1)) * 365);
    }

    /**
     *
     * Devuelve el numero de años que son bisiestos, teniendo en cuenta que
     * el mes inicial sea anterior a febrero y que el mes de destino sea posterior a febrero.
     * En cuyos casos se suma uno.
     *
     * @param datainicial la fecha de inicio.
     * @param datadesti la fecha destino.
     * @return el número de años que son bisiestos y compatibles.
     */
    @Override
    protected int numDiesPerAnysdeTraspas(DataXS datainicial, DataXS datadesti) {
        int dies = (anyDeTraspas(datainicial.any) && datainicial.mes < 2) ? 1 : 0;
        for (int i = datainicial.any + 1; i < datainicial.any + (datadesti.any - datainicial.any); i++){
            if(anyDeTraspas(i))
                dies++;
        }
        return (anyDeTraspas(datadesti.any) && datadesti.mes > 2) ? dies + 1 : dies;
    }

    /**
     *
     * Devuelve si el año que se introduce es bisiesto.
     *
     * @param any el año para comprobar si es bisiesto.
     * @return si el año es bisiesto.
     */
    @Override
    protected boolean anyDeTraspas(int any) {
        return (any % 400 == 0) || ((any % 4 == 0) && !(any % 100 == 0));
    }

}
