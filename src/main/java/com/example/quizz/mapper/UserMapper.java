package com.example.quizz.mapper;

import com.example.quizz.dto.request.UserCreationRequest;
import com.example.quizz.dto.request.UserUpdateRequest;
import com.example.quizz.dto.response.UserResponse;
import com.example.quizz.entity.User;
import org.mapstruct.*;

@Mapper(componentModel = "spring",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {

    User toUser(UserCreationRequest userCreationRequest);

    UserResponse toUserResponse(User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "avatar",ignore = true)
    void toUser(@MappingTarget User user, UserUpdateRequest userUpdateRequest);

}
