package com.act.lavender.controller;

import org.springframework.web.bind.annotation.RestController;

import com.act.lavender.model.Author;
import com.act.lavender.service.AuthorService;

import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/author")
public class AuthorController {
    private AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping()
    public ResponseEntity<List<Author>> getAll() {
        List<Author> authors = authorService.getAllAuthors();
        return ResponseEntity.ok(authors);
    }

}
