package com.github.pomaretta.cide.service;

import java.io.IOException;

import com.github.pomaretta.cide.domain.Request;
import com.github.pomaretta.cide.domain.Response;

/**
 * El protocolo de comunicación entre el cliente y el servidor.
 * La clase que implemente este protocolo debe ser capaz de
 * poder enviar un objeto Request y devolver un objeto Response.
 * Añadiendo una capa de abstracción hacia el desarrollador.
 * 
 * @author Carlos Pomares <https://www.github.com/pomaretta>
 */
public interface Protocol {
    /**
     * Permite enviar una petición al servidor utilizando el esquema de
     * Request, una vez lo comunica con el servidor, este devuelve unos datos
     * en el esquema de Response. Devolviendo la respuesta al cliente.
     * 
     * @param request La petición que se va a enviar al servidor.
     * @return La respuesta del servidor.
     * @throws IOException Si hay algún problema con la comunicación.
     */
    Response send(Request request) throws IOException;
}
