package com.example.demo.respository;

import com.example.demo.models.Transaction;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TransactionsRepository extends JpaRepository<Transaction, String> {
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO transactions (id, time, customer_id, vendor_name, transaction_type) VALUES (:txnId, :time, :customerId, :vendorName, :type )", nativeQuery = true)
    public void createTransaction(@Param("txnId") String txnId, @Param("time") String time, @Param("customerId") String customerId, @Param("vendorName") String vendorName, @Param("type") String type);
}
