package com.example.demo.controller;

import com.example.demo.entity.Book;
import com.example.demo.exception.BookIsbnAlreadyExistException;
import com.example.demo.exception.BookNotFoundException;
import com.example.demo.repository.BookRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/books")
public class BookController {
    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createBook(@Valid @RequestBody Book book, UriComponentsBuilder ucBuilder){
        if (bookRepository.findByIsbn(book.getIsbn()).isPresent()){
            throw new BookIsbnAlreadyExistException(book.getIsbn());
        }
        bookRepository.save(book);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/books/{isbn}").buildAndExpand(book.getIsbn()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @GetMapping("/{isbn}")
    public ResponseEntity<Book> getBook(@PathVariable("isbn") String isbn){
        return bookRepository.findByIsbn(isbn)
                .map(book -> new ResponseEntity<>(book, HttpStatus.OK))
                .orElseThrow(() -> new BookNotFoundException(isbn));

    }
}
