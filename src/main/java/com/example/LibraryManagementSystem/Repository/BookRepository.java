package com.example.LibraryManagementSystem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.LibraryManagementSystem.Entity.Book;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {


}
