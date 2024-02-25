package com.example.gj.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Transaction {
    @Id
    private String id;
    private String email;
    private String phone;
    private String fullName;
    private long cost;
    private int status;
    private Date createdAt;
    private Date updateAt;
    private String userId;

    @ManyToOne
    @JoinColumn(name = "payment_id", referencedColumnName = "id")
    private Payment payment;

    @ManyToOne
    @JoinColumn(name = "service_id", referencedColumnName = "id")
    private Service service;
}

