package com.example.demo.controllers;

import com.example.demo.Request.BuyTicketsRequest;
import com.example.demo.Response.BuyTicketsResponse;
import com.example.demo.Response.CustomerResponse;
import com.example.demo.Request.CustomerRequest;
import com.example.demo.models.Customer;
import com.example.demo.service.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

@RestController
@RequestMapping("/tickets/customer")
public class CustomerController {

    CustomerService customerService;

    private static final Logger logger = Logger.getLogger(CustomerController.class.getName());
    String className = this.getClass().getSimpleName();

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;

        try {
            FileHandler handler = new FileHandler("/home/jason.anthony/Documents/Ticketing_System_Application/OOP-Ticketing-System-Backend/OOP_CW_Backend/src/main/java/com/example/demo/Logs/ticketingSystemLog.log");
            logger.addHandler(handler);
            SimpleFormatter formatter = new SimpleFormatter();
            handler.setFormatter(formatter);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @GetMapping("/{customerId}")
    public CustomerResponse getCustomer(@PathVariable("customerId") String customerId) {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        logger.log(Level.INFO, String.format("Initiated %s %s ", className, methodName));
        try{
            Customer customer = customerService.getCustomer(customerId);
            logger.log(Level.INFO, String.format("%s %s succeeded", className, methodName));
            return new CustomerResponse(customer.getCustomerName(), customer.getTicketsBought());
        } catch (Exception e){
            logger.log(Level.SEVERE, String.format("%s %s failed", className, methodName));
            return null;
        }
    }

    @PostMapping("/new/{customerName}")
    public boolean addCustomer(@PathVariable("customerName") String customerName) {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        logger.log(Level.INFO, String.format("Initiated %s %s ", className, methodName));
        try {
            CustomerRequest customerRequest = new CustomerRequest(customerName);
            Customer newCustomer =  new Customer();
            newCustomer.setCustomerName(customerRequest.getCustomerName());
            customerService.addCustomer(newCustomer);
            logger.log(Level.INFO, String.format("%s %s succeeded", className, methodName));
            return true;
        } catch (Exception e){
            logger.log(Level.SEVERE, String.format("%s %s failed", className, methodName));
            return false;
        }
    }

    @PutMapping("/buy")
    public BuyTicketsResponse buyTicket(@RequestBody BuyTicketsRequest buyTicketsRequest) {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        logger.log(Level.INFO, String.format("Initiated %s %s", className, methodName));
        BuyTicketsResponse buyTicketsResponse = customerService.buyTickets(buyTicketsRequest);
        if (buyTicketsResponse.getSuccess() == true) {
            logger.log(Level.INFO, String.format("%s %s succeeded", className, methodName));
        } else {
            logger.log(Level.INFO, String.format("%s %s failed", className, methodName));
        }
        return buyTicketsResponse;
    }
}
