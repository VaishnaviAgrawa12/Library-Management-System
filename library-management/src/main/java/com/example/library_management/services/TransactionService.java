package com.example.library_management.services;

import com.example.library_management.dtos.InitiateTransactionRequest;
import com.example.library_management.models.*;
import com.example.library_management.repositaries.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    StudentService studentService;

    @Autowired
    AdminService adminService;

    @Autowired
    BookService bookService;

    @Value("${student.allowed.max-books}") //from application properties
    Integer maxBooksAllowed;

    @Value("${student.allowed.duration}")
    Integer duration;




    public String initiateTxn(InitiateTransactionRequest request) throws Exception {

        /**
         * Issuance
         * 1. If the book is available or not and student is valid or not
         * 2. entry in the Txn
         * 3. we need to check if student has reached the maximum limit of issuance
         * 4. book to be assigned to a student ==> update in book table
         *
         */

        /**
         * Return
         * 1. If the book is valid or not and student is valid or not
         * 2. entry in the Txn table
         * 3. due date check and fine calculation
         * 4. if there is no fine, then de-allocate the book from student's name ==> book table
         */
     return request.getTransactionType() == TransactionType.ISSUE ? issuance(request) : returnBook(request);
    }

    private String issuance(InitiateTransactionRequest request) throws Exception {
      Student student= studentService.find(request.getStudentId());
      Admin admin = adminService.find(request.getAdminId());
      List<Book> bookList = bookService.find("id", String.valueOf(request.getBookId()));

      Book book = bookList != null && bookList.size() > 0 ? bookList.get(0) : null;

      if(student == null
              || admin == null
              || book == null
              || book.getStudent() != null || student.getBookList().size() >= maxBooksAllowed){
          throw new Exception("Invalid request");
      }

      Transaction transaction= null;
      try {
           transaction = Transaction.builder()
                  .txnId(UUID.randomUUID().toString())
                  .student(student)
                  .book(book)
                  .admin(admin)
                  .transactionType(request.getTransactionType())
                  .transactionStatus(TransactionStatus.PENDING)
                  .build();

          transactionRepository.save(transaction);

          book.setStudent(student);

          bookService.createOrUpdate(book);

          transaction.setTransactionStatus(TransactionStatus.SUCCESS);
      }catch (Exception e){
          transaction.setTransactionStatus(TransactionStatus.FAILURE);
      }finally {
          transactionRepository.save(transaction);
      }

      return transaction.getTxnId();
    }

    private String returnBook(InitiateTransactionRequest request) throws Exception {

        Student student= studentService.find(request.getStudentId());
        Admin admin = adminService.find(request.getAdminId());
        List<Book> bookList = bookService.find("id", String.valueOf(request.getBookId()));

        Book book = bookList != null && bookList.size() > 0 ? bookList.get(0) : null;

        if(student == null
                || admin == null // admin is null
                || book == null
                || book.getStudent() == null //if the book is assigned to someone or not
                || book.getStudent().getId() != student.getId()){ //if the book is assigned to same person or not who is requesting to return
            throw new Exception("Invalid request");
        }

        //Getting the correspondence issuance txn

        Transaction issuanceTxn = transactionRepository.findTopByStudentAndBookAndTransactionTypeOrderByIdDesc(student.getId(), book.getId(),TransactionType.ISSUE);

        if(issuanceTxn == null){
            throw new Exception("Invalid request");
        }


        Transaction transaction = null;

        try {
            Integer fine =  calculateFine(issuanceTxn.getCreatedOn());

            transaction = Transaction.builder()
                    .txnId(UUID.randomUUID().toString())
                    .student(student)
                    .book(book)
                    .admin(admin)
                    .transactionType(request.getTransactionType())
                    .transactionStatus(TransactionStatus.PENDING)
                    .fine(fine)
                    .build();

            transactionRepository.save(transaction);

            if (fine == 0) {
                book.setStudent(null);
                bookService.createOrUpdate(book);
                transaction.setTransactionStatus(TransactionStatus.SUCCESS);
            }
        }catch (Exception e){
            transaction.setTransactionStatus(TransactionStatus.FAILURE);
        }finally {
            transactionRepository.save(transaction);
        }

        return transaction.getTxnId();
    }

    private Integer calculateFine(Date issuanceTime){

        long issueTimeInMillis = issuanceTime.getTime();
        long currentTime = System.currentTimeMillis();

        long diff = currentTime - issueTimeInMillis;
        long daysPassed = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);

        if(daysPassed > duration){
            return (int)(daysPassed - duration);
        }

        return 0;

    }
}
