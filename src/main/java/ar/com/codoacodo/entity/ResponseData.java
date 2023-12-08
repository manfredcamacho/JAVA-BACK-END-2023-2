package ar.com.codoacodo.entity;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ResponseData<T> {
    private String message;
    private int statusCode;
    private T data;

    public ResponseData(String message, int statusCode, T data) {
        this.message = message;
        this.statusCode = statusCode;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
