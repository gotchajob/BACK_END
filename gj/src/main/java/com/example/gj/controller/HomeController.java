package com.example.gj.controller;

import com.example.gj.model.Response;
import com.example.gj.service.HomeService;
import com.example.gj.viewmodel.advise.AdviseRequest;
import com.example.gj.viewmodel.order_service.OrderServiceRequest;
import com.example.gj.viewmodel.subscribe_news.SubscribeNewsRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class HomeController {
    final HomeService homeService;

    public HomeController(HomeService homeService) {
        this.homeService = homeService;
    }

    @GetMapping("")
    public String home() {
        return "gotcha job system API";
    }




}
