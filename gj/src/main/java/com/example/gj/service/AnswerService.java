package com.example.gj.service;

import com.example.gj.config.response.Message;
import com.example.gj.model.Answer;
import com.example.gj.repository.AnswerRepository;
import com.example.gj.viewmodel.answer.CreateAnswerRequest;
import com.example.gj.viewmodel.user.UserResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerService {
    final AnswerRepository answerRepository;
    final UserService userService;
    final EmailService emailService;

    public AnswerService(AnswerRepository answerRepository, UserService userService, EmailService emailService) {
        this.answerRepository = answerRepository;
        this.userService = userService;
        this.emailService = emailService;
    }

    public void createAnswer(List<CreateAnswerRequest> request) throws Exception {
        UserResponse user = userService.getCurrentUser();

        for (CreateAnswerRequest req : request) {
            if (req == null || req.getQuestionId() == null || req.getAnswer() == null) {
                throw new Exception(Message.NULL_INPUT);
            }

            Answer answer = new Answer(req, user.getId());
            answerRepository.save(answer);
        }

        emailService.sendMailAnswerQuestion(request, user.getEmail());
    }
}
