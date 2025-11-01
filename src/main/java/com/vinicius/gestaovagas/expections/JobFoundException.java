package com.vinicius.gestaovagas.expections;

public class JobFoundException extends RuntimeException {

    public JobFoundException() {
        super("This job Already exists");
    }

}
