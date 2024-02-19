package com.example.gj.controller;

import com.example.gj.model.Response;
import com.example.gj.service.SubscribeNewsService;
import com.example.gj.viewmodel.subscribe_news.GetSubscribeNewsResponse;
import com.example.gj.viewmodel.subscribe_news.SubscribeNewsRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subscribe-news")
public class SubscribeNewsController {
    final SubscribeNewsService subscribeNewsService;

    public SubscribeNewsController(SubscribeNewsService subscribeNewsService) {
        this.subscribeNewsService = subscribeNewsService;
    }

    @PostMapping("")
    public ResponseEntity<Response<String>> create(@RequestBody SubscribeNewsRequest request) {
        try {
            subscribeNewsService.create(request);
            return Response.success(null);
        } catch (Exception e) {
            return Response.error(e);
        }
    }

    @GetMapping("")
    public ResponseEntity<Response<List<GetSubscribeNewsResponse>>> get() {
        try {
            List<GetSubscribeNewsResponse> responseList = subscribeNewsService.getSubscribeNews();
            return Response.success(responseList);
        } catch (Exception e) {
            return Response.error(e);
        }
    }

}
