package com.example.quizz.service.serviceImpl;

import com.example.quizz.dto.request.UserUpdateRequest;
import com.example.quizz.dto.response.UserResponse;
import com.example.quizz.entity.User;
import com.example.quizz.exception.AppException;
import com.example.quizz.exception.ErrorCode;
import com.example.quizz.mapper.UserMapper;
import com.example.quizz.repository.UserRepository;
import com.example.quizz.service.AuthService;
import com.example.quizz.service.UploadFileService;
import com.example.quizz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private AuthService authService;
    @Autowired
    private UploadFileService uploadFileService;

    @Override
    public UserResponse findById(Long id) {
        UserResponse userResponse=userMapper.toUserResponse(userRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND)));
        return userResponse;
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
    }

    @Override
    public UserResponse patchUser(UserUpdateRequest userUpdateRequest) {
        User user= authService.getCurrentUser();
        if(userUpdateRequest.getAvatar()!=null){
            String url=uploadFileService.upload(userUpdateRequest.getAvatar());
            user.setAvatar(url);
        }
        userMapper.toUser(user,userUpdateRequest);
        userRepository.save(user);
        UserResponse userResponse=userMapper.toUserResponse(user);
        return userResponse;
    }

    @Override
    public UserResponse getProfile() {
        return userMapper.toUserResponse(authService.getCurrentUser());
    }
}
