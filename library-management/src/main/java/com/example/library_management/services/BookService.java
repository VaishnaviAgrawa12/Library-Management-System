package com.example.library_management.services;

import com.example.library_management.models.Author;
import com.example.library_management.models.Book;
import com.example.library_management.models.Genre;
import com.example.library_management.repositaries.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    AuthorService authorService;

    @Autowired
    BookRepository bookRepository;


    public void createOrUpdate(Book book) {

        Author bookAuthor = book.getMyAuthor();
        Author savedAuthor = authorService.getOrCreate(bookAuthor);

        //to map the author with book
        book.setMyAuthor(savedAuthor);
        bookRepository.save(book);
    }

    public List<Book> find(String searchKey, String searchValue) throws Exception {

        switch (searchKey){
            case "id": {
                Optional<Book> book = bookRepository.findById(Integer.parseInt(searchValue));
                if(book.isPresent()){
                    return Arrays.asList(book.get());
                }else{
                    return new ArrayList<>();
                }
            }
            case "genre":
                return bookRepository.findByGenre(Genre.valueOf(searchValue));
            case "author_name":
                return bookRepository.findByMyAuthor_Name(searchValue);
            case "name":
                return bookRepository.findByName(searchValue);
            default:
                throw new Exception("Search key not valid " + searchKey);
        }
    }
}
