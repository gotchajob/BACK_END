package com.example.gj.controller;

import com.example.gj.model.Response;
import com.example.gj.service.NotifyService;
import com.example.gj.util.Role;
import com.example.gj.viewmodel.notify.GetNotifyResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notify")
public class NotifyController {
    private final NotifyService notifyService;

    public NotifyController(NotifyService notifyService) {
        this.notifyService = notifyService;
    }

    @PostMapping("")
    public ResponseEntity<Response<String>> demo() {
        try {

            return Response.success(null);
        } catch (Exception e) {
            return Response.error(e);
        }
    }

    @GetMapping("")
    public ResponseEntity<Response<String>> get() {
        try {

            return Response.success(null);
        } catch (Exception e) {
            return Response.error(e);
        }
    }

    @GetMapping("/get-by-user")
    @Secured(Role.USER)
    public ResponseEntity<Response<GetNotifyResponse>> getListByUser(@RequestParam(defaultValue = "1") int page,
                                                                     @RequestParam(defaultValue = "5") int limit,
                                                                     @RequestParam(defaultValue = "createdAt",required = false) String sortBy,
                                                                     @RequestParam(defaultValue = "asc",required = false) String sortOrder) {
        try {
            GetNotifyResponse response = notifyService.getByUser(page, limit, sortBy, sortOrder);
            return Response.success(response);
        } catch (Exception e) {
            return Response.error(e);
        }
    }
}
