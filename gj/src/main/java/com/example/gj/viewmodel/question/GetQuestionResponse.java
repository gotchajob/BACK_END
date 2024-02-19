package com.example.gj.viewmodel.question;

import com.example.gj.model.Question;
import lombok.Data;

@Data
public class GetQuestionResponse {
    private String id;
    private String question;

    public GetQuestionResponse() {

    }

    public GetQuestionResponse(Question question) {
        this.id = question.getId();
        this.question = question.getQuestion();
    }
}
