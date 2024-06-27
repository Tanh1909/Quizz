package com.example.quizz.security.service;

import com.example.quizz.entity.User;
import com.example.quizz.exception.AppException;
import com.example.quizz.exception.ErrorCode;
import com.example.quizz.repository.UserRepository;
import com.example.quizz.security.service.UserDetailImpl;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userRepository.findByUsername(username).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        return UserDetailImpl.builder(user);
    }
}
