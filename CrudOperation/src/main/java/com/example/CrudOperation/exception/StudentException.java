package com.example.CrudOperation.exception;

public class StudentException extends RuntimeException{
    String message;
    public StudentException(String message) {
        this.message = message;
    }
}
