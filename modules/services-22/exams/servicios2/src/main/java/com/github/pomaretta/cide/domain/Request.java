package com.github.pomaretta.cide.domain;

public class Request {
    
    private Method method;
    private String key;
    private String value;

    /**
     * Una petición dado un método y valor, utilizada para peticiones
     * sin un dato a enviar.
     * 
     * @param method el método de la petición.
     * @param key la clave a enviar.
     */
    public Request(Method method, String key) {
        this.method = method;
        this.key = key;
    }

    /**
     * Una petición dado un método, una clave y un valor, utilizada para
     * peticiones con un dato a enviar.
     * 
     * @param method el método de la petición.
     * @param key la clave a enviar.
     * @param value el valor a enviar.
     */
    public Request(Method method, String key, String value) {
        this.method = method;
        this.key = key;
        this.value = value;
    }

    public Method getMethod() {
        return method;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    /**
     * El protocolo de comunicación utilizado para las peticiones.
     * Donde se enviaran los datos, separados por tabulaciones.
     * 
     * De está manera a traves de este objeto podemos codificar y descodificar
     * las peticiones.
     * 
     * @return un string con la petición codificada para poder se enviada.
     */
    public String encode() {
        return method.toString() + "\t" + key + "\t" + value;
    }

    /**
     * El protocolo de comunicación utilizado para poder procesar una petición,
     * a partir de un String, utilizado en la parte del servidor para procesar las
     * peticiones enviadas por el cliente.
     * 
     * @param request la petición de un cliente en formato de String.
     * @return un objeto Request con los datos de la petición.
     */
    public static Request decode(String request) {
        String[] requestParts = request.split("\t");
        Method method = Method.valueOf(requestParts[0]);
        String key = requestParts[1];
        String value = null;
        if (!method.equals(Method.GET) && !method.equals(Method.DELETE)) {
            value = requestParts[2];
        }
        return new Request(method, key, value);
    }

    @Override
    public String toString() {
        return "Request{" +
                "method=" + method +
                ", key='" + key + '\'' +
                ", value='" + value + '\'' +
                '}';
    }

}
