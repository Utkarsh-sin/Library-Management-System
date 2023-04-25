package com.example.LibraryManagementSystem.Controller;

import com.example.LibraryManagementSystem.DTO.BookRequestDTO;
import com.example.LibraryManagementSystem.Entity.Book;
import com.example.LibraryManagementSystem.Service.BookService;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookService bookService;
    @PostMapping("/add")
    public String addBook(@RequestBody BookRequestDTO bookRequestDTO){
        try {
            return bookService.addBook(bookRequestDTO);
        }
        catch(Exception e){
            return "Book not added";
        }
    }

}
