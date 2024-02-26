package com.example.gj.viewmodel.transaction;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class GetTransactionResponse {
    List<TransactionResponse> transactionList;
    long total;
}
