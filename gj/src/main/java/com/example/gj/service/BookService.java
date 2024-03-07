package com.example.gj.service;

import com.example.gj.model.Book;
import com.example.gj.model.Order;
import com.example.gj.repository.BookRepository;
import com.example.gj.util.Status;
import com.example.gj.util.Util;
import com.example.gj.viewmodel.book.GetBookResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final UserService userService;

    public BookService(BookRepository bookRepository, UserService userService) {
        this.bookRepository = bookRepository;
        this.userService = userService;
    }


    public boolean createBookingForOrder(Order order) {
        if (order == null) {
            return false;
        }

        Book book = new Book(order.getEmail());
        bookRepository.save(book);

        return true;
    }

    public GetBookResponse getBookByUser(int page, int limit, String sortBy, String sortOrder) {
        String currentEmail = userService.getCurrentUsername();
        Pageable pageable = Util.generatePage(page, limit, sortBy, sortOrder);
        List<Book> bookList = bookRepository.getBookByCustomerAndStatus(currentEmail, Status.ACTIVE, pageable);
        long total = bookRepository.countByCustomerAndStatus(currentEmail, Status.ACTIVE);

        return new GetBookResponse(bookList, total);
    }
}
