package com.example.LibraryManagementSystem.DTO;

import com.example.LibraryManagementSystem.Enum.Department;
import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentRequestDTO {

    private String name;

    @Enumerated(EnumType.STRING)
    private Department department;

    private int age;

    private String email;
}
