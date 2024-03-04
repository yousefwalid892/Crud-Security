package com.example.CrudOperation.exception;

//@Builder
public class StudentAlreadyExistsException extends StudentException {
    public StudentAlreadyExistsException(String message) {
        super(message);
    }
}
