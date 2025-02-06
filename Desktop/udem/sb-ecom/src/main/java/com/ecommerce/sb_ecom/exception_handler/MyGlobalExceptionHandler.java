package com.ecommerce.sb_ecom.exception_handler;

import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice // this is specialized version of controller advice, it will intercept any exception thrown by controller
public class MyGlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)// to handle the exception
    public ResponseEntity<Map<String, String>>myMethodArgumentNotValidException(MethodArgumentNotValidException e){
        Map<String, String> response= new HashMap<>();
        e.getBindingResult().getAllErrors().forEach(err-> {
            String fieldName=((FieldError)err).getField();
            String message= err.getDefaultMessage();
            response.put(fieldName, message);
        });
        return new ResponseEntity<Map<String, String>>(response, HttpStatus.BAD_REQUEST);
    }
    // All the resource not found exception can be handled here using this.
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> myResourceNotFoundException(ResourceNotFoundException e){
          String message= e.getMessage();
          return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(APIException.class)
    public ResponseEntity<String> APIException(APIException e){
        String message= e.getMessage();
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

}
