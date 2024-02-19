package com.example.gj.model;

import com.example.gj.util.Status;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class SubscribeNews {
    @Id
    private String id;
    private String email;
    private int status;
    private Date createdAt;
    private Date updatedAt;

    public SubscribeNews(String email) {
        this.id = UUID.randomUUID().toString();
        this.email = email;
        this.status = Status.ACTIVE;
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }
}
