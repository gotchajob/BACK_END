package com.example.gj.viewmodel.question_type;

import com.example.gj.model.QuestionType;
import com.example.gj.viewmodel.question.GetQuestionRequest;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GetQuestionTypeRequest {
    private long id;
    private String name;

    public GetQuestionTypeRequest(QuestionType questionType) {
        this.id = questionType.getId();
        this.name = questionType.getName();
    }

}
