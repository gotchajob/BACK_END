package com.example.gj.controller;

import com.example.gj.model.Response;
import com.example.gj.service.AdviseService;
import com.example.gj.util.Role;
import com.example.gj.viewmodel.advise.AdviseRequest;
import com.example.gj.viewmodel.advise.GetAdviseResponse;
import com.example.gj.viewmodel.advise.UpdateStatusRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
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
    @Secured(Role.ADMIN)
    public ResponseEntity<Response<GetAdviseResponse>> getList(@RequestParam(defaultValue = "1") int page,
                                                               @RequestParam(defaultValue = "5") int limit,
                                                               @RequestParam(defaultValue = "createdAt",required = false) String sortBy,
                                                               @RequestParam(defaultValue = "asc",required = false) String sortOrder) {
        try {
            GetAdviseResponse responses = adviseService.getAdvise(page, limit, sortBy, sortOrder);
            return Response.success(responses);
        } catch (Exception e) {
            return Response.error(e);
        }
    }

    @PostMapping("/update-status")
    @Secured(Role.ADMIN)
    public ResponseEntity<Response<GetAdviseResponse>> updateStatus(@RequestBody UpdateStatusRequest request) {
        try {
            adviseService.updateStatus(request.getId(), request.getStatus());
            return Response.success(null);
        } catch (Exception e) {
            return Response.error(e);
        }
    }
}
