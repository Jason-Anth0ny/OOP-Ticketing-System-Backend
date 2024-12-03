package com.example.demo.service.impl;

import com.example.demo.Request.BuyTicketsRequest;
import com.example.demo.controllers.CustomerController;
import com.example.demo.models.Customer;
import com.example.demo.respository.CustomerRepository;
import com.example.demo.respository.VendorRepository;
import com.example.demo.service.TicketPoolService;
import com.example.demo.service.TransactionService;
import com.example.demo.service.VendorService;

import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class TicketPurchaseImpl implements Runnable{
    private final BuyTicketsRequest buyTicketsRequest;
    private final CustomerRepository customerRepository;
    private final VendorService vendorService;
    private final TicketPoolService ticketPoolService;
    private final TransactionService transactionService;

    private static final Logger logger = Logger.getLogger(CustomerController.class.getName());

    String className = this.getClass().getSimpleName();

    public TicketPurchaseImpl(BuyTicketsRequest buyTicketsRequest, CustomerRepository customerRepository, VendorService vendorService, TicketPoolService ticketPoolService, TransactionService transactionService) {
        this.buyTicketsRequest = buyTicketsRequest;
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

    // This method handles the logic to purchase tickets
    // Only runs if there are tickets available from the chosen vendor
    // Called only by the CustomerService
    @Override
    public void run() {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        logger.log(Level.INFO, String.format("Initiated %s %s ", className, methodName));
        try {
            Customer customer = customerRepository.findByCustomerId(buyTicketsRequest.getCustomer_id());
            synchronized (customer) {
                Thread customerThread = new Thread(customer);
                customerThread.start();
                logger.log(Level.INFO, "Thread created for customer, beginning transaction");
                Integer ticketsBought =  customerRepository.getTicketsBoughtByCustomerId(buyTicketsRequest.getCustomer_id());
                ticketPoolService.removeTicketsFromPool(ticketsBought);
                vendorService.sellTickets(buyTicketsRequest.getNumber_of_tickets(), buyTicketsRequest.getVendor_name());
                ticketsBought += buyTicketsRequest.getNumber_of_tickets();
                customerRepository.setTicketsBoughtByCustomerId(ticketsBought, buyTicketsRequest.getCustomer_id());
                transactionService.createTransaction(buyTicketsRequest.getCustomer_id(), buyTicketsRequest.getVendor_name(), "TICKET_PURCHASE");
                logger.log(Level.INFO, String.format("%s %s succeeded", className, methodName));
                customerThread.interrupt();
            }
        } catch (IllegalThreadStateException e) {
            logger.log(Level.SEVERE, String.format("%s %s failed with error: ", className, methodName, e.getMessage()));
            e.printStackTrace();
        }
    }
}
