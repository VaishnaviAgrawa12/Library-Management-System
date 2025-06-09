package com.example.library_management.repositaries;

import com.example.library_management.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AuthorRepository extends JpaRepository<Author, Integer> {

    @Query("select a from Author a where a.email = :email")
    Author findByEmail(String email);





}
