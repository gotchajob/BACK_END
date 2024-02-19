package com.example.gj.controller;

import com.example.gj.model.Response;
import com.example.gj.service.QuestionTypeService;
import com.example.gj.viewmodel.question_type.GetQuestionTypeRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question-type")
public class QuestionTypeController {

    final QuestionTypeService questionTypeService;

    public QuestionTypeController(QuestionTypeService questionTypeService) {
        this.questionTypeService = questionTypeService;
    }

    @GetMapping("")
    public ResponseEntity<Response<List<GetQuestionTypeRequest>>> get() {
        try {
            List<GetQuestionTypeRequest> response = questionTypeService.get();
            return Response.success(response);
        } catch (Exception e) {
            return Response.error(e);
        }
    }
}
