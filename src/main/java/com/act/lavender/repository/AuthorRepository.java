package com.act.lavender.repository;

import com.act.lavender.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface AuthorRepository extends JpaRepository<Author,Long> {
    List<Author> findByEmail(String email);
}
