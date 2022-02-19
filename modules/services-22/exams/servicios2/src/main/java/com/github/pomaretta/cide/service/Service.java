package com.github.pomaretta.cide.service;

import com.github.pomaretta.cide.domain.Request;

/**
 * La clase que implemente esta interfaz debe ser capaz de procesar una petición
 * y realizar las diferentes operaciones que pueden ser enviadas en una petición (Request)
 * en la cache distribuida.
 */
public interface Service {
    /**
     * Dada una petición, se obtienen sus atributos y se realiza la operación en la cache
     * distribuida. Devolviendo un valor en consecuencia a la operación realizada.
     * 
     * @param request La petición que se va a procesar.
     * @return El valor que se obtiene en la operación realizada.
     * @throws Exception Si hay algún problema con el procesamiento de la operación.
     */
    Object operate(Request request) throws Exception;
}
