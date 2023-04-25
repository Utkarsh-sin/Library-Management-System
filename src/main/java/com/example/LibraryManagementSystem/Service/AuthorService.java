package com.example.LibraryManagementSystem.Service;

import com.example.LibraryManagementSystem.DTO.AuthorRequestDTO;
import com.example.LibraryManagementSystem.DTO.AuthorResponseDTO;
import com.example.LibraryManagementSystem.Entity.Author;
import com.example.LibraryManagementSystem.Entity.Student;
import com.example.LibraryManagementSystem.Repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorService {

    @Autowired
    AuthorRepository authorRepository;

    public String add_author(AuthorRequestDTO authorRequestDTO) {
        Author author = new Author();
        author.setName(authorRequestDTO.getName());
        author.setEmail(authorRequestDTO.getEmail());
        author.setAge(authorRequestDTO.getAge());
        authorRepository.save(author);
        return "Author added successfully";
    }

    public List<AuthorResponseDTO> getAllAuthor() {

        List<Author> listOfAuthor =  authorRepository.findAll();
        List<AuthorResponseDTO> listOfauthorResponseDTO = new ArrayList<>();
        for(int i=0;i<listOfAuthor.size();i++){
            AuthorResponseDTO authorResponseDTO = new AuthorResponseDTO();
            authorResponseDTO.setAuthorId(listOfAuthor.get(i).getId());
            authorResponseDTO.setName(listOfAuthor.get(i).getName());
            listOfauthorResponseDTO.add(authorResponseDTO);
        }
        return listOfauthorResponseDTO;
    }

    public AuthorResponseDTO getAuthorByID(int id) {
        AuthorResponseDTO authorResponseDTO = new AuthorResponseDTO();
        Author author = authorRepository.findById(id).get();
        authorResponseDTO.setName(author.getName());
        authorResponseDTO.setAuthorId(id);
        return authorResponseDTO;
    }


}
