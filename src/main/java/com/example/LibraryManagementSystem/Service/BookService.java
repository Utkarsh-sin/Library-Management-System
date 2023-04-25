package com.example.LibraryManagementSystem.Service;

import com.example.LibraryManagementSystem.DTO.BookRequestDTO;
import com.example.LibraryManagementSystem.Entity.Book;
import com.example.LibraryManagementSystem.Entity.Author;
import com.example.LibraryManagementSystem.Repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BookService {

    @Autowired
    AuthorRepository authorRepository;
    public String addBook(BookRequestDTO bookRequestDTO) {

        Book book =new Book();
        book.setTitle(bookRequestDTO.getTitle());
        book.setPrice(bookRequestDTO.getPrice());
        book.setGenre(bookRequestDTO.getGenre());
        book.setIssued(false);

        Author author;
        try{
            author = authorRepository.findById(bookRequestDTO.getAuthorID()).get();
        }
        catch (Exception e){
            return  e.getMessage();
        }
        book.setAuthor(author);
        List<Book> listofBooks= author.getListOfBook();
        listofBooks.add(book);

        authorRepository.save(author);
        return "Book added successfully";
    }
}
