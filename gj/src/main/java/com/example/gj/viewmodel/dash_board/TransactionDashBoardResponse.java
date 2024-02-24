package com.example.gj.viewmodel.dash_board;

import lombok.Data;

import java.util.List;

@Data
public class TransactionDashBoardResponse {
    private String service;
    private List<Long> transactionPerDay;
    private long total;
    private long revenue;
}
