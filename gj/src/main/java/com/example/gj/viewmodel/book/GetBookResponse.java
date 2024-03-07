package com.example.gj.viewmodel.book;

import com.example.gj.model.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class GetBookResponse {
    private List<BookResponse> bookList;
    private long total;

    public GetBookResponse(List<Book> list, long total) {
        bookList = new ArrayList<>();
        for (Book book : list) {
            bookList.add(new BookResponse(book));
        }

        this.total = total;
    }
}
