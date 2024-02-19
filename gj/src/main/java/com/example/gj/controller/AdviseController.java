package com.example.gj.controller;

import com.example.gj.model.Response;
import com.example.gj.service.AdviseService;
import com.example.gj.viewmodel.advise.AdviseRequest;
import com.example.gj.viewmodel.advise.GetAdviseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/advice-service")
public class AdviseController {
    final AdviseService adviseService;

    public AdviseController(AdviseService adviseService) {
        this.adviseService = adviseService;
    }

    @PostMapping("")
    public ResponseEntity<Response<String>> create(@RequestBody AdviseRequest request) {
        try {
            adviseService.create(request);
            return Response.success(null);
        } catch (Exception e) {
            return Response.error(e);
        }
    }

    @GetMapping("")
    public ResponseEntity<Response<List<GetAdviseResponse>>> getList() {
        try {
            List<GetAdviseResponse> responses = adviseService.getAdvise();
            return Response.success(responses);
        } catch (Exception e) {
            return Response.error(e);
        }
    }
}
