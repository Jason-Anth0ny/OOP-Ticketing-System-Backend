package com.example.demo.Request;

public class AddTicketsRequest {
    private Integer tickets;
    private String vendor_id;

    public AddTicketsRequest(Integer tickets, String vendor_id) {
        this.tickets = tickets;
        this.vendor_id = vendor_id;
    }

    public AddTicketsRequest() {}

    public Integer getTickets() {
        return tickets;
    }

    public void setTickets(Integer tickets) {
        this.tickets = tickets;
    }

    public String getVendor_id() {
        return vendor_id;
    }

    public void setVendor_id(String vendor_id) {
        this.vendor_id = vendor_id;
    }
}
