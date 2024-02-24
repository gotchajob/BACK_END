package com.example.gj.viewmodel.dash_board;

import lombok.Data;

import java.util.List;

@Data
public class GetTransactionDashBoardResponse {
    private TransactionDashBoardResponse totalTransaction;
    private List<TransactionDashBoardResponse> listTransaction;
}
