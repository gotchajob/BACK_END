package com.example.gj.controller;

import com.example.gj.model.Response;
import com.example.gj.service.MentorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mentor")
public class MentorController {
    private final MentorService mentorService;

    public MentorController(MentorService mentorService) {
        this.mentorService = mentorService;
    }

    @PostMapping("")
    public ResponseEntity<Response<String>> demo() {
        try {

            return Response.success(null);
        } catch (Exception e) {
            return Response.error(e);
        }
    }
}
