package com.example.demo.Response;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerResponse {
    private String name;
    private int ticketsBought;

    public CustomerResponse(String name, int tickets_Bought) {
        this.name = name;
        this.ticketsBought = tickets_Bought;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTicketsBought() {
        return ticketsBought;
    }

    public void setTicketsBought(int ticketsBought) {
        this.ticketsBought = ticketsBought;
    }
}
