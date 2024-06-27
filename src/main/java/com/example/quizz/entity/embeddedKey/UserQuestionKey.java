package com.example.quizz.entity.embeddedKey;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class UserQuestionKey implements Serializable {
    @Column(name = "user_question_id")
    private Long userQuestionId;
    @Column(name = "question_id")
    private Long questionId;
}
