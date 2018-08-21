package com.example.demo.exception;

public class BookIsbnAlreadyExistException extends RuntimeException {
    public BookIsbnAlreadyExistException(String isbn){
        super("book already exists for ISBN: '" + isbn + "'");
    }
}
