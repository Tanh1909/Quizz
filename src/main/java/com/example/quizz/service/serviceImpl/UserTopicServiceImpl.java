package com.example.quizz.service.serviceImpl;

import com.example.quizz.dto.request.UserTopicRequest;
import com.example.quizz.dto.response.UserTopicResponse;
import com.example.quizz.entity.*;
import com.example.quizz.exception.AppException;
import com.example.quizz.exception.ErrorCode;
import com.example.quizz.mapper.UserQuestionMapper;
import com.example.quizz.mapper.UserTopicMapper;
import com.example.quizz.repository.UserTopicRepository;
import com.example.quizz.service.AuthService;
import com.example.quizz.service.TopicService;
import com.example.quizz.service.UserTopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserTopicServiceImpl implements UserTopicService {
    @Autowired
    private UserTopicRepository userTopicRepository;
    @Autowired
    private TopicService topicService;
    @Autowired
    private AuthService authService;
    @Autowired
    private UserQuestionMapper userQuestionMapper;
    @Autowired
    private UserTopicMapper userTopicMapper;
    @Override
    public UserTopicResponse create(UserTopicRequest userTopicRequest) {
        UserTopic userTopic=new UserTopic();
        Topic topic=topicService.findById(userTopicRequest.getTopicId());
        User user=authService.getCurrentUser();
        userTopic.setUser(user);
        userTopic.setTopic(topic);

        List<UserQuestion> userQuestions=userQuestionMapper.toUserQuestions(userTopicRequest.getAnswers());
        List<Question> questions=topic.getQuestions();
        for(int i=0;i<questions.size();i++){
            userQuestions.get(i).setQuestion(questions.get(i));
            userQuestions.get(i).setUserTopic(userTopic);
        }
        userTopic.setAnswers(userQuestions);
        return userTopicMapper.toUserTopicResponse(userTopicRepository.save(userTopic));
    }

    @Override
    public UserTopicResponse findById(Long id) {
        UserTopic userTopic=userTopicRepository.findById(id).orElseThrow(() ->new AppException( ErrorCode.TOPIC_NOT_FOUND));
        return userTopicMapper.toUserTopicResponse(userTopic);
    }

    @Override
    public List<UserTopicResponse> findByUser(Long id) {
        List<UserTopic> userTopics=userTopicRepository.findByUserId(id);
        return userTopicMapper.toUserTopicResponses(userTopics);
    }

    @Override
    public void deleteById(Long id) {
        userTopicRepository.deleteById(id);
    }
}
