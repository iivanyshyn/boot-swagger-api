package com.example.demo.controller;

import com.example.demo.exception.BookIsbnAlreadyExistException;
import com.example.demo.exception.BookNotFoundException;
import org.springframework.hateoas.VndErrors;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@ControllerAdvice
@RequestMapping(produces = "application/vnd.error")
public class BookControllerAdvice {

    @ResponseBody
    @ExceptionHandler(BookIsbnAlreadyExistException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    VndErrors bookIsbnAlreadyExistsExceptionHandler(BookIsbnAlreadyExistException e){
        return new VndErrors("error", e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(BookNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    VndErrors bookNotFoundExceptionHandler(BookNotFoundException e){
        return new VndErrors("error", e.getMessage());
    }

}
