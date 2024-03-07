package com.example.gj.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Book {
    @Id
    private String id;
    private String customer;
    private String mentor_id;
    private String admin;
    private int serviceId;
    private String address;
    private Date startDate;
    private Date endDate;
    private int status;
    private Date createdAt;
    private Date updatedAt;
    private String note;
}
