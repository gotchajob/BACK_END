package com.example.gj.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;
@Entity
@Data
public class ServiceFeedback {
    @Id
    private String id;
    private String description;
    private int rating;
    private String userId;
    private String serviceId;
    private Date created_at;
    private Date updated_at;
    private int status;
}
