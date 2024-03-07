package com.example.gj.model;

import com.example.gj.util.Status;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    private String id;
    private String customer;
    private String mentorId;
    private String admin;
    private String address;
    private int slot;
    private LocalDate date;
    private int status;
    private Date createdAt;
    private Date updatedAt;
    private String note;

    public Book(String email) {
        this.id = UUID.randomUUID().toString();
        this.customer = email;
        this.mentorId = "n/a";
        this.admin = "n/a";
        this.address = "191 Nam Kỳ Khởi Nghĩa, Phường 7, Quận 3, HCM";
        this.slot = new Random().nextInt(4);
        this.date = LocalDate.now().plusDays(5);
        this.status = Status.ACTIVE;
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }
}
