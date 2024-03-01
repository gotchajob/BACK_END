package com.example.gj.model;

import com.example.gj.viewmodel.order_service.CreateOrderRequest;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
public class Order {
    @Id
    private String id;
    private String email;
    private String name;
    private String phone;
    private int total;
    private int status;
    private Date createdAt;
    private Date updatedAt;
    private String processingBy;
    private String code;

    @ManyToOne
    @JoinColumn(name = "payment_id", referencedColumnName = "id")
    private Payment payment;

    @ManyToOne
    @JoinColumn(name = "service_id", referencedColumnName = "id")
    private Service service;


    public Order(CreateOrderRequest request, String code) {
        this.id = UUID.randomUUID().toString();
        this.service = new Service(request.getServiceId());
        this.email = request.getEmail();
        this.phone = request.getPhone();
        this.name = request.getName();
        this.total = request.getTotal();
        this.payment = new Payment(request.getPaymentId());
        this.status = 1;
        this.createdAt = new Date();
        this.updatedAt = new Date();
        this.code = code;
    }
}
