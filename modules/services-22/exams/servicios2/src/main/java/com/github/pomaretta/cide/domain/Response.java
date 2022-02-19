package com.github.pomaretta.cide.domain;

public class Response {

	private int status;
	private String value;

	/**
	 * Una respuesta es el resultado de haber procesado una petición por parte
	 * del servidor.
	 * 
	 * @param status el estado de la respuesta
	 * @param value  el valor de la respuesta
	 */
	public Response(int status, String value) {
		this.status = status;
		this.value = value;
	}

	public int getStatus() {
		return status;
	}

	public String getValue() {
		return value;
	}

	/**
	 * Codifica la respuesta para poder ser enviada de una manera que el cliente
	 * entienda.
	 * 
	 * @return un string con la respuesta codificada para poder se enviada.
	 */
	public String encode() {
		return status + "\t" + value;
	}

	/**
	 * Descodifica una respuesta recibida por parte del servidor, donde a partir de
	 * un String se obtiene un objecto Response con los datos encapsulados de la
	 * respuesta.
	 * 
	 * @param response el string con la respuesta recibida.
	 * @return un objeto Response con los datos de la respuesta.
	 */
	public static Response decode(String response) {
		String[] responseParts = response.split("\t");
		int status = Integer.parseInt(responseParts[0]);
		String value = null;
		if (responseParts.length > 1) {
			value = responseParts[1];
		}
		return new Response(status, value);
	}

	@Override
	public String toString() {
		return "Response{" +
				"status=" + status +
				", value='" + value + '\'' +
				'}';
	}

}
