package com.example.quizz.mapper;

import com.example.quizz.dto.request.TopicRequest;
import com.example.quizz.dto.response.TopicResponseDTO;
import com.example.quizz.entity.Topic;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,uses = QuestionMapper.class)
public interface TopicMapper {
    @Mapping(target = "image",ignore = true)
    @Mapping(target = "category",ignore = true)
    Topic toTopic(TopicRequest topicRequest);

    @Mapping(target = "image",ignore = true)
    @Mapping(target = "category",ignore = true)
    void toTopic(@MappingTarget Topic topic, TopicRequest topicRequest);


    @Mapping(target = "category",source = "category.name")
    TopicResponseDTO toTopicResponse(Topic topic);


    @Mapping(target = "category",source = "category.name")
    List<TopicResponseDTO> toTopicResponses(List<Topic> topics);
}
