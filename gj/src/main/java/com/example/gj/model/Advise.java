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
public class Advise {
    @Id
    private String id;
    private String fullName;
    private String phone;
    private String email;
    private String advise;
    private int status;
    private Date createdAt;
    private Date updatedAt;

    public Advise(String fullName, String phone, String mail, String advise) {
        this.id = UUID.randomUUID().toString();
        this.fullName = fullName;
        this.phone = phone;
        this.email = mail;
        this.advise = advise;
        this.status = Status.ACTIVE;
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }
}
