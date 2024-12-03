package com.example.demo.controllers;

import com.example.demo.Request.AddTicketsRequest;
import com.example.demo.Request.VendorRequest;
import com.example.demo.Response.AddTicketsResponse;
import com.example.demo.Response.VendorResponse;
import com.example.demo.models.Vendor;
import com.example.demo.service.VendorService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalTime;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

@RestController
@RequestMapping("/tickets/vendor")
public class VendorController {
    String className = this.getClass().getSimpleName();

    private final VendorService vendorService;

    private static final Logger logger = Logger.getLogger(CustomerController.class.getName());

    public VendorController(VendorService vendorService) {
        try {
            FileHandler handler = new FileHandler("/home/jason.anthony/Documents/Ticketing_System_Application/OOP-Ticketing-System-Backend/OOP_CW_Backend/src/main/java/com/example/demo/Logs/ticketingSystemLog.log");
            logger.addHandler(handler);
            SimpleFormatter formatter = new SimpleFormatter();
            handler.setFormatter(formatter);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.vendorService = vendorService;
    }

    @PostMapping("/new")
    public boolean createVendor(@RequestBody VendorRequest vendor) {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        logger.log(Level.INFO, String.format("Initiated %s %s", className, methodName));
        try {
            Vendor newVendor = new Vendor(vendor.getVendorName(), vendor.getNumberOfTickets());
            vendorService.addVendor(newVendor);
            logger.log(Level.INFO, String.format("%s %s succeeded", className, methodName));
            return true;
        } catch (Exception e) {
            logger.log(Level.SEVERE, String.format("%s %s failed with error: %s", className, methodName, e.getMessage()));
            return false;
        }
    }

    @GetMapping(value = "/{vendorId}", produces = "application/json")
    public VendorResponse getVendorById(@PathVariable String vendorId){
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        logger.log(Level.INFO, String.format("Initiated %s %s", className, methodName));
        try {
            Vendor vendor = vendorService.findVendorById(vendorId);
            logger.log(Level.INFO, String.format("%s %s succeeded", className, methodName));
            return new VendorResponse(vendor.getVendorName(), vendor.getVendorId(), vendor.getTicketsByVendor(), true);
        } catch (Exception e) {
            VendorResponse vendorResponse = new VendorResponse();
            vendorResponse.setSuccess(false);
            logger.log(Level.INFO, String.format("%s %s succeeded with error: %s", className, methodName, e.getMessage()));
            return vendorResponse;
        }
    }

    @PutMapping(value="/add", produces = "application/json")
    public AddTicketsResponse addTicketsByVendorId(@RequestBody AddTicketsRequest addTicketsRequest) {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        logger.log(Level.INFO, String.format("Initiated %s %s", className, methodName));
        try {
            vendorService.addTickets(addTicketsRequest.getTickets(), addTicketsRequest.getVendor_id());
            logger.log(Level.INFO, String.format("%s %s succeeded", className, methodName));
            return new AddTicketsResponse(true, "Tickets Added Successfully", addTicketsRequest.getTickets());
        } catch (Exception e) {
            logger.log(Level.INFO, String.format("%s %s succeeded with error: %s", className, methodName, e.getMessage()));
            return new AddTicketsResponse(false, e.getMessage(), null);
        }
    }
}
