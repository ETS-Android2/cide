package com.github.pomaretta.cide.domain;

public class Response {
    
    private int status;
    private String value;

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

    public String encode() {
        return status + "\t" + value;
    }

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
