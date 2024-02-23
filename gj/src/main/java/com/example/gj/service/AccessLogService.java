package com.example.gj.service;

import com.example.gj.model.AccessLog;
import com.example.gj.repository.AccessLogRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class AccessLogService {
    final AccessLogRepository accessLogRepository;

    public AccessLogService(AccessLogRepository accessLogRepository) {
        this.accessLogRepository = accessLogRepository;
    }

    public void access() {
        AccessLog accessLog = new AccessLog();
        accessLog.setAccessTime(LocalDateTime.now());
        accessLog.setAccessDate(LocalDate.now());
        accessLogRepository.save(accessLog);
    }

    public List<Long> getAccessCountByMonth(int year, int month) {
        List<Long> list = new ArrayList<>();
        LocalDate startDate = LocalDate.of(year, month, 1);
        LocalDate endDate = startDate.with(TemporalAdjusters.lastDayOfMonth());

        for (LocalDate date = startDate; !date.isAfter(endDate);date = date.plusDays(1)) {
            long count = accessLogRepository.countAccessLogsByAccessDate(date);
            list.add(count);
        }

        return list;
    }


}
