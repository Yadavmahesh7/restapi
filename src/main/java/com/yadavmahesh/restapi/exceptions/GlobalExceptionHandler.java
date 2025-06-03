package com.yadavmahesh.restapi.exceptions;

import com.yadavmahesh.restapi.io.ErrorObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Global exceptional handler for all the exceptions
 * @author Mahesh Yadav
 */

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundExeption.class)
    public ErrorObject handleResourceNotFoundExeption(ResourceNotFoundExeption ex, WebRequest request){
       return ErrorObject .builder()
                .errorCode("Data Not Found")
                .statusCode(HttpStatus.NOT_FOUND.value())
                .message(ex.getMessage())
                .timestamp(new Date())
                .build();
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {
        Map<String,Object> errorResponse = new HashMap<>();
        List<String> errors =ex.getBindingResult().getAllErrors().stream().map(filed ->filed.getDefaultMessage()).collect(Collectors.toList());
        errorResponse.put("StatusCode",HttpStatus.BAD_REQUEST.value());
        errorResponse.put("message",errors);
        errorResponse.put("timestamp",new Date());
        errorResponse.put("errorCode","Validation Failed");
        return new ResponseEntity<Object>(errorResponse,HttpStatus.BAD_REQUEST);
    }
}
