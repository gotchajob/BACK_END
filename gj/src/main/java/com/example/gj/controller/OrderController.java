package com.example.gj.controller;

import com.example.gj.model.Response;
import com.example.gj.service.HomeService;
import com.example.gj.service.OrderService;
import com.example.gj.util.Role;
import com.example.gj.viewmodel.advise.UpdateStatusRequest;
import com.example.gj.viewmodel.order_service.CreateOrderRequest;
import com.example.gj.viewmodel.order_service.GetOrderResponse;
import com.example.gj.viewmodel.order_service.OrderResponse;
import com.example.gj.viewmodel.order_service.OrderServiceRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order-service")
public class OrderController {
    final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("")
    public ResponseEntity<Response<OrderResponse>> orderService(@RequestBody CreateOrderRequest request) {
        try {
            OrderResponse response  = orderService.orderService(request);
            return Response.success(response);
        } catch (Exception e) {
            return Response.error(e);
        }
    }

    @GetMapping("")
    @Secured(Role.ADMIN)
    public ResponseEntity<Response<GetOrderResponse>> get(@RequestParam(defaultValue = "1") int page,
                                                @RequestParam(defaultValue = "5") int limit,
                                                @RequestParam(defaultValue = "createdAt",required = false) String sortBy,
                                                @RequestParam(defaultValue = "asc",required = false) String sortOrder) {
        try {
            GetOrderResponse response = orderService.get(page, limit, sortBy, sortOrder);
            return Response.success(response);
        } catch (Exception e) {
            return Response.error(e);
        }
    }

    @PostMapping("/update-status")
    @Secured(Role.ADMIN)
    public ResponseEntity<Response<String>> updateStatus(@RequestBody UpdateStatusRequest request) {
        try {
            orderService.updateStatus(request.getId(), request.getStatus());
            return Response.success(null);
        } catch (Exception e) {
            return Response.error(e);
        }
    }
}
