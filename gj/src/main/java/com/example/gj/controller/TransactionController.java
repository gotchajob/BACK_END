package com.example.gj.controller;

import com.example.gj.model.Response;
import com.example.gj.service.TransactionService;
import com.example.gj.util.Role;
import com.example.gj.viewmodel.advise.GetAdviseResponse;
import com.example.gj.viewmodel.transaction.GetTransactionResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("")
    //@Secured(Role.ADMIN) //TODO: uncomment this
    public ResponseEntity<Response<GetTransactionResponse>> getList(@RequestParam(defaultValue = "1") int page,
                                                                    @RequestParam(defaultValue = "5") int limit,
                                                                    @RequestParam(defaultValue = "createdAt",required = false) String sortBy,
                                                                    @RequestParam(defaultValue = "asc",required = false) String sortOrder) {
        try {
            GetTransactionResponse responses = transactionService.getTransactionList(page, limit, sortBy, sortOrder);
            return Response.success(responses);
        } catch (Exception e) {
            return Response.error(e);
        }
    }
}
