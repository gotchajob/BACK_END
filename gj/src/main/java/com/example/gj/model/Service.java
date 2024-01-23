package com.example.gj.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Service {
    @Id
    private String id;
    private String title;
    private String logo;
    private String description;
    private String timeUsed;
    private String cost_lowest;
    private String cost_highest;
    private String subtitle;
    private Date created_at;
    private Date updated_at;
    private int status;
}
