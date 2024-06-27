package com.example.quizz.mapper;

import com.example.quizz.dto.response.UserTopicResponse;
import com.example.quizz.entity.UserQuestion;
import com.example.quizz.entity.UserTopic;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,uses = {UserQuestionMapper.class,UserMapper.class,TopicMapper.class})
public interface UserTopicMapper {
    @Mapping(source = "topic",target = "topic")
    @Mapping(source = "user",target = "user")
    UserTopicResponse toUserTopicResponse(UserTopic userTopic);

    List<UserTopicResponse> toUserTopicResponses(List<UserTopic> userTopics);
}
