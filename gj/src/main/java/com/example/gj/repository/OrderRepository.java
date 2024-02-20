package com.example.gj.repository;

import com.example.gj.model.Order;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {
    List<Order> getAllByStatusIn(List<Integer> status, Pageable pageable);
    int countByStatusIn(List<Integer> status);
}
