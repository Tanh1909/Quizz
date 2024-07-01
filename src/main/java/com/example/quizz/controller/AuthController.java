package com.example.quizz.controller;

import com.cloudinary.api.ApiResponse;
import com.example.quizz.dto.request.AuthRequest;
import com.example.quizz.dto.request.UserCreationRequest;
import com.example.quizz.dto.response.ResponseApi;
import com.example.quizz.email.EmailService;
import com.example.quizz.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    @Autowired
    private EmailService emailService;
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody UserCreationRequest userCreationRequest){
        return ResponseEntity.ok(ResponseApi.success(authService.signup(userCreationRequest)));
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest){
        return ResponseEntity.ok(ResponseApi.success(authService.login(authRequest)));
    }
    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestParam String token ){
        authService.logout(token);
        return ResponseEntity.ok(ResponseApi.success("HAS BEEN LOGOUT!"));
    }
    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(@RequestParam String token){
        return ResponseEntity.ok(ResponseApi.success(authService.refreshToken(token)));
    }
    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestParam String email){
        authService.forgotPassword(email);
        return ResponseEntity.ok(ResponseApi.success(null));
    }
    @PostMapping("/confirm-otp")
    public ResponseEntity<?> confirmOTP(@RequestParam String email,@RequestParam String otp){
        return ResponseEntity.ok(ResponseApi.success(authService.confirmOTP(email,otp)));
    }
    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestParam String newPassword){
        return ResponseEntity.ok(ResponseApi.success(authService.changePassword(newPassword)));
    }


}
