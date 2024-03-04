package com.example.CrudOperation.exception;

//@Builder
public class StudentNotFoundException  extends StudentException {
    public StudentNotFoundException(String message) {
        super(message);
    }
}