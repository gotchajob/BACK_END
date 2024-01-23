package com.example.gj.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class WebsiteFeedback {
    @Id
    private String id;
    private String description;
    private int rating;
    private String fullName;
    private String avatar;
    private String job;
    private int status;
    private Date created_at;
    private Date update_at;

}
