package com.example.demo.Response;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class VendorResponse {
    private boolean success;
    private String vendorName;
    private String vendorId;
    private Integer tickets;

    public VendorResponse(String vendorName, String vendorId, Integer tickets, boolean success) {
        this.vendorName = vendorName;
        this.vendorId = vendorId;
        this.tickets = tickets;
        this.success = success;
    }

    public VendorResponse() {}

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }

    public Integer getTickets() {
        return tickets;
    }

    public void setTickets(Integer tickets) {
        this.tickets = tickets;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }
}
