package com.example.gj.service;

import com.example.gj.viewmodel.dash_board.GetUserDashBoardResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DashBoardService {
    final AccessLogService accessLogService;
    final UserService userService;
    final AdviseService adviseService;

    public DashBoardService(AccessLogService accessLogService, UserService userService, AdviseService adviseService) {
        this.accessLogService = accessLogService;
        this.userService = userService;
        this.adviseService = adviseService;
    }

    public GetUserDashBoardResponse getUserDashBoard(int year, int month) {
        List<Long> timeAccess = accessLogService.getAccessCountByMonth(year, month);
        long newUser = userService.countUserByMonth(year, month);
        long newAdvise = adviseService.countAdviseByMonth(year, month);

        return new GetUserDashBoardResponse(timeAccess, newUser, newAdvise);
    }
}
