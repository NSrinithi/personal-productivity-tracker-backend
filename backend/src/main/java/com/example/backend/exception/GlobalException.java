package com.example.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.backend.dto.ErrorDto;


@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDto> handleGenric(Exception ex){
        ErrorDto err=new ErrorDto(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(err,HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<ErrorDto> handleResource(Exception ex){
        ErrorDto err=new ErrorDto(ex.getMessage(), HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(err,HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDto> handleValid(MethodArgumentNotValidException ex){
        ErrorDto err=new ErrorDto(ex.getBindingResult().getAllErrors().get(0).getDefaultMessage(), HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
    }

}
