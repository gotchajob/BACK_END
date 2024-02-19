package com.example.gj.viewmodel.answer;

import lombok.Data;

@Data
public class CreateAnswerRequest {
    private String questionId;
    private String answer;
}
