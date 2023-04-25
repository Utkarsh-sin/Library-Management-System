package com.example.LibraryManagementSystem.Service;

import com.example.LibraryManagementSystem.DTO.StudentRequestDTO;
import com.example.LibraryManagementSystem.DTO.StudentResponseDTO;
import com.example.LibraryManagementSystem.Entity.LibraryCard;
import com.example.LibraryManagementSystem.Entity.Student;
import com.example.LibraryManagementSystem.Enum.Status;
import com.example.LibraryManagementSystem.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;
    public String add_student(StudentRequestDTO studentRequestDTO) {
        Student student = new Student();
        student.setAge(studentRequestDTO.getAge());
        student.setName(studentRequestDTO.getName());
        student.setEmail(studentRequestDTO.getEmail());
        student.setDepartment(studentRequestDTO.getDepartment());

        LibraryCard card = new LibraryCard();
        card.setValidTill("24/03/27");
        card.setStatus(Status.ACTIVATED);
        card.setStudent(student);

        student.setLibraryCard(card);

        studentRepository.save(student);
        return "Student added successfully";
    }

    public List<StudentResponseDTO> getAllStudent() {
        List<StudentResponseDTO> listOfStudentDTO=  new ArrayList<>();

        List<Student> listOfStudent = studentRepository.findAll();
        for(int i=0; i<listOfStudent.size();i++){
            StudentResponseDTO studentResponseDTO = new StudentResponseDTO();
            studentResponseDTO.setId(listOfStudent.get(i).getId());
            studentResponseDTO.setName(listOfStudent.get(i).getName());
            studentResponseDTO.setAge(listOfStudent.get(i).getAge());

            listOfStudentDTO.add(studentResponseDTO);
        }

        return listOfStudentDTO;
    }

    public Student getStudentByID(int id) {
        return studentRepository.findById(id).get();
    }

    public Student getStudentByEmail(String email) {
        return studentRepository.findByEmail(email);
    }

    public List<Student> getStudentByAge(int age) {
        return studentRepository.findByAge(age);
    }
}
