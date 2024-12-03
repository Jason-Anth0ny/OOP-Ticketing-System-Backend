package com.example.demo.models;

public class TicketPool {
    private static Integer maximumTicketCapacity;
    private static Integer ticketsAvailable = 0;

    public TicketPool(Integer ticketsAvailable) {
        this.maximumTicketCapacity = ticketsAvailable;
    }

    public static Integer getMaximumTicketCapacity() {
        return maximumTicketCapacity;
    }

    public void setTicketsAvailable(Integer ticketsAvailable) {
        this.ticketsAvailable = ticketsAvailable;
    }

    public static void setMaximumTicketCapacity(Integer maximumTicketCapacity) {
        TicketPool.maximumTicketCapacity = maximumTicketCapacity;
    }

    public static Integer getTicketsAvailable() {
        return ticketsAvailable;
    }
}
