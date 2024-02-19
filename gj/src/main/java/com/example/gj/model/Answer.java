package com.example.gj.model;

import com.example.gj.viewmodel.answer.CreateAnswerRequest;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class Answer {
    @Id
    private String id;
    private String questionId;
    private String answer;
    private String customerId;
    private int status;
    private Date created_at;
    private Date updated_at;

    public Answer(CreateAnswerRequest request, String userId) {
        this.id = UUID.randomUUID().toString();
        this.questionId = request.getQuestionId();
        this.answer = request.getAnswer();
        this.customerId = userId;
        this.status = 1;
        this.created_at = new Date();
        this.updated_at = new Date();
    }


}
