package com.example.library_management.controllers;


import com.example.library_management.dtos.CreateStudentRequest;
import com.example.library_management.models.Student;
import com.example.library_management.services.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
public class StudentController {

    @Autowired
    StudentService studentService;


    @PostMapping("/student")
    public void createStudent(@RequestBody @Valid CreateStudentRequest studentRequest){
        studentService.create(studentRequest.to());
    }

    @GetMapping("/student")
    public Student findStudent(@RequestParam("id") int studentId){
       return studentService.find(studentId);

    }
}
