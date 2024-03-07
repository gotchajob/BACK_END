package com.example.gj.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Mentor {
    @Id
    private String id;
    private String email;
    private String phone;
    private String fullName;
    private String address;
    private String image;
    private String job;
    private int status;
    private Date createdAt;
    private Date updatedAt;
    private String note;
}
