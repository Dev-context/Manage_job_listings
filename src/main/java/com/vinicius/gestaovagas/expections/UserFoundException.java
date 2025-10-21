package com.vinicius.gestaovagas.expections;

public class UserFoundException extends RuntimeException {

    public UserFoundException() {
        super("Usuario Ja Existe");
    }
}
