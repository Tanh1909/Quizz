package com.example.quizz.mapper;

import com.example.quizz.dto.request.QuestionRequest;
import com.example.quizz.entity.Answer;
import com.example.quizz.entity.Question;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface QuestionMapper {
    @Mapping(source = "answers",target ="answers" ,qualifiedByName ="convertAnswer" )
    List<Question> toQuestions(List<QuestionRequest> questionRequests);

    default List<Answer> convertAnswer(List<String> answersReq){
        List<Answer> answers=new ArrayList<>();
        answersReq.forEach(s -> {
            Answer answer=new Answer();
            answer.setAnswer(s);
            answers.add(answer);
        });
        return answers;
    }

}
