package com.example.quizz.service.serviceImpl;

import com.example.quizz.dto.request.QuestionRequest;
import com.example.quizz.dto.request.TopicRequest;
import com.example.quizz.dto.response.TopicResponseDTO;
import com.example.quizz.entity.Answer;
import com.example.quizz.entity.Question;
import com.example.quizz.entity.Topic;
import com.example.quizz.exception.AppException;
import com.example.quizz.exception.ErrorCode;
import com.example.quizz.repository.QuestionRepository;
import com.example.quizz.repository.TopicRepository;
import com.example.quizz.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TopicServiceImpl implements TopicService {
    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private QuestionRepository questionRepository;
    @Override
    public Topic create(TopicRequest topicRequest) {
        Topic topic=new Topic();
        topic.setName(topicRequest.getName());
        topic.setQuestions(topicRequest.getQuestions());
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
    public void deleteById(Long id) {

    }

    @Override
    public List<TopicResponseDTO> findAll() {
        List<Topic> topics=topicRepository.findAll();
        if(topics.size()==0){
            throw new AppException(ErrorCode.TOPIC_IS_EMPTY);
        }
        return topics.stream().map(topic -> new TopicResponseDTO(topic.getName())).collect(Collectors.toList());
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
}
