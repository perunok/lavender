package com.act.lavender.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.act.lavender.model.Book;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByTitle(String title);
}
