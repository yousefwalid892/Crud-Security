package com.example.CrudOperation.handler;

import com.example.CrudOperation.exception.StudentAlreadyExistsException;
import com.example.CrudOperation.exception.StudentNotFoundException;
import lombok.Builder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Builder
@ControllerAdvice
public class GlobalExceptionHandler{
       @ExceptionHandler(value = StudentNotFoundException.class)
       public ResponseEntity<ErrorResponse> handleStudentNotFound(StudentNotFoundException ex){
              ErrorResponse errorResponse = ErrorResponse.builder
                      (ex,HttpStatus.NOT_FOUND,ex.getMessage())
                      .build();
              return new ResponseEntity<>(errorResponse.getStatusCode());
       }
      @ExceptionHandler(value = StudentAlreadyExistsException.class)
      public ResponseEntity<ErrorResponse> handleStudentAlreadyExist(StudentAlreadyExistsException ex){
             ErrorResponse errorResponse = ErrorResponse.builder
                     (ex,HttpStatus.FOUND,ex.getMessage())
                      .build();
             return new ResponseEntity<>(errorResponse.getStatusCode());
    }
}

