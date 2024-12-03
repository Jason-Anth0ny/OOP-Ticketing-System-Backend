package com.example.demo.service;

import com.example.demo.Request.BuyTicketsRequest;
import com.example.demo.Response.BuyTicketsResponse;
import com.example.demo.models.Customer;

public interface CustomerService {
    public boolean addCustomer(Customer customer);
    public BuyTicketsResponse buyTickets(BuyTicketsRequest buyTicketsRequest);
    public int getNumberOfTickets(String customerId);
    public Customer getCustomer(String customerId);
}
