package com.ensat.controllers;

import com.ensat.entities.ProductError;
import com.ensat.exception.ProductServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(ProductServiceException.class)
    public ResponseEntity<ProductError> mapException(ProductServiceException ex){
        ProductError productError = new ProductError(HttpStatus.INTERNAL_SERVER_ERROR.value(),ex.getMessage());
        return new ResponseEntity<ProductError>(productError,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
