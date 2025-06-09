package com.example.library_management.controllers;

import com.example.library_management.dtos.InitiateTransactionRequest;
import com.example.library_management.services.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public class TransactionController {


    @Autowired
    TransactionService transactionService;



    @PostMapping("/transaction")
    public String initiateTxn(@RequestBody @Valid InitiateTransactionRequest transactionRequest) throws Exception {

        //student id
        //book id
        //admin id
        //txn type
        return transactionService.initiateTxn(transactionRequest);
    }

    @PostMapping("/transaction/payment")
    public void makePayment(@RequestParam("amount") Double amount,
                            @RequestParam("studentId") Integer studentId,
                            @RequestParam("transactionId") String txnId){

    }
}
