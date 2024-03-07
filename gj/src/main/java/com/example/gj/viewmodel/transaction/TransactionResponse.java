package com.example.gj.viewmodel.transaction;

import com.example.gj.model.Transaction;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionResponse {
    private String id;
    private String email;
    private String name;
    private String phone;
    private String serviceName;
    private String paymentName;
    private long total;
    private Date createdAt;
    private String orderId;

    public TransactionResponse(Transaction transaction) {
        if (transaction != null) {
            this.id = transaction.getId();
            this.email = transaction.getEmail();
            this.name = transaction.getFullName();
            this.phone = transaction.getPhone();
            this.serviceName = transaction.getService().getTitle();
            this.paymentName = transaction.getPayment().getName();
            this.total = transaction.getCost();
            this.createdAt = transaction.getCreatedAt();
            this.orderId = transaction.getOrderId();
        }
    }
}
