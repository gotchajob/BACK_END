package com.example.gj.repository;

import com.example.gj.model.Book;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {

    List<Book> getBookByCustomerAndStatus(String customer, int status, Pageable pageable);
    long countByCustomerAndStatus(String customer, int status);
}
