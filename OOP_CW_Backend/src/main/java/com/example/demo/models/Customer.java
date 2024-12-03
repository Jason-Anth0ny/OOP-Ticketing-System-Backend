package com.example.demo.models;

import jakarta.persistence.*;
import org.springframework.data.annotation.Persistent;

import java.io.IOException;
import java.net.ServerSocket;

@Entity
@Table(name="customer")
public class Customer implements Runnable{
    @Id
    private String customerId;
    private String customerName;
    private int ticketsBought = 0;

    @Transient
    private boolean active = true;

    public Customer(String customerName, String customerId) throws IOException {
        this.customerName = customerName;
        this.customerId = customerId;
    }

    public Customer() {}

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getTicketsBought() {
        return ticketsBought;
    }

    @Override
    public void run() {
        while (active) {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println(customerName + " was interrupted.");
                break;
            }
        }
    }

}
