package com.example.gj.viewmodel.order_service;

import lombok.Data;

@Data
public class CreateOrderRequest {
    private int serviceId;
    private String email;
    private String name;
    private String phone;
    private int paymentId;
    private int total;
}
