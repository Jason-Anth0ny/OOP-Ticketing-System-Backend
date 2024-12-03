package com.example.demo.respository;

import com.example.demo.models.Customer;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CustomerRepository extends JpaRepository<Customer, String>{

    Customer findByCustomerId(String customerId);

    @Query(value = "SELECT * FROM customer WHERE customer_name= :customerName;", nativeQuery = true)
    Customer findByCustomerName(@Param("customerName") String customerName);

    @Query(value = "SELECT tickets_bought FROM customer WHERE customer_id = :customerId", nativeQuery = true)
    Integer getTicketsBoughtByCustomerId(@Param("customerId") String customerId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE customer SET tickets_bought = :ticketsBought WHERE customer_id= :customerId", nativeQuery = true)
    void setTicketsBoughtByCustomerId(@Param("ticketsBought") Integer ticketsBought, @Param("customerId") String customerId);
}
