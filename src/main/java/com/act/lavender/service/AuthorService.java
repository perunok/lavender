package com.act.lavender.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.act.lavender.model.Author;
import com.act.lavender.repository.AuthorRepository;
import com.act.lavender.repository.BookRepository;

@Service
public class AuthorService {
    // private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public AuthorService(BookRepository bookRepository, AuthorRepository authorRepository) {
        // this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

}
