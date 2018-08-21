package com.example.demo.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(name = "uk_book_isbn", columnNames = "isbn")})
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String isbn;
    @NotBlank
    private String title;
    private String description;
    @ElementCollection
    @NotEmpty
    private Set<Author> authors;
    @NotBlank
    private String publisher;

    public Book() {
    }

    public Book(@NotBlank String isbn, @NotBlank String title, @NotEmpty Set<Author> authors, @NotBlank String publisher) {
        this.isbn = isbn;
        this.title = title;
        this.authors = authors;
        this.publisher = publisher;
    }

    public Book(@NotBlank String isbn, @NotBlank String title, @NotBlank String publisher) {
        this(isbn, title, new HashSet<>(), publisher);
    }

    public void addAuthor (Author author){
        this.authors.add(author);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("id", id)
                .append("isbn", isbn)
                .append("title", title)
                .append("description", description)
                .append("authors", authors)
                .append("publisher", publisher)
                .toString();
    }
}
