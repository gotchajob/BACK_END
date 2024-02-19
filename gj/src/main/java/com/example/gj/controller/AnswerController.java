package com.example.gj.controller;

import com.example.gj.model.Response;
import com.example.gj.service.AnswerService;
import com.example.gj.util.Role;
import com.example.gj.viewmodel.answer.CreateAnswerRequestList;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/answer")
public class AnswerController {
    final AnswerService answerService;

    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    @PostMapping("")
    @Secured(Role.USER)
    public ResponseEntity<Response<String>> createAnswer(@RequestBody CreateAnswerRequestList request) {
        try {
            answerService.createAnswer(request.getAnswerList());
            return Response.success(null);
        } catch (Exception e) {
            return Response.error(e);
        }
    }
}
