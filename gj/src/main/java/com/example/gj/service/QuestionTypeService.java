package com.example.gj.service;

import com.example.gj.model.QuestionType;
import com.example.gj.repository.QuestionTypeRepository;
import com.example.gj.viewmodel.question_type.GetQuestionTypeRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionTypeService {
    final QuestionTypeRepository questionTypeRepository;

    public QuestionTypeService(QuestionTypeRepository questionTypeRepository) {
        this.questionTypeRepository = questionTypeRepository;
    }

    public List<GetQuestionTypeRequest> get() {
        List<QuestionType> questionTypes = questionTypeRepository.findAll();

        List<GetQuestionTypeRequest> response = new ArrayList<>();
        for (QuestionType q : questionTypes) {
            response.add(new GetQuestionTypeRequest(q));
        }
        return response;
    }
}
