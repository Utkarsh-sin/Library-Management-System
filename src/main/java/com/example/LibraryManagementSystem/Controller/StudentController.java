package com.example.LibraryManagementSystem.Controller;

import com.example.LibraryManagementSystem.DTO.StudentRequestDTO;
import com.example.LibraryManagementSystem.DTO.StudentResponseDTO;
import com.example.LibraryManagementSystem.Entity.Student;
import com.example.LibraryManagementSystem.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @PostMapping("/add")
    public String add_student(@RequestBody StudentRequestDTO studentRequestDTO){
        return studentService.add_student(studentRequestDTO);
    }

    @GetMapping("/getAll")
    public List<StudentResponseDTO> getAllStudent(){
        return studentService.getAllStudent();
    }

    @GetMapping("/getById")
    public Student getStudentByID(@RequestParam("id") int id){
        return studentService.getStudentByID(id);
    }

    @GetMapping("/getByEmail")
    public Student getStudentByEmail(@RequestParam("email") String email){
        return studentService.getStudentByEmail(email);
    }

    @GetMapping("/getByAge")
    public List<Student> getStudentByAge(@RequestParam("age") int age){
        return studentService.getStudentByAge(age);
    }

}
