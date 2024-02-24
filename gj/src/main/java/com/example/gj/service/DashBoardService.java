package com.example.gj.service;

import com.example.gj.config.response.Message;
import com.example.gj.util.Util;
import com.example.gj.viewmodel.dash_board.GetOrderDashBoardResponse;
import com.example.gj.viewmodel.dash_board.GetUserDashBoardResponse;
import org.springframework.stereotype.Service;

import java.lang.reflect.UndeclaredThrowableException;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.util.List;

@Service
public class DashBoardService {
    final AccessLogService accessLogService;
    final UserService userService;
    final AdviseService adviseService;
    final OrderService orderService;

    public DashBoardService(AccessLogService accessLogService, UserService userService, AdviseService adviseService, OrderService orderService) {
        this.accessLogService = accessLogService;
        this.userService = userService;
        this.adviseService = adviseService;
        this.orderService = orderService;
    }

    public GetUserDashBoardResponse getUserDashBoard(int year, int month) throws Exception {
        if (year < 1900 || year > 2100 || month < 0 || month > 12) {
            throw new Exception(Message.INVALID_INPUT);
        }

        LocalDate startDate = LocalDate.of(year, month, 1);
        LocalDate endDate = startDate.with(TemporalAdjusters.lastDayOfMonth());

        Date[] date = Util.getStartDateAndEndDate(year, month);


        List<Long> timeAccess = accessLogService.countAccessLogsByDay(startDate, endDate);
        List<Long> newUserList = userService.countUserPerDay(date[0], date[1]);

        long totalAccess = Util.sumList(timeAccess);
        long totalUser = Util.sumList(newUserList);

        long totalUserBefore = userService.totalUserBefore(date[1]);
        long totalAccessBefore = accessLogService.totalAccessBefore(endDate);

        long newAdvise = adviseService.countAdviseByMonth(year, month);


        return GetUserDashBoardResponse.builder()
                .timeAccess(timeAccess)
                .newUser(newUserList)
                .totalAccess(totalAccess)
                .totalUser(totalUser)
                .totalAccessBefore(totalAccessBefore)
                .totalUserBefore(totalUserBefore)
                .newAdvise(newAdvise)
                .build();
    }

    public GetOrderDashBoardResponse getOrderDashBoard(int year, int month) throws Exception {
        if (year < 1900 || year > 2100 || month < 0 || month > 12) {
            throw new Exception(Message.INVALID_INPUT);
        }

        Date[] date = Util.getStartDateAndEndDate(year, month);
        List<Long> timeOrder = orderService.countOrderPerDay(date[0], date[1]);
        long total = Util.sumList(timeOrder);
        long revenue = orderService.totalRevenue(date[0], date[1]);
        long totalOrderBefore = orderService.totalOrderBefore(date[1]);

        return new GetOrderDashBoardResponse(timeOrder, total, revenue, totalOrderBefore);

    }
}
