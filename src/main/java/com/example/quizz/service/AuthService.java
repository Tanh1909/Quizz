package com.example.quizz.service;

import com.example.quizz.dto.request.AuthRequest;
import com.example.quizz.dto.request.UserCreationRequest;
import com.example.quizz.dto.response.AuthResponse;
import com.example.quizz.dto.response.UserResponse;
import com.example.quizz.entity.User;

public interface AuthService {
    AuthResponse login(AuthRequest authRequest);
    UserResponse signup(UserCreationRequest userCreationRequest);
    User getCurrentUser();
}
