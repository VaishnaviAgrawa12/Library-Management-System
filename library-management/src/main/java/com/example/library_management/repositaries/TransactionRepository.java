package com.example.library_management.repositaries;

import com.example.library_management.models.Book;
import com.example.library_management.models.Student;
import com.example.library_management.models.Transaction;
import com.example.library_management.models.TransactionType;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {


   // @Query("select * from transaction where student_id = ?1 and book_id = ?2 and transactionType = ?3 order by id desc limit 1")
    Transaction findTopByStudentAndBookAndTransactionTypeOrderByIdDesc(Integer student, Integer book, TransactionType transactionType);

    Transaction findByTxnId(String txnId);



}
