package com.example.gj.viewmodel.dash_board;

import lombok.Data;

import java.util.List;

@Data
public class TransactionDashBoardResponse {
    private String service;
    private List<Long> transactionPerDay;
    private long total;
    private long revenue;

    public TransactionDashBoardResponse(String service, List<Long> transactionPerDay, long revenue) {
        this.service = service;
        this.transactionPerDay = transactionPerDay;
        this.revenue = revenue;

        this.total = transactionPerDay.stream().mapToLong(Long::longValue).sum();
    }
}
