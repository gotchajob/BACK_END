package com.example.gj.controller;

import com.example.gj.model.Response;
import com.example.gj.service.HomeService;
import com.example.gj.viewmodel.advise.AdviseRequest;
import com.example.gj.viewmodel.order_service.OrderServiceRequest;
import com.example.gj.viewmodel.subscribe_news.SubscribeNewsRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/home")
public class HomeController {
    final HomeService homeService;

    public HomeController(HomeService homeService) {
        this.homeService = homeService;
    }

    @GetMapping("/")
    public String home() {
        return "gotcha job system API";
    }

    @PostMapping("/order-service")
    public ResponseEntity<Response<Object>> orderService(@RequestBody OrderServiceRequest request) {
        try {
            homeService.orderService(request);
            return Response.success(null);
        } catch (Exception e) {
            return Response.error(e);
        }
    }

    @PostMapping("/subscribe-news")
    public ResponseEntity<Response<Object>> subscribeNews(@RequestBody SubscribeNewsRequest request) {
        try {
            homeService.subscribeNews(request);
            return Response.success(null);
        } catch (Exception e) {
            return Response.error(e);
        }
    }

    @PostMapping("/advice-service")
    public ResponseEntity<Response<Object>> adviceService(@RequestBody AdviseRequest request) {
        try {
            homeService.adviseService(request);

            return Response.success(null);
        } catch (Exception e) {
            return Response.error(e);
        }
    }


}
