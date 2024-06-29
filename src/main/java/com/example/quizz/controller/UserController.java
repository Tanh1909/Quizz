package com.example.quizz.controller;

import com.cloudinary.api.ApiResponse;
import com.example.quizz.dto.request.UserUpdateRequest;
import com.example.quizz.dto.response.ResponseApi;
import com.example.quizz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    @PatchMapping("")
    public ResponseEntity<?> patchUser( @ModelAttribute UserUpdateRequest userUpdateRequest){
        return ResponseEntity.ok(ResponseApi.success(userService.patchUser(userUpdateRequest)));
    }
    @GetMapping("")
    public ResponseEntity<?> getUserById(){
        return ResponseEntity.ok(ResponseApi.success(userService.getProfile()));
    }
}
