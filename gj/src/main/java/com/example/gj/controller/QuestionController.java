package com.example.gj.controller;

import com.example.gj.model.Response;
import com.example.gj.service.QuestionService;
import com.example.gj.util.Role;
import com.example.gj.viewmodel.question.CreateQuestionRequest;
import com.example.gj.viewmodel.question.GetQuestionRequest;
import com.example.gj.viewmodel.question.GetQuestionResponse;
import jakarta.websocket.server.PathParam;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {

    final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("")
    @Secured(Role.USER)
    public ResponseEntity<Response<List<GetQuestionResponse>>> getQuestion(@RequestParam int type) {
        try {
            List<GetQuestionResponse> responseList = questionService.getQuestionByType(type);
            return Response.success(responseList);
        } catch (Exception e) {
            return Response.error(e);
        }
    }

    @PostMapping("")
    @Secured(Role.ADMIN)
    public ResponseEntity<Response<String>> createQuestion(@RequestBody CreateQuestionRequest request) {
        try {
            questionService.createQuestion(request);
            return Response.success(null);
        } catch (Exception e) {
            return Response.error(e);
        }
    }

}
