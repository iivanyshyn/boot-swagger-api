package com.example.demo.exception;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(String isbn){
        super("could not find book with ISBN: '" + isbn + "'");
    }
}
