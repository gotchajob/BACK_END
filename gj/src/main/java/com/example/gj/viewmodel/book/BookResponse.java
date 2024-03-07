package com.example.gj.viewmodel.book;

import com.example.gj.model.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookResponse {
    private String id;
    private String customer;
    private String mentor;
    private LocalDate date;
    private int slot;
    private String address;
    private String note;

    public BookResponse(Book book) {
        if (book != null) {
            this.id = book.getId();
            this.customer = book.getCustomer();
            this.mentor = book.getMentorId();
            this.date = book.getDate();
            this.slot = book.getSlot();
            this.address = book.getAddress();
            this.note = book.getNote();
        }
    }
}
