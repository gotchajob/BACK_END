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
    private String userId;
    private int paymentId;
    private int cost;
    private String service_id;
    private int status;
    private Date created_at;
    private Date updated_at;
}

