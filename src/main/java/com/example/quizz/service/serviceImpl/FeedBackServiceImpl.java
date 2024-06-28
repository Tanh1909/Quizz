package com.example.quizz.service.serviceImpl;

import com.example.quizz.entity.FeedBack;
import com.example.quizz.repository.FeedBackRepository;
import com.example.quizz.service.FeedBackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedBackServiceImpl implements FeedBackService {
    @Autowired
    private FeedBackRepository feedBackRepository;
    @Override
    public FeedBack create(FeedBack feedBackReq) {
        return feedBackRepository.save(feedBackReq);
    }

    @Override
    public List<FeedBack> findAll() {
        return feedBackRepository.findAll();
    }
}
