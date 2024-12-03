package com.example.demo.service.impl;

import com.example.demo.Request.BuyTicketsRequest;
import com.example.demo.Response.BuyTicketsResponse;
import com.example.demo.controllers.CustomerController;
import com.example.demo.models.Customer;
import com.example.demo.respository.CustomerRepository;
import com.example.demo.service.CustomerService;
import com.example.demo.service.TicketPoolService;
import com.example.demo.service.TransactionService;
import com.example.demo.service.VendorService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.Instant;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

@Service
public class CustomerServiceImpl implements CustomerService {
    String className = this.getClass().getSimpleName();
    private static final Logger logger = Logger.getLogger(CustomerController.class.getName());

    private final VendorService vendorService;
    CustomerRepository customerRepository;
    private final TicketPoolService ticketPoolService;
    private final TransactionService transactionService;

    public CustomerServiceImpl(CustomerRepository customerRepository, VendorService vendorService, TicketPoolService ticketPoolService, TransactionService transactionService) {
        this.customerRepository = customerRepository;
        this.vendorService = vendorService;
        this.ticketPoolService = ticketPoolService;
        this.transactionService = transactionService;

        try {
            FileHandler handler = new FileHandler("/home/jason.anthony/Documents/Ticketing_System_Application/OOP-Ticketing-System-Backend/OOP_CW_Backend/src/main/java/com/example/demo/Logs/ticketingSystemLog.log");
            logger.addHandler(handler);
            SimpleFormatter formatter = new SimpleFormatter();
            handler.setFormatter(formatter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean addCustomer(Customer customer){
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        logger.log(Level.INFO, String.format("Initiated %s %s ", className, methodName));
        customer.setCustomerId("C" +  Instant.EPOCH.toString());
        try {
            customerRepository.save(customer);
            logger.log(Level.INFO, String.format("%s %s succeeded", className, methodName));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            logger.log(Level.SEVERE, String.format("%s %s failed with error: ", className, methodName, e.getMessage()));
            return false;
        }

    }

    // This is mostly a pass-through method.
    // It first verifies to make sure the ticket purchase is possible.
    // Then it simply calls the run() method in the TicketPurchaseImpl class.
    @Override
    public BuyTicketsResponse buyTickets(BuyTicketsRequest buyTicketsRequest) {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        logger.log(Level.INFO, String.format("Initiated %s %s ", className, methodName));
        TicketPurchaseImpl ticketPurchase = new TicketPurchaseImpl(buyTicketsRequest, this.customerRepository, this.vendorService, this.ticketPoolService, this.transactionService);
        Integer ticketsAvailable = vendorService.getTicketsByVendorId(buyTicketsRequest.getVendor_name());
        if (ticketsAvailable >= buyTicketsRequest.getNumber_of_tickets()) {
            try {
                ticketPurchase.run();
                logger.log(Level.INFO, String.format("%s %s succeeded", className, methodName));
                return new BuyTicketsResponse(true, "Tickets purchased successfully!");
            } catch (IllegalThreadStateException e) {
                e.printStackTrace();
                logger.log(Level.SEVERE, String.format("%s %s failed with error: ", className, methodName, e.getMessage()));
                return new BuyTicketsResponse(false, "Error completing transaction!");
            }
        } else {
            logger.log(Level.SEVERE, String.format("%s %s failed: tickets unavailable ", className, methodName));
            return new BuyTicketsResponse(false, "Tickets not available");
        }
    }

    @Override
    public int getNumberOfTickets(String customerId) {
        return 0;
    }

    @Override
    public Customer getCustomer(String customerId) {
        return customerRepository.findByCustomerId(customerId);
    }
}
