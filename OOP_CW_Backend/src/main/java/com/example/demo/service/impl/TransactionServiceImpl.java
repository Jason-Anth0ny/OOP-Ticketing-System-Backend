package com.example.demo.service.impl;

import com.example.demo.respository.TransactionsRepository;
import com.example.demo.service.TransactionService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class TransactionServiceImpl implements TransactionService {

    TransactionsRepository transactionsRepository;
    public TransactionServiceImpl(TransactionsRepository transactionsRepo) {
        this.transactionsRepository = transactionsRepo;
    }

    @Override
    public void createTransaction(String customerId, String vendorId, String type) {
        String currentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String txnId = "TXN" + System.currentTimeMillis();
        try {
            transactionsRepository.createTransaction(txnId, currentTime, customerId, vendorId, type);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
