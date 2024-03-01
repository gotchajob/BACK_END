package com.example.gj.service;

import com.example.gj.config.response.Message;
import com.example.gj.model.Order;
import com.example.gj.repository.OrderRepository;
import com.example.gj.util.Util;
import com.example.gj.viewmodel.order_service.CreateOrderRequest;
import com.example.gj.viewmodel.order_service.GetOrderResponse;
import com.example.gj.viewmodel.order_service.OrderResponse;
import com.example.gj.viewmodel.order_service.OrderServiceRequest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final EmailService emailService;
    private final UserService userService;
    private final TransactionService transactionService;


    public OrderService(OrderRepository orderRepository, EmailService emailService, UserService userService, TransactionService transactionService) {
        this.orderRepository = orderRepository;
        this.emailService = emailService;
        this.userService = userService;
        this.transactionService = transactionService;
    }


    public OrderResponse orderService(CreateOrderRequest request) throws Exception {
        if (request == null || request.getName() == null || request.getPhone() == null || request.getEmail() == null) {
            throw new Exception(Message.NULL_INPUT);
        }

        if (request.getTotal() < 0 || request.getServiceId() <= 0 || request.getPaymentId() <= 0) {
            throw  new Exception(Message.INVALID_INPUT);
        }

        String code = getOrderCode();
        if (code == null) {
            throw new Exception("Error when generate code for order");
        }

        Order order =  orderRepository.save(new Order(request, code));

        emailService.sendMailOrderService(request.getName(), request.getPhone(), request.getEmail(), code);

        return new OrderResponse(order);
    }

    private String getOrderCode() {
        int count = 0;
        String code = null;
        while (count < 5) {
            code = Util.generateRandomCode();
            Order order = orderRepository.getOrderByCodeAndStatus(code, 1);
            if (order == null) {
                break;
            }
        }

        if (count == 5) {
            return null;
        }

        return code;
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
        if (id == null || status < 0 || status > 3) {
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

            transactionService.createTransaction(order);
        }

        order.setStatus(status);
        order.setUpdatedAt(new Date());
        orderRepository.save(order);

        return true;
    }

    public List<Long> countOrderPerDay(Date startDate, Date endDate) {
        List<Object[]> results = orderRepository.countOrdersByDay(startDate, endDate);
        return Util.convertToList(startDate, endDate, results);
    }

    public long totalRevenue(Date startDate, Date endDate) {
        Long result =  orderRepository.sumServicePricesInDay(startDate, endDate);
        return result == null ? 0 : result.longValue();
    }
    public long totalOrderBefore(Date endDate) {
        return orderRepository.countByCreatedAtBefore(endDate);
    }
}
