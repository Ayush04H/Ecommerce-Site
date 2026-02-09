package com.ecom.Project.exceptions;


import com.ecom.Project.payload.APIResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class myGlobalExceptionHandler {
    //For mapping Exception
    @ExceptionHandler(MethodArgumentNotValidException.class)
    //Declaring Methods
    public ResponseEntity<Map<String , String>> myMethodArgumentNotValidException(MethodArgumentNotValidException e){
        Map<String,String> response = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach(err ->{
            String fieldname = ((FieldError)err).getField();
            String message = err.getDefaultMessage();
            response.put(fieldname,message);
        });
        return new ResponseEntity<Map<String , String>>(response, HttpStatus.BAD_REQUEST);

    }

    //Custom Class Exception Handler
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<APIResponse> myResoucenotFoundException(ResourceNotFoundException e){
        String message = e.getMessage();
        APIResponse apiResponse = new APIResponse(message , false);
        return new ResponseEntity<>(apiResponse,HttpStatus.NOT_FOUND);
    }

    //Custom Class Exception Handler
    @ExceptionHandler(APIException.class)
    public ResponseEntity<APIResponse> myAPIException(APIException e){
        String message = e.getMessage();
        APIResponse apiResponse = new APIResponse(message , false);
        return new ResponseEntity<>(apiResponse,HttpStatus.BAD_REQUEST);
    }

    //Custom Class Exception Handler
    @ExceptionHandler(NoCatgoryException.class)
    public ResponseEntity<String> myNoCatgoryException(NoCatgoryException e){
        String message = e.getMessage();
        return new ResponseEntity<>(message,HttpStatus.BAD_REQUEST);
    }







}
