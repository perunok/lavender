package com.act.lavender.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.act.lavender.model.Author;
import com.act.lavender.model.Book;
import com.act.lavender.repository.AuthorRepository;
import com.act.lavender.repository.BookRepository;
import com.act.lavender.request.BookRequest;
import com.act.lavender.request.ReplaceBookRequest;

@Service
public class BookService implements Serializable {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookService(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    public List<Book> allBooks() {
        List<Book> allBooksList = new ArrayList<Book>();
        bookRepository.findAll().forEach(allBooksList::add);
        return allBooksList;
    }

    public Optional<Book> findBookByTitle(String title) {
        try {
            Optional<Book> optional = Optional.of(bookRepository.findByTitle(title).getFirst());
            return optional;
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public Book createBook(BookRequest request) {
        try {
            // handle if the book already exists
            boolean bookExists = !bookRepository.findByTitle(request.getTitle()).isEmpty();
            if (bookExists) {
                return null;
            }
            Book book = new Book();
            Author author = new Author();
            // handle if the author already exists
            List<Author> authors = authorRepository.findByEmail(request.getEmail());
            if (!authors.isEmpty()) {
                book.setAuthor(authors.getFirst());
            } else {
                author.setName(request.getName());
                author.setEmail(request.getEmail());
                authorRepository.save(author);
                book.setAuthor(author);
            }
            book.setTitle(request.getTitle());
            book.setDescription(request.getDescription());
            bookRepository.save(book);
            return book;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean deleteBook(String title) {
        try {
            bookRepository.delete(bookRepository.findByTitle(title).getFirst());
            return true;
        } catch (Exception e) {
            // e.printStackTrace();
            return false;
        }
    }

    public Book updateBook(BookRequest request) {
        try {
            List<Book> books = bookRepository.findByTitle(request.getTitle());
            // handle if the book does not exists
            if (books.isEmpty()) {
                return null;
            }
            Book book = books.getFirst();
            Author author;
            // handle if the author is new
            List<Author> authors = authorRepository.findByEmail(request.getEmail());
            if (authors.isEmpty()) {
                author = new Author();
                author.setName(request.getName());
                author.setEmail(request.getEmail());
                authorRepository.save(author);
                book.setAuthor(author);
            }
            book.setTitle(request.getTitle());
            book.setDescription(request.getDescription());
            bookRepository.save(book);
            return book;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Book replaceBook(ReplaceBookRequest request) {
        try {
            List<Book> books = bookRepository.findByTitle(request.getOldTitle());
            // handle if the book does not exists
            if (books.isEmpty()) {
                return null;
            }
            Book book = books.getFirst();
            Author author;
            // handle if the author is new
            List<Author> authors = authorRepository.findByEmail(request.getEmail());
            if (authors.isEmpty()) {
                author = new Author();
                author.setName(request.getName());
                author.setEmail(request.getEmail());
                authorRepository.save(author);
                book.setAuthor(author);
            }
            book.setTitle(request.getTitle());
            book.setDescription(request.getDescription());
            bookRepository.save(book);
            return book;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
