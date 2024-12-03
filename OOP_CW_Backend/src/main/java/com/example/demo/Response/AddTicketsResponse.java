package com.example.demo.Response;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddTicketsResponse {
    private boolean success;
    private String message;
    private Integer tickets_added;

    public AddTicketsResponse(boolean success, String message, Integer tickets_added) {
        this.success = success;
        this.message = message;
        this.tickets_added = tickets_added;
    }

    public AddTicketsResponse() {}

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Integer getTickets_added() {
        return tickets_added;
    }

    public void setTickets_added(Integer tickets_added) {
        this.tickets_added = tickets_added;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
