package com.example.library_management.services;


import com.example.library_management.models.Student;
import com.example.library_management.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    public void create(Student student) {
        studentRepository.save(student);
    }

    public Student find(int studentId) {
        return studentRepository.findById(studentId).orElse(null);

    }
}
