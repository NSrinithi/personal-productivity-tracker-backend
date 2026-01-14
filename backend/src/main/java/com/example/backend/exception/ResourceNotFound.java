package com.example.backend.exception;

public class ResourceNotFound extends RuntimeException{
    private String message;

    public ResourceNotFound(String message) {
        super(message);
    }

}
