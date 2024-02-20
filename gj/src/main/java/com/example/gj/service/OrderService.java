package com.example.gj.service;

import com.example.gj.config.response.Message;
import com.example.gj.model.Order;
import com.example.gj.repository.OrderRepository;
import com.example.gj.viewmodel.order_service.CreateOrderRequest;
import com.example.gj.viewmodel.order_service.GetOrderResponse;
import com.example.gj.viewmodel.order_service.OrderServiceRequest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    final OrderRepository orderRepository;
    final EmailService emailService;
    final UserService userService;

    public OrderService(OrderRepository orderRepository, EmailService emailService, UserService userService) {
        this.orderRepository = orderRepository;
        this.emailService = emailService;
        this.userService = userService;
    }


    public boolean orderService(CreateOrderRequest request) throws Exception {
        if (request == null || request.getName() == null || request.getPhone() == null || request.getEmail() == null) {
            throw new Exception(Message.NULL_INPUT);
        }

        if (request.getTotal() < 0 || request.getServiceId() <= 0 || request.getPaymentId() <= 0) {
            throw  new Exception(Message.INVALID_INPUT);
        }

        orderRepository.save(new Order(request));

        emailService.sendMailOrderService(request.getName(), request.getPhone(), request.getEmail());

        return true;
    }

    public GetOrderResponse get(int page, int limit, String sortBy, String sortOrder) {
        Sort.Direction direction = sortOrder.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page-1, limit, direction, sortBy);
        List<Integer> statusList = Arrays.asList(1,2,3);

        List<Order> orders = orderRepository.getAllByStatusIn(statusList, pageable);
        int total = orderRepository.countByStatusIn(statusList);

        return new GetOrderResponse(orders, total);
    }

    public boolean updateStatus(String id, int status) throws Exception {
        if (id == null || status < 1 || status > 3) {
            throw new Exception(Message.INVALID_INPUT);
        }

        Optional<Order> orderOptional = orderRepository.findById(id);
        if (orderOptional.isEmpty()) {
            throw new Exception(Message.NOT_FOUND);
        }

        Order order = orderOptional.get();
        if (status == order.getStatus()) {
            return false;
        }

        String userName = userService.getCurrentUsername();
        if (status == 1) {
            order.setProcessingBy(null);
        }

        if (status == 2) {
            if (order.getStatus() != 1) {
                throw new Exception("old status is not 1");
            }
            order.setProcessingBy(userName);
        }

        if (status == 3) {
            if (order.getStatus() != 2) {
                throw new Exception("status is not processing");
            }
            if (!order.getProcessingBy().equals(userName)) {
                throw new Exception("User is not allow to finish order!");
            }

            order.setProcessingBy(null);
        }



        order.setStatus(status);
        order.setUpdatedAt(new Date());
        orderRepository.save(order);

        return true;
    }
}
