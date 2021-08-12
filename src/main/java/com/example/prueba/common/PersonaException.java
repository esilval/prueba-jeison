package com.example.prueba.common;

public class PersonaException extends Exception {
    private final int code;

    public PersonaException(int code, String message) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
