package com.example.gj.repository;

import com.example.gj.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, String> {

    @Query("SELECT s.title, DAY(t.createdAt), COUNT(t), SUM(t.cost) FROM Transaction t join t.service s where t.createdAt >= :startDate AND t.createdAt <= :endDate GROUP BY s.title, DAY(t.createdAt)")
    List<Object[]> countTransactionPerDay(Date startDate, Date endDate);

}
