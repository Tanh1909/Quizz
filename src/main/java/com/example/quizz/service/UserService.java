package com.example.quizz.service;

import com.example.quizz.dto.request.UserUpdateRequest;
import com.example.quizz.dto.response.UserResponse;
import com.example.quizz.entity.User;

public interface UserService {
    UserResponse findById(Long id);
    User findByUsername(String username);
    UserResponse patchUser(UserUpdateRequest userUpdateRequest);
    UserResponse getProfile();
}
