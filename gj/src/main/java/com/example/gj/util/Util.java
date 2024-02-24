package com.example.gj.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Util {
    public static String date() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date currentDateTime = new Date();
        return dateFormat.format(currentDateTime);
    }

    public static String currentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication.isAuthenticated()) {
            return SecurityContextHolder.getContext().getAuthentication().getName();
        }
        return null;
    }
    public static Date expipyDate(int expiryTimeInMinute) {
        return new Date(System.currentTimeMillis() + (1000L *60*expiryTimeInMinute));
    }

    public static String generateRandomNumberString(int length) {
        if (length <= 0) {
            throw new IllegalArgumentException("Length must be greater than 0");
        }

        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int randomNumber = random.nextInt(10);
            stringBuilder.append(randomNumber);
        }

        return stringBuilder.toString();
    }

    public static Date[] getStartDateAndEndDate(int year, int month) {
        Calendar startCal = Calendar.getInstance();
        startCal.set(year, month - 1, 1, 0, 0, 0);
        startCal.set(Calendar.MILLISECOND, 0);
        Date startDate = startCal.getTime();

        Calendar endCal = Calendar.getInstance();
        endCal.set(year, month - 1, startCal.getActualMaximum(Calendar.DAY_OF_MONTH), 23, 59, 59);
        endCal.set(Calendar.MILLISECOND, 999);
        Date endDate = endCal.getTime();

        return new Date[]{startDate, endDate};

    }

    public static long sumList(List<Long> list) {
        long sum = 0;
        for (Long value : list) {
            sum += value;
        }
        return sum;
    }

    public static List<Long> convertToList(Date startDate, Date endDate, List<Object[]> results) {
        if (results == null) {
            return new ArrayList<>();
        }
        Map<Integer, Long> countsMap = new HashMap<>();

        // Initialize counts for all days to 0
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        while (!calendar.getTime().after(endDate)) {
            countsMap.put(calendar.get(Calendar.DAY_OF_MONTH), 0L);
            calendar.add(Calendar.DAY_OF_MONTH, 1);
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
