package com.example.gj.controller;

import com.example.gj.model.Response;
import com.example.gj.service.BookService;
import com.example.gj.util.Role;
import com.example.gj.viewmodel.transaction.GetTransactionResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("")
    public ResponseEntity<Response<String>> demo() {
        try {

            return Response.success(null);
        } catch (Exception e) {
            return Response.error(e);
        }
    }

    @GetMapping("/get-by-user")
    @Secured(Role.USER)
    public ResponseEntity<Response<String>> getListByUser(@RequestParam(defaultValue = "1") int page,
                                                                          @RequestParam(defaultValue = "5") int limit,
                                                                          @RequestParam(defaultValue = "createdAt",required = false) String sortBy,
                                                                          @RequestParam(defaultValue = "asc",required = false) String sortOrder) {
        try {
            return Response.success(null);
        } catch (Exception e) {
            return Response.error(e);
        }
    }
}
