package com.act.lavender.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.act.lavender.model.Book;
import com.act.lavender.request.BookRequest;
import com.act.lavender.request.ReplaceBookRequest;
import com.act.lavender.service.BookService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @SuppressWarnings("null")
    @GetMapping()
    public ResponseEntity<List<Book>> getAllBookList() {

        if (bookService.allBooks().isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(bookService.allBooks());
    }

    @GetMapping("/{title}")
    public ResponseEntity<Book> getBook(@PathVariable("title") String title) {
        var books = bookService.findBookByTitle(title);
        if (!books.isEmpty()) {
            return ResponseEntity.ok(books.get());
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @SuppressWarnings("null")
    @PostMapping()
    public ResponseEntity<Book> addBook(@RequestBody BookRequest bookRequest) {
        Book _book = bookService.createBook(bookRequest);
        if (_book == null) {
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }
        return ResponseEntity.ok(_book);
    }

    @SuppressWarnings("null")
    @PatchMapping()
    public ResponseEntity<Book> updateBook(@RequestBody BookRequest bookRequest) {
        Book _book = bookService.updateBook(bookRequest);
        if (_book == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_MODIFIED);
        }
        return ResponseEntity.ok(_book);
    }

    @SuppressWarnings("null")
    @PutMapping()
    public ResponseEntity<Book> replaceBook(@RequestBody ReplaceBookRequest replaceBookRequest) {
        Book _book = bookService.replaceBook(replaceBookRequest);
        if (_book == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_MODIFIED);
        }
        return ResponseEntity.ok(_book);
    }

    @SuppressWarnings("null")
    @DeleteMapping()
    public ResponseEntity<String> deleteBook(@RequestParam String title) {
        if (bookService.deleteBook(title)) {
            return ResponseEntity.ok("");
        }
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

}
