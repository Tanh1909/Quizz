package com.example.quizz.service.serviceImpl;
import com.example.quizz.constants.PaginationConstants;
import com.example.quizz.dto.request.QuestionRequest;
import com.example.quizz.dto.request.TopicRequest;
import com.example.quizz.dto.response.TopicResponseDTO;
import com.example.quizz.entity.*;
import com.example.quizz.exception.AppException;
import com.example.quizz.exception.ErrorCode;
import com.example.quizz.mapper.QuestionMapper;
import com.example.quizz.mapper.TopicMapper;
import com.example.quizz.repository.CategoryRepository;
import com.example.quizz.repository.QuestionRepository;
import com.example.quizz.repository.TopicRepository;
import com.example.quizz.repository.UserTopicRepository;
import com.example.quizz.service.AuthService;
import com.example.quizz.service.TopicService;
import com.example.quizz.service.UploadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TopicServiceImpl implements TopicService {
    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private AuthService authService;
    @Autowired
    private TopicMapper topicMapper;
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UploadFileService uploadFileService;
    @Autowired
    private UserTopicRepository userTopicRepository;

    @Override
    public List<TopicResponseDTO> findByUser(String username) {
        List<Topic> topics=topicRepository.findAllByUserUsername(username);
        return topicMapper.toTopicResponses(topics);
    }

    @Override
    public Topic create(TopicRequest topicRequest) {
        User user=authService.getCurrentUser();
        Category category=categoryRepository.findById(topicRequest.getCategory()).orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_FOUND));
        Topic topic=new Topic();
        topic.setName(topicRequest.getName());
        List<Question> questions=questionMapper.toQuestions(topicRequest.getQuestions());
        questions.forEach(question -> {
            question.getAnswers().forEach(answer -> answer.setQuestion(question));
            question.setTopic(topic);
        });
        topic.setQuestions(questions);
        topic.setCategory(category);
        topic.setUser(user);
        if(topicRequest.getImage()!=null){
            String url=uploadFileService.upload(topicRequest.getImage());
            topic.setImage(url);
        }
        return topicRepository.save(topic);
    }

    @Override
    public List<Topic> create(List<Topic> topics) {
        return topicRepository.saveAll(topics);
    }

    @Override
    public Topic findById(Long id) {
        return topicRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.TOPIC_NOT_FOUND));
    }



    @Override
    public List<TopicResponseDTO> findAll() {
        List<Topic> topics=topicRepository.findAll();
        if(topics.size()==0){
            throw new AppException(ErrorCode.TOPIC_IS_EMPTY);
        }
        return topicMapper.toTopicResponses(topics);
    }

    @Override
    public Topic addQuestion(Long id,List<QuestionRequest> questionsReq) {
        if(questionsReq.size()==0){
            throw new AppException(ErrorCode.QUESTIONS_ARRAY_EMPTY);
        }
        Topic topic=findById(id);
        List<Question> questions=questionsReq.stream().map(questionRequest -> {
            Question question=new Question();
            question.setTopic(topic);
            question.setCorrectAnswer(questionRequest.getCorrectAnswer());
            question.setQuestion(questionRequest.getQuestion());
            question.setAnswers(questionRequest.getAnswers().stream().map(
                    s ->{
                        Answer answer=new Answer();
                        answer.setQuestion(question);
                        answer.setAnswer(s);
                        return answer;
                    }
            ).collect(Collectors.toList()));
            return question;
        }).collect(Collectors.toList());
        topic.setQuestions(questions);
        return  topicRepository.save(topic);
    }

    @Override
    public Topic patchTopic(Long id, TopicRequest topicRequest) {
        Category category=categoryRepository.findById(topicRequest.getCategory()).orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_FOUND));
        Topic topic=topicRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.TOPIC_NOT_FOUND));
        topicMapper.toTopic(topic,topicRequest);
        topic.setCategory(category);
        if(topicRequest.getImage()!=null){
            String url=uploadFileService.upload(topicRequest.getImage());
            topic.setImage(url);
        }
        return topicRepository.save(topic);

    }

    @Override
    public String uploadImage(Long id, MultipartFile image) {
        if(image!=null){
            Topic topic=topicRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.TOPIC_NOT_FOUND));
            String url=uploadFileService.upload(image);
            topic.setImage(url);
            topicRepository.save(topic);
            return url;
        }
        return null;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        userTopicRepository.deleteAllByTopicId(id);
        topicRepository.deleteById(id);
    }

    @Override
    public List<Topic> findAllByCategory(String category, Integer page, Integer size) {
        if(page!=null||size!=null){
            if(page==null) page= PaginationConstants.DEFAULT_PAGE.getValue();
            if(size==null) size=PaginationConstants.DEFAULT_SIZE.getValue();
            Pageable pageable= PageRequest.of(page,size);
            return topicRepository.findAllByCategoryName(category,pageable);
        }
        else{
            return topicRepository.findAllByCategoryName(category);
        }
    }

    @Override
    public List<TopicResponseDTO> searchByNameLike(String name, Integer page, Integer size) {
        if(page!=null||size!=null){
            if(page==null) page= PaginationConstants.DEFAULT_PAGE.getValue();
            if(size==null) size=PaginationConstants.DEFAULT_SIZE.getValue();
            Pageable pageable= PageRequest.of(page,size);
            return topicRepository.findAllByTopicNameContains(name,pageable);
        }
        else{
            return topicRepository.findAllByNameContaining(name);
        }
    }


}
