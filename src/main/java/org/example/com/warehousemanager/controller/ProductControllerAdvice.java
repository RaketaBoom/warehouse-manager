package org.example.com.warehousemanager.controller;


import org.example.com.warehousemanager.model.dto.ErrorResponse;
import org.example.com.warehousemanager.model.exception.NonUniqueException;
import org.example.com.warehousemanager.model.exception.NotFoundException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ProductControllerAdvice {
    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<Object> handleException(DataAccessException e) {
        return ResponseEntity.internalServerError().body(new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getClass().getSimpleName()));
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleException(NotFoundException e){
        return ResponseEntity.badRequest().body(new ErrorResponse(HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(), e.getClass().getSimpleName()));
    }

    @ExceptionHandler(NonUniqueException.class)
    public ResponseEntity<Object> handleException(NonUniqueException e){
        return ResponseEntity.badRequest().body(new ErrorResponse(HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(), e.getClass().getSimpleName()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public  ResponseEntity<Object> handleException(MethodArgumentNotValidException e){
        return ResponseEntity.badRequest().body(new ErrorResponse(HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(), e.getMessage()));
    }


}
