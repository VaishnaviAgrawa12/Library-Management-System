package com.example.library_management.dtos;

import com.example.library_management.models.Author;
import com.example.library_management.models.Book;
import com.example.library_management.models.Genre;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateBookRequest {

    @NotBlank
    private String name;

    @NotNull
    private Genre genre;

    @NotBlank
    private String authorName;

    @NotBlank
    private String authorEmail;

    public Book to(){
       return Book.builder()
                .name(this.name)
                .genre(this.genre)
                .myAuthor(
                        Author.builder()
                                .name(this.authorName)
                                .email(this.authorEmail)
                                .build()
                )
                .build();

       // Author author = Author.builder()
        //                .name(this.authorName)
        //                .email(this.authorEmail)
        //                .build();
        //
        //        return Book.builder()
        //                .name(this.name)
        //                .genre(this.genre)
        //                .my_author(author)
        //                .build();
    }

}
