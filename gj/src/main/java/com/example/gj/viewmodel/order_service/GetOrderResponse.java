package com.example.gj.viewmodel.order_service;

import com.example.gj.model.Order;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class GetOrderResponse {
    private List<OrderResponse> orderList;
    private int total;

    public GetOrderResponse(List<Order> list, int total) {
        orderList = new ArrayList<>();

        for (Order o : list) {
            orderList.add(new OrderResponse(o));
        }

        this.total = total;
    }
}
