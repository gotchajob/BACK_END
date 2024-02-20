package com.example.gj.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Service {
    @Id
    private int id;
    private String title;
    private String logo;
    private String description;
    private String timeUsed;
    private String costLowest;
    private String costHighest;
    private String subtitle;
    private Date createdAt;
    private Date updatedAt;
    private int status;

    public Service(int id) {
        this.id = id;
    }
}
