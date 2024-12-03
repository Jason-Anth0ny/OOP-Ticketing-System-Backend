package com.example.demo.Request;

public class VendorRequest {
    private String vendorName;
    private int numberOfTickets;

    public VendorRequest(String vendorName, int numberOfTickets) {
        this.vendorName = vendorName;
        this.numberOfTickets = numberOfTickets;
    }

    public VendorRequest() {}

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public int getNumberOfTickets() {
        return numberOfTickets;
    }

    public void setNumberOfTickets(int numberOfTickets) {
        this.numberOfTickets = numberOfTickets;
    }
}
