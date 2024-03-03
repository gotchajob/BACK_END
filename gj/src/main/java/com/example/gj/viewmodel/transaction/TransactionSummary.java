package com.example.gj.viewmodel.transaction;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TransactionSummary {
    private long count;
    private double sumTotal;
}
