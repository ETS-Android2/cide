package com.github.pomaretta.cide.domain;

public class Request {
    
    private Method method;
    private String key;
    private String value;

    public Request(Method method, String key) {
        this.method = method;
        this.key = key;
    }

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

    public String toString() {
        return method.toString() + "\t" + key + "\t" + value;
    }

    public static Request parseRequest(String request) {
        String[] requestParts = request.split("\t");
        Method method = Method.valueOf(requestParts[0]);
        String key = requestParts[1];
        String value = null;
        if (!method.equals(Method.GET) && !method.equals(Method.DELETE)) {
            value = requestParts[2];
        }
        return new Request(method, key, value);
    }

}
