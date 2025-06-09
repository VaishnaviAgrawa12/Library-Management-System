package com.example.library_management.repositaries;

import com.example.library_management.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepositary extends JpaRepository<Student, Integer> {



}
