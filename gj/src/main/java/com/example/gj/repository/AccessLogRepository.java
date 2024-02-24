package com.example.gj.repository;

import com.example.gj.model.AccessLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Repository
public interface AccessLogRepository extends JpaRepository<AccessLog, Integer> {
    long countAccessLogsByAccessDate(LocalDate date);

    @Query("SELECT DAY(o.accessDate), COUNT(o) FROM AccessLog o WHERE o.accessDate >= :startDate AND o.accessDate <= :endDate GROUP BY DAY(o.accessDate)")
    List<Object[]> countAccessLogsPerDay(LocalDate startDate, LocalDate endDate);

    long countAccessLogsByAccessDateBefore(LocalDate date);
}
