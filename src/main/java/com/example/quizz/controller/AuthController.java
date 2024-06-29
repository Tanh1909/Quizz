package com.example.quizz.controller;

import com.cloudinary.api.ApiResponse;
import com.example.quizz.dto.request.AuthRequest;
import com.example.quizz.dto.request.UserCreationRequest;
import com.example.quizz.dto.response.ResponseApi;
import com.example.quizz.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody UserCreationRequest userCreationRequest){
        return ResponseEntity.ok(ResponseApi.success(authService.signup(userCreationRequest)));
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest){
        return ResponseEntity.ok(ResponseApi.success(authService.login(authRequest)));
    }
    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(@RequestParam String token){
        return ResponseEntity.ok(ResponseApi.success(authService.refreshToken(token)));
    }
}
