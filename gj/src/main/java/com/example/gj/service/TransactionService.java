package com.example.gj.service;

import com.example.gj.config.response.Message;
import com.example.gj.model.Order;
import com.example.gj.model.Transaction;
import com.example.gj.model.User;
import com.example.gj.repository.TransactionRepository;
import com.example.gj.util.Util;
import com.example.gj.viewmodel.dash_board.TransactionDashBoardResponse;
import com.example.gj.viewmodel.transaction.GetTransactionResponse;
import com.example.gj.viewmodel.transaction.TransactionResponse;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final ServiceService serviceService;
    private final UserService userService;

    public TransactionService(TransactionRepository transactionRepository, ServiceService serviceService, UserService userService) {
        this.transactionRepository = transactionRepository;
        this.serviceService = serviceService;
        this.userService = userService;
    }


    public List<TransactionDashBoardResponse> getTransactionDashBoard(Date startDate, Date endDate) throws Exception {
        //String[] titles = new String[]{"Mock Interview", "Partner Training", "CV", "Smart TUM"};
        List<String> titles = serviceService.getServiceTitle();
        if (titles == null || titles.size() == 0) {
            throw new Exception(Message.NOT_FOUND + "Service title");
        }
        int day = Util.getDayOfMonth(endDate);
        List<Long> defaultDay = new ArrayList<>();
        while (defaultDay.size() < day) {
            defaultDay.add(0L);
        }

        List<Object[]> listObjectPerDay = transactionRepository.countTransactionPerDay(startDate, endDate);
        Map<String, List<Long>> serviceTransactionCounts = new HashMap<>();

        Map<String, Long> serviceRevenueMap = new HashMap<>();
        for (String title : titles) {
            serviceTransactionCounts.putIfAbsent(title, new ArrayList<>(defaultDay));
            serviceRevenueMap.put(title, 0L);
        }

        // Process the query results and populate serviceTransactionCounts map
        for (Object[] result : listObjectPerDay) {
            String serviceTitle = (String) result[0];
            Integer dayOfMonth = (Integer) result[1];
            Long count = (Long) result[2];
            Long cost = (Long) result[3];

            serviceTransactionCounts.putIfAbsent(serviceTitle, new ArrayList<>());
            List<Long> countsList = serviceTransactionCounts.get(serviceTitle);

            // Ensure the list is long enough to accommodate the count for the current day
            while (countsList.size() < dayOfMonth) {
                countsList.add(0L);
            }

            countsList.set(dayOfMonth - 1, count);

            // Update revenue for the service
            serviceRevenueMap.put(serviceTitle, serviceRevenueMap.getOrDefault(serviceTitle, 0L) + cost);
        }

        // Convert the map into a list of TransactionDashBoardResponse objects
        List<TransactionDashBoardResponse> dashBoardResponses = new ArrayList<>();
        for (String title : titles) {
            String serviceTitle = title;
            List<Long> transactionPerDay = serviceTransactionCounts.get(title);
            long revenue = serviceRevenueMap.get(serviceTitle);

            dashBoardResponses.add(new TransactionDashBoardResponse(serviceTitle, transactionPerDay, revenue));
        }

        return dashBoardResponses;
    }

    public GetTransactionResponse getTransactionList(int page, int limit, String sortBy, String sortOrder) {
        Sort.Direction direction = sortOrder.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page - 1, limit, direction, sortBy);

        List<Transaction> list = transactionRepository.getAllByStatus(1, pageable);

        List<TransactionResponse> responseList = new ArrayList<>();

        for (Transaction t : list) {
            responseList.add(new TransactionResponse(t));
        }

        long total = transactionRepository.countByStatus(1);

        return new GetTransactionResponse(responseList, total);
    }

    public boolean createTransaction(Order order) {
        if (order == null) {
            return false;
        }

        String currentUserId = userService.getCurrentUserId();
        Transaction transaction = new Transaction(order, currentUserId);

        transactionRepository.save(transaction);

        return true;
    }
}
