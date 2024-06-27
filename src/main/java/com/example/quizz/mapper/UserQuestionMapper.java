package com.example.quizz.mapper;

import com.example.quizz.dto.request.UserQuestionRequest;
import com.example.quizz.dto.response.UserQuestionResponse;
import com.example.quizz.entity.UserQuestion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserQuestionMapper {
     UserQuestion toUserQuestion(UserQuestionRequest userQuestionRequest);
     List<UserQuestion> toUserQuestions(List<UserQuestionRequest> userQuestionRequests);
     @Mapping(target = "questionId",source = "id.questionId")
     UserQuestionResponse toUserQuestionResponse(UserQuestion userQuestion);

     List<UserQuestionResponse> toUserQuestionResponses(List<UserQuestion> userQuestions);
}
