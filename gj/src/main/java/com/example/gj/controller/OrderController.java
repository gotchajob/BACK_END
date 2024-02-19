package com.example.gj.controller;

import com.example.gj.model.Response;
import com.example.gj.service.HomeService;
import com.example.gj.viewmodel.order_service.OrderServiceRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order-service")
public class OrderController {
    final HomeService homeService;

    public OrderController(HomeService homeService) {
        this.homeService = homeService;
    }

    @PostMapping("")
    public ResponseEntity<Response<Object>> orderService(@RequestBody OrderServiceRequest request) {
        try {
            homeService.orderService(request);
            return Response.success(null);
        } catch (Exception e) {
            return Response.error(e);
        }
    }
}
