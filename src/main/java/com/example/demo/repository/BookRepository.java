package com.example.demo.repository;

import com.example.demo.entity.Book;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface BookRepository extends PagingAndSortingRepository<Book, Long> {
    Optional<Book> findByIsbn(String isbn);
}
