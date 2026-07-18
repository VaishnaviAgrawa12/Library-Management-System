package com.example.library_management.controllers;

import com.example.library_management.dtos.CreateBookRequest;
import com.example.library_management.models.Book;
import com.example.library_management.services.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {

    @Autowired

    BookService bookService;

    @PostMapping("/book")
    public void createBook(@RequestBody @Valid CreateBookRequest createBookRequest){

        bookService.createOrUpdate(createBookRequest.to());

    }

    //localhost:9090/book?key=author_name&value=Peter
    //localhost:9090/book?key=genre&value=FICTIONAL
    @GetMapping("/book")
    public List<Book> getBooks(@RequestParam("key") String searchKey,
                               @RequestParam("value") String searchValue) throws Exception {

        return bookService.find(searchKey, searchValue);

    }
}
