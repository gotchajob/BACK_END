package com.example.gj.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;
@Entity
@Data
public class Answer {
    @Id
    private String id;
    private String questionId;
    private String answer;
    private String customerId;
    private int status;
    private Date created_at;
    private Date updated_at;
}
