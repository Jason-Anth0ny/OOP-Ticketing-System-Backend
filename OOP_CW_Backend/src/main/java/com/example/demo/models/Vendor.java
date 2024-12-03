package com.example.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Scanner;

@Entity
@Table(name = "Vendor")
public class Vendor implements Runnable {
    @Id
    private String vendorId;
    private String vendorName;
    private int ticketsByVendor;

    @Transient
    private ServerSocket serverSocket;
    @Transient
    private boolean active = true;
    @Transient
    Scanner scanner = new Scanner(System.in);

    public Vendor(String vendorName, String vendorId) throws IOException {
        this.vendorName = vendorName;
        this.vendorId = vendorId;
    }

    public Vendor(String vendorName, int ticketsByVendor) throws IOException {
        this.vendorName = vendorName;
        this.ticketsByVendor = ticketsByVendor;
    }

    public int getTicketsByVendor() {
        return ticketsByVendor;
    }

    public Vendor() {}

    public String getVendorName() {
        return vendorName;
    }

    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    @Override
    public void run() {
        while (active) {
            try {
                Thread.sleep(7000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println(vendorName + " was interrupted.");
                break;
            }
        }

    }
}
