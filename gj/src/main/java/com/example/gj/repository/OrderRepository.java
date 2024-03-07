package com.example.gj.repository;

import com.example.gj.model.Order;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {
    List<Order> getAllByStatusIn(List<Integer> status, Pageable pageable);
    int countByStatusIn(List<Integer> status);
    @Query("SELECT SUM(s.costLowest) FROM Order o JOIN o.service s WHERE o.createdAt >= :startDate AND o.createdAt <= :endDate")
    Long sumServicePricesInDay(Date startDate, Date endDate);

    @Query("SELECT DAY(o.createdAt), COUNT(o) FROM Order o WHERE o.createdAt >= :startDate AND o.createdAt <= :endDate GROUP BY DAY(o.createdAt)")
    List<Object[]> countOrdersByDay(Date startDate, Date endDate);

    long countByCreatedAtBefore(Date date);

    Order getOrderByCodeAndStatus(String code, int status);

    List<Order> getAllByStatusInAndEmail(List<Integer> status,String email, Pageable pageable);
    int countByStatusInAndEmail(List<Integer> status, String email);


}
