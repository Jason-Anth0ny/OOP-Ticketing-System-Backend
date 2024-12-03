package com.example.demo.service;

import com.example.demo.Response.AddTicketsResponse;
import com.example.demo.Response.VendorResponse;
import com.example.demo.models.Vendor;

public interface VendorService {
    public boolean addVendor(Vendor vendor);
    public Vendor findVendorById(String vendorId);
    public AddTicketsResponse addTickets(int tickets, String vendorId);
    public Integer getTicketsByVendorId(String vendorId);
    public void sellTickets(int ticketsSold, String vendorId);
}
