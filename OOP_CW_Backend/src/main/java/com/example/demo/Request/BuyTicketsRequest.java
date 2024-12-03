package com.example.demo.Request;

public class BuyTicketsRequest {
    private String vendor_name;
    private Integer number_of_tickets;
    private String customer_id;

    public BuyTicketsRequest(String vendor_name, String customer_name, Integer number_of_tickets) {
        this.vendor_name = vendor_name;
        this.customer_id = customer_name;
        this.number_of_tickets = number_of_tickets;
    }

    public BuyTicketsRequest() {}

    public String getVendor_name() {
        return vendor_name;
    }

    public void setVendor_name(String vendor_name) {
        this.vendor_name = vendor_name;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public Integer getNumber_of_tickets() {
        return number_of_tickets;
    }

    public void setNumber_of_tickets(Integer number_of_tickets) {
        this.number_of_tickets = number_of_tickets;
    }
}
