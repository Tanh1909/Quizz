package com.example.quizz.entity;

import com.example.quizz.entity.embeddedKey.UserQuestionKey;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UserQuestion {
    @EmbeddedId
    private UserQuestionKey id=new UserQuestionKey();

    @ManyToOne
    @JoinColumn(name = "user_topic_id")
    @MapsId("userQuestionId")
    @JsonIgnore
    private UserTopic userTopic;

    @ManyToOne
    @JoinColumn(name = "question_id")
    @MapsId("questionId")
    private Question question;

    private Integer answer;

}
