package com.example.LibraryManagementSystem.Controller;

import com.example.LibraryManagementSystem.DTO.AuthorRequestDTO;
import com.example.LibraryManagementSystem.DTO.AuthorResponseDTO;
import com.example.LibraryManagementSystem.Entity.Author;
import com.example.LibraryManagementSystem.Entity.Student;
import com.example.LibraryManagementSystem.Service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    AuthorService authorService;

    @PostMapping("/add")
    public String add_author(@RequestBody AuthorRequestDTO authorRequestDTO){
        return authorService.add_author(authorRequestDTO);
    }

    @GetMapping("getAll")
    public List<AuthorResponseDTO> getAllAuthor(){
        return authorService.getAllAuthor();
    }

    @GetMapping("getById")
    public AuthorResponseDTO getAuthorByID(@RequestParam("id") int id){
        return authorService.getAuthorByID(id);
    }
}
