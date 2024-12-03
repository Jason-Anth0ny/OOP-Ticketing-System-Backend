package com.example.demo.Request;

public class TicketRequest {
    private String vendorName;
    private int numberOfTickets;

    public TicketRequest() {}
    public TicketRequest(String vendorName, int numberOfTickets) {
        this.vendorName = vendorName;
        this.numberOfTickets = numberOfTickets;
    }

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
