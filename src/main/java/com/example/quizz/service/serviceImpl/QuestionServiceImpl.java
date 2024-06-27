package com.example.quizz.service.serviceImpl;

import com.example.quizz.entity.Answer;
import com.example.quizz.entity.Question;
import com.example.quizz.exception.AppException;
import com.example.quizz.exception.ErrorCode;
import com.example.quizz.repository.QuestionRepository;
import com.example.quizz.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private QuestionRepository questionRepository;
    @Override
    public Question create(Question question) {
        return questionRepository.save(question);
    }

    @Override
    public Question findById(Long id) {
        return questionRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.QUESTION_NOT_FOUND));
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public List<Question> findAll() {
        List<Question> questions=questionRepository.findAll();
        if(questions.size()==0){
            throw new AppException(ErrorCode.QUESTION_NOT_FOUND);
        }
        return questions;
    }

    @Override
    public Question addAnswers(Long id, List<Answer> answers) {
        if(answers.size()==0){
            throw new AppException(ErrorCode.ANSWERS_ARRAY_EMPTY);
        }
        Question question=findById(id);
        question.getAnswers().addAll(answers);
        answers.forEach(answer -> answer.setQuestion(question));

        return questionRepository.save(question);
    }

    @Override
    public List<Question> findByTopicId(Long id) {
        return questionRepository.findByTopicId(id);
    }

}
