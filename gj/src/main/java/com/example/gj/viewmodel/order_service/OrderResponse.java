package com.example.gj.viewmodel.order_service;

import com.example.gj.model.Order;
import lombok.Data;

import java.util.Date;


@Data
public class OrderResponse {
    private String id;
    private String email;
    private String name;
    private String phone;
    private String service;
    private String payment;
    private int status;
    private Date created;
    private int total;
    private String processingBy;

    public OrderResponse(Order order) {
        this.id = order.getId();
        this.email = order.getEmail();
        this.name = order.getName();
        this.phone = order.getPhone();
        this.service = order.getService().getTitle();
        this.payment = order.getPayment().getName();
        this.status = order.getStatus();
        this.created = order.getCreatedAt();
        this.total = order.getTotal();
        this.processingBy = order.getProcessingBy();
    }
}
