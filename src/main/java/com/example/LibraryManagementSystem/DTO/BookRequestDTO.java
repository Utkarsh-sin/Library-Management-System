package com.example.LibraryManagementSystem.DTO;

import com.example.LibraryManagementSystem.Enum.Genre;
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
public class BookRequestDTO {

    private String title;
    private double price;
    @Enumerated(EnumType.STRING)
    private Genre genre;

    private int authorID;

}
