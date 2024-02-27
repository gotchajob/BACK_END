package com.example.gj.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    @Id
    private String id;
    private String email;
    private String phone;
    private String fullName;
    private long cost;
    private int status;
    private Date createdAt;
    private Date updatedAt;
    private String userId;

    @ManyToOne
    @JoinColumn(name = "payment_id", referencedColumnName = "id")
    private Payment payment;

    @ManyToOne
    @JoinColumn(name = "service_id", referencedColumnName = "id")
    private Service service;

    public Transaction(Order order, String userId) {
        if (order != null) {
            this.id = UUID.randomUUID().toString();
            this.email = order.getEmail();
            this.phone = order.getPhone();
            this.fullName = order.getName();
            this.cost = order.getTotal();
            this.status = 1;
            this.createdAt = new Date();
            this.updatedAt = new Date();
            this.userId = userId;

            this.payment = order.getPayment();
            this.service = order.getService();
        }
    }
}

