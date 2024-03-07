package com.example.gj.model;

import com.example.gj.util.Status;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notify {
    @Id
    private String id;
    private String email;
    private String title;
    private String description;
    private int status;
    private Date createdAt;
    private Date updatedAt;

    public Notify(String email, String title, String description) {
        this.id = UUID.randomUUID().toString();
        this.email = email;
        this.title = title;
        this.description = description;
        this.status = Status.ACTIVE;
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }
}
