package com.example.gj.repository;

import com.example.gj.model.Transaction;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, String> {

    @Query("SELECT s.title, DAY(t.createdAt), COUNT(t), SUM(t.cost) FROM Transaction t join t.service s where t.createdAt >= :startDate AND t.createdAt <= :endDate GROUP BY s.title, DAY(t.createdAt)")
    List<Object[]> countTransactionPerDay(Date startDate, Date endDate);

    List<Transaction> getAllByStatus(int status, Pageable pageable);

    long countByStatus(int status);

    @Query("SELECT count(t), sum(t.cost) from Transaction t where t.status = 1")
    Long[] countAndSumTotalTransaction();

}
