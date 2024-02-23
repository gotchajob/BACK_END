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
}
