package com.example.library_management.repositories;

import com.example.library_management.models.Book;
import com.example.library_management.models.Student;
import com.example.library_management.models.Transaction;
import com.example.library_management.models.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {


   // @Query("select * from transaction where student_id = ?1 and book_id = ?2 and transactionType = ?3 order by id desc limit 1")
    Transaction findTopByStudentAndBookAndTransactionTypeOrderByIdDesc(Student student, Book book, TransactionType transactionType);

    Transaction findByTxnId(String txnId);



}
