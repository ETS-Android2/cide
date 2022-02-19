package com.github.pomaretta.cide.service;

import java.util.HashMap;

/**
 * La cache debe permitir las diferentes operaciones de
 * obtención de datos, modificación de datos y borrado de datos.
 * 
 * @author Carlos Pomares <https://www.github.com/pomaretta>
 */
public interface Cache {
	/**
	 * Permite crear un dato en la cache.
	 * 
	 * @param key   La clave del dato.
	 * @param value El valor del dato.
	 * @throws Exception Si la clave existe en la cache.
	 */
	public void put(String key, String value) throws Exception;

	/**
	 * Permite remplazar el valor de un dato en la cache.
	 * 
	 * @param key   La clave del dato.
	 * @param value El valor del dato.
	 * @throws Exception Si la clave no existe en la cache.
	 */
	public void replace(String key, String value) throws Exception;

	/**
	 * Permite obtener el valor de un dato en la cache.
	 * 
	 * @param key La clave del dato.
	 * @return El valor del dato.
	 * @throws Exception Si la clave no existe en la cache.
	 */
	public String get(String key) throws Exception;

	/**
	 * Permite eliminar un dato de la cache.
	 * 
	 * @param key La clave del dato.
	 * @throws Exception Si la clave no existe en la cache.
	 */
	public void remove(String key) throws Exception;

	/**
	 * Permite obtener todos los valores de la cache.
	 * 
	 * @return Un mapa con todos los valores de la cache.
	 */
	public HashMap<String, String> getAll() throws Exception;
}