package com.example.LibraryManagementSystem.Service;

import com.example.LibraryManagementSystem.DTO.IssueBookResponseDTO;
import com.example.LibraryManagementSystem.DTO.IssueBookRequestDTO;
import com.example.LibraryManagementSystem.Entity.LibraryCard;
import com.example.LibraryManagementSystem.Entity.Book;
import com.example.LibraryManagementSystem.Entity.Transaction;
import com.example.LibraryManagementSystem.Enum.Status;
import com.example.LibraryManagementSystem.Enum.TransactionStatus;
import com.example.LibraryManagementSystem.Repository.BookRepository;
import com.example.LibraryManagementSystem.Repository.LibraryCardRepository;
import com.example.LibraryManagementSystem.Repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TransactionService {

    @Autowired
    LibraryCardRepository libraryCardRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    private JavaMailSender emailSender;

    public IssueBookResponseDTO issueBook(IssueBookRequestDTO issueBookRequestDTO) throws Exception {
        Transaction transaction = new Transaction();
        transaction.setTransactionNum(String.valueOf(UUID.randomUUID()));
        transaction.setIssueOperation(true);

        LibraryCard card;
        try{
            card = libraryCardRepository.findById(issueBookRequestDTO.getCardid()).get();
        }
        catch(Exception e){
            transaction.setTransaction_status(TransactionStatus.FAILED);
            transaction.setMessage("Invalid Card ID");
            transactionRepository.save(transaction);
            throw new Exception("Invalid Card ID");
        }

        Book book;
        try{
            book = bookRepository.findById(issueBookRequestDTO.getBookid()).get();
        }
        catch(Exception e){
            transaction.setTransaction_status(TransactionStatus.FAILED);
            transaction.setMessage("Invalid Book ID");
            transactionRepository.save(transaction);
            throw new Exception("Invalid Book ID");
        }

        transaction.setBook(book);
        transaction.setCard(card);

        if(card.getStatus() != Status.ACTIVATED){
            transaction.setTransaction_status(TransactionStatus.FAILED);
            transaction.setMessage("Card Not Activated");
            transactionRepository.save(transaction);
            throw new Exception("Your card is not activated!");
        }
        if(book.isIssued()==true){
            transaction.setTransaction_status(TransactionStatus.FAILED);
            transaction.setMessage("Book already issued");
            transactionRepository.save(transaction);
            throw new Exception("Sorry! Book is already issued");
        }

        transaction.setTransaction_status(TransactionStatus.SUCCESS);
        transaction.setMessage("Book issued successfully");
        book.setIssued(true);
        book.setCard(card);
        book.getTransactions().add(transaction);
        card.getTransactions().add(transaction);
        card.getBooksIssued().add(book);

        libraryCardRepository.save(card);

        IssueBookResponseDTO issueBookResponseDTO = new IssueBookResponseDTO();
        issueBookResponseDTO.setBookName(book.getTitle());
        issueBookResponseDTO.setTransactionNum(transaction.getTransactionNum());
        issueBookResponseDTO.setTransaction_status(transaction.getTransaction_status());

        String text = "Congrats! "+ card.getStudent().getName()+" You have issued book"+ book.getTitle();

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("sinhautkarsh4527@gmail.com");
        message.setTo(card.getStudent().getEmail());
        message.setSubject("Issue Book notification");
        message.setText(text);
        emailSender.send(message);

        return issueBookResponseDTO;


    }
}
