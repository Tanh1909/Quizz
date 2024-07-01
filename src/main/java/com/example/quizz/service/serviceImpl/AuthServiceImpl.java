package com.example.quizz.service.serviceImpl;

import com.example.quizz.dto.request.AuthRequest;
import com.example.quizz.dto.request.UserCreationRequest;
import com.example.quizz.dto.response.AuthResponse;
import com.example.quizz.dto.response.UserResponse;
import com.example.quizz.email.EmailService;
import com.example.quizz.entity.ERole;
import com.example.quizz.entity.User;
import com.example.quizz.exception.AppException;
import com.example.quizz.exception.ErrorCode;
import com.example.quizz.mapper.UserMapper;
import com.example.quizz.repository.RoleRepository;
import com.example.quizz.repository.UserRepository;
import com.example.quizz.security.jwt.JwtUtils;
import com.example.quizz.security.service.UserDetailImpl;
import com.example.quizz.service.AuthService;
import com.example.quizz.service.RedisService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private RedisService redisService;
    @Autowired
    private EmailService emailService;

    @Override
    public AuthResponse login(AuthRequest authRequest) {
        User user=userRepository.findByUsername(authRequest.getUsername()).orElseThrow(() -> new AppException(ErrorCode.WRONG_USERNAME_OR_PASSWORD));
        if(passwordEncoder.matches(authRequest.getPassword(),user.getPassword())){
            UserResponse userResponse=userMapper.toUserResponse(user);
            String token=jwtUtils.generateToken(user,false);
            String refreshToken= jwtUtils.generateToken(user,true);
            user.setRefreshToken(refreshToken);
            userRepository.save(user);
            return AuthResponse.builder().token(token).userResponse(userResponse).refreshToken(refreshToken).build();
        }
        else{
            throw new AppException(ErrorCode.WRONG_USERNAME_OR_PASSWORD);
        }
    }

    @Override
    public AuthResponse refreshToken(String refreshToken) {
        Claims claims=jwtUtils.getBody(refreshToken);
        if(claims!=null){
            User user=userRepository.findByUsername(claims.getSubject()).orElseThrow(() ->   new AppException(ErrorCode.UNAUTHENTICATED));
            if(user.getRefreshToken().equals(refreshToken)){
                String token=jwtUtils.generateToken(user,false);
                String newRefreshToken= jwtUtils.generateToken(user,true);
                user.setRefreshToken(newRefreshToken);
                userRepository.save(user);
                return AuthResponse.builder().token(token).userResponse(userMapper.toUserResponse(user)).refreshToken(newRefreshToken).build();
            }
        }
        throw new AppException(ErrorCode.UNAUTHENTICATED);
    }

    @Override
    public UserResponse signup(UserCreationRequest userCreationRequest) {
        if(userRepository.existsByUsername(userCreationRequest.getUsername())){
            throw new AppException(ErrorCode.USERNAME_ALREADY_EXISTS);
        }
        if(userRepository.existsByEmail(userCreationRequest.getEmail())){
            throw new AppException(ErrorCode.EMAIL_ALREADY_EXISTS);
        }
        User user=userMapper.toUser(userCreationRequest);
        user.setPassword(passwordEncoder.encode(userCreationRequest.getPassword()));
        user.getRoles().add(roleRepository.findById(ERole.ROLE_USER.name()).get());
        userRepository.save(user);
        UserResponse userResponse=userMapper.toUserResponse(user);
        return userResponse;
    }

    @Override
    public void logout(String token) {
        if(token!=null){
            String id=jwtUtils.getIdJwt(token);
            long expiration=jwtUtils.getExpiration(token);
            redisService.set(id,token,expiration);
        }
        else {
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }
    }

    @Override
    public void forgotPassword(String email) {
        User user=userRepository.findByEmail(email).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        String otp=generateOTP();
        redisService.set(user.getUsername(),otp,300000L);
        String content="Hi,"+user.getUsername()+"\n"
                +"Your OTP is: "+otp+"\n"
                +"This OTP will expire in 5 minutes"+"\n"
                +"Thank You!";
        emailService.sendMail(email,"Request to change your password!",content);

    }

    @Override
    public AuthResponse confirmOTP(String email,String otp) {
        User user=userRepository.findByEmail(email).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        String username= user.getUsername();
        if(redisService.get(username)!=null&&redisService.get(username).equals(otp)){
            UserResponse userResponse=userMapper.toUserResponse(user);
            String token=jwtUtils.generateToken(user,false);
            String refreshToken= jwtUtils.generateToken(user,true);
            user.setRefreshToken(refreshToken);
            userRepository.save(user);
            return AuthResponse.builder().token(token).userResponse(userResponse).refreshToken(refreshToken).build();
        }
        throw new AppException(ErrorCode.WRONG_OTP);
    }

    @Override
    public UserResponse changePassword(String newPassword) {
        User user=getCurrentUser();
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
        return userMapper.toUserResponse(user);
    }

    @Override
    public User getCurrentUser() {
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        if(authentication.getPrincipal().equals("anonymousUser")){
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }
        UserDetailImpl userDetail=(UserDetailImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepository.findByUsername(userDetail.getUsername()).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
    }
    private String generateOTP() {
        Random random = new Random();
        int otpValue = 100000 + random.nextInt(900000); // OTP will be a 6-digit number
        return String.valueOf(otpValue);
    }
}
