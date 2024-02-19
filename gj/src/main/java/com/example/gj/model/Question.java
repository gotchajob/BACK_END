package com.example.gj.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Question {
    @Id
    private String id;
    private int questionTypeId;
    private String question;
    private Date createdAt;
    private Date updatedAt;
    private int status;
}
