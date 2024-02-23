package com.example.gj.controller;

import com.example.gj.model.Response;
import com.example.gj.service.DashBoardService;
import com.example.gj.viewmodel.advise.AdviseRequest;
import com.example.gj.viewmodel.dash_board.GetUserDashBoardResponse;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dash-board")
public class DashBoardController {
    final DashBoardService dashBoardService;

    public DashBoardController(DashBoardService dashBoardService) {
        this.dashBoardService = dashBoardService;
    }

    @GetMapping("/user")
    public ResponseEntity<Response<GetUserDashBoardResponse>> create(@RequestParam int year, @RequestParam int month) {
        try {
            GetUserDashBoardResponse response = dashBoardService.getUserDashBoard(year, month);
            return Response.success(response);
        } catch (Exception e) {
            return Response.error(e);
        }
    }
}