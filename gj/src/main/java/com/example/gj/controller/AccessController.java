package com.example.gj.controller;

import com.example.gj.model.Response;
import com.example.gj.service.AccessLogService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/access")
public class AccessController {
    private final AccessLogService accessLogService;

    public AccessController(AccessLogService accessLogService) {
        this.accessLogService = accessLogService;
    }
    @PostMapping("")
    public ResponseEntity<Response<String>> newAccess() {
        try {
            accessLogService.createAccessLog();
            return Response.success(null);
        } catch (Exception e) {
            return Response.error(e);
        }
    }
}
