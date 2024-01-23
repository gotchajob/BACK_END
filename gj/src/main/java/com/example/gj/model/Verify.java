package com.example.gj.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Verify {
    @Id
    private String id;
    private String userId;
    private String code;
    private int status;
    private Date expireDate;
    private Date createdAt;
    private Date updatedAt;
    private int type;
}
