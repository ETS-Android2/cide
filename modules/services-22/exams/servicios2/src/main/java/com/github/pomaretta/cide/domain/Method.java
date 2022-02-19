package com.github.pomaretta.cide.domain;

/**
 * Este enum representa las diferentes operaciones que pueden ser enviadas y
 * procesadas
 * por un cliente y servidor.
 * 
 * @author Carlos Pomares <https://www.github.com/pomaretta>
 */
public enum Method {
	GET,
	GET_ALL,
	POST,
	PUT,
	DELETE,
	SHUTDOWN,
}
