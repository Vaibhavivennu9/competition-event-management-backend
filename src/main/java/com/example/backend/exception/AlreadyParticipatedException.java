package com.example.backend.exception;

public class AlreadyParticipatedException extends RuntimeException {

    public AlreadyParticipatedException(String message) {
        super(message);
    }
}
