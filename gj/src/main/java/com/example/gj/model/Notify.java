package com.example.gj.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Notify {
    @Id
    private String id;
    private String userId;
    private String title;
    private String description;
    private int status;
    private Date createdAt;
    private Date updatedAt;
}
