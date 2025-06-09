package com.example.library_management.services;


import com.example.library_management.models.Student;
import com.example.library_management.repositaries.StudentRepositary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    StudentRepositary studentRepositary;

    public void create(Student student) {
        studentRepositary.save(student);
    }

    public Student find(int studentId) {
        return studentRepositary.findById(studentId).orElse(null);

    }
}
