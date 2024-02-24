package com.example.gj.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
    private int paymentId;
    private int serviceId;
    private int cost;
    private int status;
    private Date createdAt;
    private Date updateAt;
}

