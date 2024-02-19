package com.example.gj.service;

import com.example.gj.config.response.Message;
import com.example.gj.model.Question;
import com.example.gj.repository.QuestionRepository;
import com.example.gj.viewmodel.question.CreateQuestionRequest;
import com.example.gj.viewmodel.question.GetQuestionResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class QuestionService {
    private static final int LIMIT_QUESTION = 5;

    final QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }


    public List<GetQuestionResponse> getQuestionByType(int type) throws Exception {
        List<Question> questions = questionRepository.findRandomRowsByType(type, LIMIT_QUESTION);
        if (questions == null) {
            throw new Exception(Message.NOT_FOUND);
        }

        List<GetQuestionResponse> response = new ArrayList<>();
        for (Question q : questions) {
            response.add(new GetQuestionResponse(q));
        }

        return response;
    }

    public void createQuestion(CreateQuestionRequest request) throws Exception {
        if (request == null || request.getQuestion() == null || request.getType() <= 0) {
            throw new Exception(Message.NULL_INPUT);

        }

        Question question = new Question();
        question.setId(UUID.randomUUID().toString());
        question.setQuestion(request.getQuestion());
        question.setQuestionTypeId(request.getType());
        question.setStatus(1);
        question.setCreatedAt(new Date());
        question.setUpdatedAt(new Date());

        questionRepository.save(question);
    }
}
