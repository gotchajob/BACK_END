package com.example.gj.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;
@Entity
@Data
public class Partner {
    @Id
    private String id;
    private String name;
    private String logo;
    private String url;
    private Date created_at;
    private Date updated_at;
    private int status;

}
