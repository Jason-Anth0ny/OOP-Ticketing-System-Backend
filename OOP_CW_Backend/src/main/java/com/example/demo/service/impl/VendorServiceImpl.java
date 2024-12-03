package com.example.demo.service.impl;

import com.example.demo.Response.AddTicketsResponse;
import com.example.demo.controllers.CustomerController;
import com.example.demo.models.Vendor;
import com.example.demo.respository.VendorRepository;
import com.example.demo.service.TicketPoolService;
import com.example.demo.service.VendorService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

@Service
public class VendorServiceImpl implements VendorService {

    String className = this.getClass().getSimpleName();

    VendorRepository vendorRepository;
    private final TicketPoolService ticketPoolService;
    private static final Logger logger = Logger.getLogger(CustomerController.class.getName());

    public VendorServiceImpl(VendorRepository vendorRepository, TicketPoolService ticketPoolService) {
        this.vendorRepository = vendorRepository;
        this.ticketPoolService = ticketPoolService;

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
    public boolean addVendor(Vendor vendor) {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        logger.log(Level.INFO, String.format("Initiated %s %s ", className, methodName));
        vendor.setVendorId("V" + Instant.now().toEpochMilli());
        try {
            vendorRepository.save(vendor);
            logger.log(Level.INFO, String.format("%s %s succeeded", className, methodName));
            return true;
        } catch (Exception e) {
            logger.log(Level.SEVERE, String.format("%s %s failed with error: ", className, methodName, e.getMessage()));
            return false;
        }
    }

    @Override
    public Vendor findVendorById(String vendorId) {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        logger.log(Level.INFO, String.format("Initiated %s %s ", className, methodName));
       try {
           return vendorRepository.findByVendorId(vendorId);
       } catch (Exception e) {
           logger.log(Level.SEVERE, String.format("%s %s failed with error: ", className, methodName, e.getMessage()));
           return null;
       }
    }

    @Override
    public AddTicketsResponse addTickets(int tickets, String vendorId) {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        logger.log(Level.INFO, String.format("Initiated %s %s ", className, methodName));
        Vendor vendor = findVendorById(vendorId);
        try {
            ticketPoolService.addTicketsToPool(tickets);
            vendorRepository.AddTicketsByVendorId(vendorId, tickets);
            logger.log(Level.INFO, String.format("%s %s succeeded", className, methodName));
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            logger.log(Level.SEVERE, String.format("%s %s failed with error: ", className, methodName, e.getMessage()));
            return null;
        }
    }

    @Override
    public Integer getTicketsByVendorId(String vendorName) {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        logger.log(Level.INFO, String.format("Initiated %s %s ", className, methodName));
        try {
            Vendor vendor = vendorRepository.findVendorByVendorName(vendorName);
            logger.log(Level.INFO, String.format("%s %s succeeded", className, methodName));
            return vendorRepository.getTicketsByVendorId(vendor.getVendorId());
        } catch (Exception e) {
            e.printStackTrace();
            logger.log(Level.SEVERE, String.format("%s %s failed with error: ", className, methodName, e.getMessage()));
            return null;
        }
    }

    @Override
    public void sellTickets(int ticketsSold, String vendorId) {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        logger.log(Level.INFO, String.format("Initiated %s %s ", className, methodName));
        try {
            Vendor vendor =  vendorRepository.findVendorByVendorName(vendorId);
            int ticketsRemaining = vendor.getTicketsByVendor() - ticketsSold;
            vendorRepository.sellTickets(ticketsRemaining);
            logger.log(Level.INFO, String.format("%s %s succeeded", className, methodName));
        } catch (Exception e) {
            e.printStackTrace();
            logger.log(Level.SEVERE, String.format("%s %s failed with error: ", className, methodName, e.getMessage()));
        }
    }
}
