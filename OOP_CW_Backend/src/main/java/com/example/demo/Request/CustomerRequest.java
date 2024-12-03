package com.example.demo.Request;

public class CustomerRequest {
    private String customerName;

    public CustomerRequest(String customerName) {
        this.customerName = customerName;
    }

    public CustomerRequest(){}

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}
