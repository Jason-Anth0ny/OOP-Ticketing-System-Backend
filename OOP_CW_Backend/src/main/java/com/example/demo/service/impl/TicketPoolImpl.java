package com.example.demo.service.impl;

import com.example.demo.DemoApplication;
import com.example.demo.models.TicketPool;
import com.example.demo.service.TicketPoolService;
import org.springframework.stereotype.Service;

@Service
public class TicketPoolImpl implements TicketPoolService {
    TicketPool ticketPool = DemoApplication.ObjectRegistry.getTicketPool();
    String className = this.getClass().getSimpleName();



    @Override
    public synchronized Integer addTicketsToPool(int ticketsAdded) {
        try {
            this.ticketPool.setTicketsAvailable(ticketPool.getTicketsAvailable() + ticketsAdded);
            return this.ticketPool.getTicketsAvailable();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public synchronized Integer removeTicketsFromPool(int ticketsRemoved) {
        try {
            this.ticketPool.setTicketsAvailable(TicketPool.getTicketsAvailable() - ticketsRemoved);
            return this.ticketPool.getTicketsAvailable();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
