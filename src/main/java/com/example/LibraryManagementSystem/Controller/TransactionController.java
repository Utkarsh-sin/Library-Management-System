package com.example.LibraryManagementSystem.Controller;

import com.example.LibraryManagementSystem.DTO.IssueBookResponseDTO;
import com.example.LibraryManagementSystem.DTO.IssueBookRequestDTO;
import com.example.LibraryManagementSystem.Service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @PostMapping("/issueBook")
    public ResponseEntity issueBook(@RequestBody IssueBookRequestDTO issueBookRequestDTO){
        IssueBookResponseDTO issueBookResponseDTO;
        try {
           issueBookResponseDTO =  transactionService.issueBook(issueBookRequestDTO);
        }
        catch(Exception e){
           return new ResponseEntity(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity(issueBookResponseDTO, HttpStatus.ACCEPTED);
    }

}
