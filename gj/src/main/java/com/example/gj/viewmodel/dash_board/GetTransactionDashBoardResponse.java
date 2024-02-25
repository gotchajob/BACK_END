package com.example.gj.viewmodel.dash_board;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class GetTransactionDashBoardResponse {
    private TransactionDashBoardResponse totalTransaction;
    private List<TransactionDashBoardResponse> listTransaction;
}
