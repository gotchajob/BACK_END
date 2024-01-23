package com.example.gj.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class ServicePayment {
    @Id
    private String id;
    private String serviceId;
    private String received;
    private String description;

}
