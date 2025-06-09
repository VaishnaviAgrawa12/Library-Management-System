package com.example.library_management.repositaries;

import com.example.library_management.models.Book;
import com.example.library_management.models.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {

    List<Book> findByGenre(Genre genre);

    List<Book> findByMyAuthor_Name(String authorname);

    List<Book> findByName(String bookName);
}
