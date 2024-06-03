package com.message.publisher.model;

import org.springframework.stereotype.Component;

@Component
public class ErrorResponse<T> {
    private String code;
    private String message;
    private T errors;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getErrors() {
        return errors;
    }

    public void setErrors(T errors) {
        this.errors = errors;
    }
}
