package com.example.gj.service;

import com.example.gj.model.AccessLog;
import com.example.gj.repository.AccessLogRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.HashMap;
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

    public List<Long> countAccessLogsByDay(LocalDate startDate, LocalDate endDate) {
        List<Object[]> results = accessLogRepository.countAccessLogsPerDay(startDate, endDate);

        // Create a map to store counts for each day
        Map<Integer, Long> countsMap = new HashMap<>();

        // Initialize counts for all days to 0
        LocalDate currentDate = startDate;
        while (!currentDate.isAfter(endDate)) {
            countsMap.put(currentDate.getDayOfMonth(), 0L);
            currentDate = currentDate.plusDays(1);
        }

        // Populate counts from query results
        for (Object[] result : results) {
            Integer dayOfMonth = (Integer) result[0];
            Long count = (Long) result[1];
            countsMap.put(dayOfMonth, count);
        }

        // Convert map values to list
        List<Long> counts = new ArrayList<>(countsMap.values());
        return counts;
    }


}
