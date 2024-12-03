package com.example.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.spi.TreatedNavigablePath;
@Entity
@Table(name = "transaction")
public class Transaction {
    @Id
    private String id;
    private String transactionType;
    private String time;
    private String customerId;
    private String vendorName;

    public Transaction(String id, String transactionType, String time, String customerId, String vendorName) {
        this.id = id;
        this.transactionType = transactionType;
        this.time = time;
        this.customerId = customerId;
        this.vendorName = vendorName;
    }

    public Transaction() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }
}
