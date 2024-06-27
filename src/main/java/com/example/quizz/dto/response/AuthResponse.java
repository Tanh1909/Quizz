package com.example.quizz.dto.response;

import lombok.*;
import org.springframework.security.core.Authentication;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
    private String token;
    private String refreshToken;
    private UserResponse userResponse;
}
