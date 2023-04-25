package com.example.LibraryManagementSystem.DTO;

import com.example.LibraryManagementSystem.Enum.TransactionStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IssueBookResponseDTO {

    private String transactionNum;

    private String bookName;

    @Enumerated(EnumType.STRING)
    private TransactionStatus transaction_status;
}
