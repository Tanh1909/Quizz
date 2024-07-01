package com.example.quizz.service;

import com.example.quizz.dto.request.AuthRequest;
import com.example.quizz.dto.request.UserCreationRequest;
import com.example.quizz.dto.response.AuthResponse;
import com.example.quizz.dto.response.UserResponse;
import com.example.quizz.entity.User;

public interface AuthService {
    AuthResponse login(AuthRequest authRequest);
    AuthResponse refreshToken(String refreshToken);
    UserResponse signup(UserCreationRequest userCreationRequest);
    void logout(String token);
    void forgotPassword(String email);
    AuthResponse confirmOTP(String email,String otp);
    UserResponse changePassword(String newPassword);
    User getCurrentUser();
}
